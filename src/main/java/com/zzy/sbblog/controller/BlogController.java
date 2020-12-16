package com.zzy.sbblog.controller;

import com.zzy.sbblog.entity.Blog;
import com.zzy.sbblog.entity.ResponseBean;
import com.zzy.sbblog.entity.Type;
import com.zzy.sbblog.exception.CommonException;
import com.zzy.sbblog.service.BlogService;
import com.zzy.sbblog.service.TypeService;
import com.zzy.sbblog.vo.BlogArchieveVO;
import com.zzy.sbblog.vo.BlogVO;
import com.zzy.sbblog.vo.TypesAndTagsVo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */

@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Resource
    private BlogService blogService;

    @Resource
    private TypeService typeService;

    @PostMapping("/getAllBlogVOs")
    public ResponseBean getAllBlogVOs() {
        ResponseBean result;

        List<Blog> blogs = blogService.getAllBlogVOs();
        result = new ResponseBean("001", "成功", blogs);
        return result;
    }


    @PostMapping("/findAllBlogs")
    public ResponseBean findAllBlogs() {
        ResponseBean responseBean;
        List<Blog> blogs = blogService.findAllBlogs();
        responseBean = new ResponseBean("001", "success", blogs);
        return responseBean;
    }

    @GetMapping("/findAllBlogsByYear")
    public ResponseBean findAllBlogsByYear() {
        ResponseBean responseBean;
        List<BlogArchieveVO> blogArchieveVOS = blogService.findAllBlogsByYear();
        responseBean = new ResponseBean("001", "success", blogArchieveVOS);
        return responseBean;
    }

    @GetMapping("/queryBlogsByTypeId")
    public ResponseBean queryBlogsByTypeId(@RequestParam("typeId") Long typeId) {
        ResponseBean responseBean;
        List<Blog> blogs = blogService.queryBlogsByTypeId(typeId);
        responseBean = new ResponseBean("001", "success", blogs);
        return responseBean;
    }

    @PostMapping("/queryBlogVOsByCondition")
    public ResponseBean queryBlogVOsByCondition(@RequestBody Blog blogCondition) {
        ResponseBean responseBean;
        List<BlogVO> blogs = blogService.queryBlogVOs(blogCondition);
        responseBean = new ResponseBean("001", "success", blogs);
        return responseBean;

    }

    @GetMapping("/queryBlogById")
    public ResponseBean queryBlogById(@RequestParam("blogId") Long blogId,
                                      @RequestParam(value = "blogName",required = false) String blogName) {
        ResponseBean responseBean = null;
        Blog blogTOBrowser = blogService.queryBlogById(blogId);
        blogTOBrowser.setViews((Long.parseLong(blogTOBrowser.getViews()) + 1) + "");

        blogService.updateBlog(blogTOBrowser);
        responseBean = new ResponseBean("001", "success", blogTOBrowser);
        return responseBean;
    }

    @PostMapping("/addBlog")
    public ResponseBean addBlog(@RequestBody Blog blog) {
        ResponseBean responseBean;
        blog.setUpdateTime(new Date());
        if (blog.getId() != null && blog.getId() != -1) {
            /*更新操作*/
            int rows = blogService.updateBlog(blog);
            if (rows == 1) {
                responseBean = new ResponseBean("001", "success", blog);
            } else {
                responseBean = new ResponseBean("000", "failed", blog);
            }
        } else {
            /*新增操作——1、title不重复 2、重复*/
            /*查询所有的title，判断是否存在：若存在，*/
            Integer rows = blogService.addNewBlog(blog);
            if (rows != 1) {
                responseBean = new ResponseBean("000", "failed", blog);
            }
            responseBean = new ResponseBean("001", "success", blog);
        }
        return responseBean;
    }

    @GetMapping("/findAllTypesAndTags")
    public ResponseBean findAllTypesAndTags(){
        ResponseBean responseBean;
        /*
        * 1、调用typeMapper，查询types
        * 2、调用tagsMapper，查询tags
        * 3、ArrayList，放入types、tags，
        * */
        List<Type> typeList = typeService.queryAllTypes();
        if(typeList == null){
            throw new CommonException("000","没有[类别]或者[类别]错误！");
        }
        TypesAndTagsVo typesAndTags = new TypesAndTagsVo(typeList);
        responseBean = new ResponseBean("001","success",typesAndTags);
        return responseBean;



    }

    @GetMapping("/deleteBlogById")
    public ResponseBean deleteBlogById(@RequestParam("blogId") Long blogId) {
        ResponseBean responseBean;
        Blog blog = blogService.queryBlogById(blogId);
        Integer rows = blogService.deleteBlogById(blogId);
        if (rows != 1) {
            responseBean = new ResponseBean("000", "failed", blog);
        } else {
            responseBean = new ResponseBean("001", "success", blog);
        }
        return responseBean;

    }


}
