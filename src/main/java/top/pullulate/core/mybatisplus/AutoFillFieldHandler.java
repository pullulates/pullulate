package top.pullulate.core.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import top.pullulate.core.utils.TokenUtils;

import java.time.LocalDateTime;

/**
 * @功能描述:   mybatis 字段自动填充
 * @Author: xuyong
 * @Date: 2020/8/26 10:02
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AutoFillFieldHandler implements MetaObjectHandler {

    private final TokenUtils tokenUtils;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("新增自动填充创建人及创建时间...");
        this.fillStrategy(metaObject, "createAt", LocalDateTime.now());
        this.fillStrategy(metaObject, "createBy", tokenUtils.getUserName());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新自动填充更新人及更新时间...");
        this.fillStrategy(metaObject, "updateAt", LocalDateTime.now());
        this.fillStrategy(metaObject, "updateBy", tokenUtils.getUserName());
    }
}
