package com.zzy.sbblog.service;

import com.zzy.sbblog.entity.Blog;
import com.zzy.sbblog.vo.BlogArchieveVO;
import com.zzy.sbblog.vo.BlogVO;

import java.util.List;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
public interface BlogService {

    List<Blog> getList();

    List<Blog> findAllBlogs();

    List<Blog> getAllBlogVOs();

    List<Blog> queryBlogsByTypeId(Long typeId);

    List<BlogArchieveVO> findAllBlogsByYear();

    Blog queryBlogById(Long blodId);

    Integer addBlog(Blog blog);

    Integer addBlogUser(Blog blog);

    Integer addBlogType(Blog blog);

    Integer addNewBlog(Blog blog);

    List<BlogVO> queryBlogVOs(Blog blogCondition);

    Integer deleteBlogById(Long blogId);

    Integer updateBlog(Blog blog);
}
