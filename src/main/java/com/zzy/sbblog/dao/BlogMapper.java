package com.zzy.sbblog.dao;

import com.zzy.sbblog.entity.Blog;
import com.zzy.sbblog.vo.BlogVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {
    List<Blog>  getList();

    List<Blog> getAllBlogVOs();

    List<Blog> findAllBlogs();

    List<Blog> queryBlogsByTypeId(Long typeId);

    Blog queryBlogById(Long blogId);

    Blog queryBlogByBlogName(String blogName);

    Integer addBlog(Blog blog);

    Integer addBlogUser(Blog blog);

    Integer addBlogType(Blog blog);

    List<BlogVO> queryBlogVOs(Blog blogConditon);

    Integer deleteBlogById(Long blogId);

    Integer updateBlog(Blog blog);
}
