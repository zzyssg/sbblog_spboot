package com.zzy.sbblog.vo;

import com.zzy.sbblog.entity.Blog;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
@Data
@ToString
public class BlogArchieveVO {

    private Integer curYear;
    private List<Blog> blogsOfYear;

    public BlogArchieveVO() {
    }

    public BlogArchieveVO(Integer curYear, List<Blog> blogsOfYear) {
        this.curYear = curYear;
        this.blogsOfYear = blogsOfYear;
    }
}
