package top.pullulate.web.data.dto.tree;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @功能描述:   树结构数据传输对象
 * @Author: xuyong
 * @Date: 2020/7/14 14:53
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Getter
@Setter
public class Tree {

    private String title;

    private String value;

    private String key;

    private List<Tree> children;

    public Tree(String title, String value, String key) {
        this.title = title;
        this.value = value;
        this.key = key;
    }
}
