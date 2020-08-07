package top.pullulate.core.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.pullulate.common.service.DeptCacheService;
import top.pullulate.system.service.IPulDeptService;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @功能描述:   项目启动时，缓存部门信息
 * @Author: xuyong
 * @Date: 2020/7/9 15:38
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CacheDept {

    private final IPulDeptService deptService;

    private final DeptCacheService deptCacheService;

    @PostConstruct
    public void init() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 启动缓存部门信息任务");
        log.info(">>>>>>>>>>>>>>>> 销毁部门缓存信息");
        deptCacheService.deleteAllDepts();
        deptCacheService.deleteDeptListTree();
        deptCacheService.deleteDeptTreeSelect();
        log.info(">>>>>>>>>>>>>>>> 缓存所有部门信息");
        deptService.getAllDepts();
        log.info(">>>>>>>>>>>>>>>> 缓存前端部门列表树信息");
        deptService.getDeptTreeList();
        log.info(">>>>>>>>>>>>>>>> 缓存前端部门选择树信息");
        deptService.getTreeSelect();
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 缓存系统部门任务结束");
    }

    @PreDestroy
    public void destroy() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 启动销毁部门缓存任务");
        log.info(">>>>>>>>>>>>>>>> 销毁部门信息缓存");
        deptCacheService.deleteAllDepts();
        log.info(">>>>>>>>>>>>>>>> 销毁前端部门列表树信息");
        deptCacheService.deleteDeptListTree();
        log.info(">>>>>>>>>>>>>>>> 销毁前端部门选择树信息");
        deptCacheService.deleteDeptTreeSelect();
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 销毁部门缓存任务结束");
    }

}
