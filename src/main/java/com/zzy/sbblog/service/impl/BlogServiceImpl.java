package com.zzy.sbblog.service.impl;

import com.zzy.sbblog.dao.BlogMapper;
import com.zzy.sbblog.entity.Blog;
import com.zzy.sbblog.entity.Type;
import com.zzy.sbblog.entity.User;
import com.zzy.sbblog.service.BlogService;
import com.zzy.sbblog.vo.BlogArchieveVO;
import com.zzy.sbblog.vo.BlogVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    private Blog newBLog = new Blog();

    @Resource
    private BlogMapper blogMapper;

    @Override
    public List<Blog> getList() {
        return blogMapper.getList();
    }

    @Override
    public List<Blog> findAllBlogs() {
        return blogMapper.findAllBlogs();
    }

    @Override
    public List<Blog> getAllBlogVOs() {
        return blogMapper.getAllBlogVOs();
    }

    @Override
    public List<Blog> queryBlogsByTypeId(Long typeId) {
        return blogMapper.queryBlogsByTypeId(typeId);
    }

    @Override
    public List<BlogArchieveVO> findAllBlogsByYear() {
        List<BlogArchieveVO> result = new ArrayList<>();
        List<Blog> allBlog = findAllBlogs();
        //按照年份划分blog
        Map<Integer, ArrayList<Blog>> blogMap = new TreeMap<>();
            Calendar c = Calendar.getInstance();
        for(Blog blog : allBlog){
        // 得到每个博客的年份
            c.setTime(blog.getUpdateTime());
            Integer curYear = c.get(Calendar.YEAR);
            if (!blogMap.containsKey(curYear.intValue())) {
                ArrayList<Blog> blogListOfYear = new ArrayList<>();
                blogListOfYear.add(blog);
                blogMap.put(curYear, blogListOfYear);
            } else {
                blogMap.get(curYear.intValue()).add(blog);
            }

        }
        // 遍历map，将map转为 list ——key为curYear，value 为blogList
        for(Integer year : blogMap.keySet()){
            BlogArchieveVO blogArchieveVO = new BlogArchieveVO(year,blogMap.get(year));
            result.add(blogArchieveVO);
        }
        return result;

    }

    @Override
    public Blog queryBlogById(Long blodId) {
        /*先根据blogId更新博客，然后再查询*/

        return blogMapper.queryBlogById(blodId);
    }

    @Override
    public Integer addBlog(Blog blog) {
        /*addBlog 、addBlogUser 、 addBlogType */
        Blog blogToDao = new Blog();
        BeanUtils.copyProperties(blog, blogToDao);
        blogToDao.setCreateTime(new Date());
        blogToDao.setUpdateTime(new Date());
        Type type = new Type();
        type.setId(1L);
        type.setName("日常");
        blogToDao.setTypeId(1);
        blogToDao.setType(type);
        User user = new User();
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setId(1L);
        user.setNickname("海绵爱上星");
        blogToDao.setUserId(1);
        blogToDao.setUser(user);
        Integer effectedRows = blogMapper.addBlog(blogToDao);
        newBLog = blogToDao;
        return effectedRows;
    }

    @Override
    public Integer addBlogUser(Blog blog) {
        return blogMapper.addBlogUser(blog);
    }

    @Override
    public Integer addBlogType(Blog blog) {
        return blogMapper.addBlogType(blog);
    }

    /*调用 addBlog 、 addBlogUser 、addBlogType*/
    @Override
    public Integer addNewBlog(Blog blog) {
        /*添加到 t_blog 表*/
        Integer rowsBlog = addBlog(blog);

        /*添加到 blog_type 表*/
        Integer rowsBlogType = addBlogType(newBLog);

        /*添加到 blog_user 表*/
        Integer rowsBlogUser = addBlogUser(newBLog);

        return 1;
    }

    @Override
    public List<BlogVO> queryBlogVOs(Blog blogCondition) {
        return blogMapper.queryBlogVOs(blogCondition);
    }

    @Override
    public Integer deleteBlogById(Long blogId) {
        return blogMapper.deleteBlogById(blogId);
    }

    @Override
    public Integer updateBlog(Blog blog) {
        return blogMapper.updateBlog(blog);
    }


}
