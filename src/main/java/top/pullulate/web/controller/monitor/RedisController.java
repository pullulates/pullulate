package top.pullulate.web.controller.monitor;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pullulate.core.utils.RedisUtils;
import top.pullulate.web.data.dto.response.P;

/**
 * @功能描述:   redis监控前端控制器
 * @Author: xuyong
 * @Date: 2020/7/22 13:20
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/monitor/redis")
public class RedisController {

    private final RedisUtils redisUtils;

    /**
     * 获取redis key的数量
     *
     * @return
     */
    @GetMapping("/keys")
    public P getRedisKeys() {
        long keys = redisUtils.keys();
        return P.data(keys);
    }

    /**
     * 获取已使用内存
     *
     * @return
     */
    @GetMapping("/used-memory")
    public P getUsedMemoryInfo() {
        String usedMemory = redisUtils.getUsedMemoryInfo();
        return P.data(usedMemory);
    }
}
