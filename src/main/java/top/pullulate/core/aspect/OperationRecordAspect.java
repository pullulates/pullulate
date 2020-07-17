package top.pullulate.core.aspect;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.HttpConstant;
import top.pullulate.core.annotations.OperationRecord;
import top.pullulate.core.rabbitmq.producer.RabbitMqOperationRecordProducer;
import top.pullulate.core.security.user.UserInfo;
import top.pullulate.core.utils.TokenUtils;
import top.pullulate.system.entity.PulOperationRecord;
import top.pullulate.utils.IPUtils;
import top.pullulate.utils.LocationUtils;
import top.pullulate.utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @功能描述:   操作日志切面实现类
 * @Author: xuyong
 * @Date: 2020/7/4 17:31
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class OperationRecordAspect {

    private final TokenUtils tokenUtils;

    private final RabbitMqOperationRecordProducer operationRecordProducer;

    @Pointcut("@annotation(top.pullulate.core.annotations.OperationRecord)")
    public void operationRecordPointCut() {
    }

    @Around("operationRecordPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        LocalDateTime begin = LocalDateTime.now();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("记录AOP日志发生异常", throwable);
        }
        int cost = LocalDateTime.now().compareTo(begin);
        buildOperationRecord(joinPoint, cost, result);
        return result;
    }

    /**
     * 构造操作日志信息
     *
     * @param joinPoint
     * @param cost
     */
    public void buildOperationRecord(ProceedingJoinPoint joinPoint, int cost, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OperationRecord recordAnnotation = method.getAnnotation(OperationRecord.class);
        if (ObjectUtil.isNotNull(recordAnnotation)) {
            String title = recordAnnotation.title();
            String ip = IPUtils.getIP();
            String location = LocationUtils.getLocation(ip);
            UserAgent userAgent = UserAgentUtil.parse(ServletUtils.getUserAgent());
            HttpServletRequest request = ServletUtils.getRequest();
            String params = getReqestParams(request, joinPoint);
            UserInfo userInfo = tokenUtils.getUserInfo(request);
            String costText = cost < 3 ? "不足3S" : cost + "S";
            PulOperationRecord operationRecord = new PulOperationRecord(
                    IdUtil.fastSimpleUUID(), title, ServletUtils.getRequest().getRequestURI(),
                    ip, location, userAgent.getBrowser().getName(), userAgent.getOs().getName(),
                    params, JSONUtil.toJsonStr(result), "0", null, userInfo.getUserId(), userInfo.getUsername(),
                    userInfo.getDept().getDeptId(), userInfo.getDept().getDeptName(), LocalDateTime.now(), costText);
            operationRecordProducer.sendOperationInfor(operationRecord);
        }
    }

    /**
     * 获取请求参数
     *
     * @param request
     * @param joinPoint
     * @return
     */
    private String getReqestParams(HttpServletRequest request, JoinPoint joinPoint) {
        String httpMethod = request.getMethod();
        String params = "";
        if (HttpConstant.HTTP_METHOD_POST.equals(httpMethod) || HttpConstant.HTTP_METHOD_PUT.equals(httpMethod)
                || HttpConstant.HTTP_METHOD_PATCH.equals(httpMethod)) {
            Object[] paramsArray = joinPoint.getArgs();
            params = JSONObject.toJSONString(paramsArray);
        } else {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Object[] args = joinPoint.getArgs();
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] paramNames = u.getParameterNames(method);
            if (args != null && paramNames != null) {
                for (int i = 0; i < args.length; i++) {
                    params += "  " + paramNames[i] + ": " + args[i];
                }
            }
        }
        return params;
    }
}
