package top.pullulate.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @功能描述:   操作日志实体类
 * @Author: xuyong
 * @Date: 2020/7/4 17:14
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PulOperationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    /** 日志主键 */
    private String recordId;

    /** 日志名称 */
    private String title;

    /** 请求路径 */
    private String path;

    /** 请求ip */
    private String ip;

    /** 请求地址 */
    private String location;

    /** 浏览器 */
    private String browser;

    /** 终端类型 */
    private String os;

    /** 请求参数 */
    private String requestParam;

    /** 响应数据 */
    private String responseData;

    /** 是否发生异常：0-正常；1-异常 */
    private String hasOccurException;

    /** 异常信息 */
    private String exceptionInfo;

    /** 用户主键 */
    private String userId;

    /** 用户名称 */
    private String userName;

    /** 部门主键 */
    private String deptId;

    /** 部门名称 */
    private String deptName;

    /** 操作时间 */
    private LocalDateTime operateTime;

    /** 操作耗时 */
    private String costTime;
}
