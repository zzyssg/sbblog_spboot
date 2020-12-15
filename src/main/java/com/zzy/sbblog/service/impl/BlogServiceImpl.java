package com.zzy.sbblog.service.impl;

import com.zzy.sbblog.dao.BlogMapper;
import com.zzy.sbblog.dao.UserMapper;
import com.zzy.sbblog.entity.Blog;
import com.zzy.sbblog.entity.Comment;
import com.zzy.sbblog.entity.Type;
import com.zzy.sbblog.entity.User;
import com.zzy.sbblog.service.BlogService;
import com.zzy.sbblog.service.UserService;
import com.zzy.sbblog.vo.BlogArchieveVO;
import com.zzy.sbblog.vo.BlogVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BlogServiceImpl implements BlogService {

    private final UserService userService;
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
        Blog blog = blogMapper.queryBlogById(blodId);
        //从blog中取出comments
        List<Comment> resultComments = new ArrayList<>();
        List<Comment> comments = blog.getComments();
        if (!comments.isEmpty()) {
            //去除特殊情况：当blog中没有comment时，仍能查出来一条，但是其comment中的成员变量，除了blogId以外均为空
            //——因此去除这种特殊情况
            if (comments.size() == 1 && comments.get(0).getContent() == null) {
                comments.remove(0);
            }
            //此处处理一下comments
            //TODO 首先为comments赋user
            for (Comment comment : comments) {
                Integer commentUserId = comment.getUserId();
                User curUser = userService.getUserById(commentUserId);
                comment.setUser(curUser);
            }
            //找到各自的parentComment——设置父级评论昵称
            for (int i = 0; i < comments.size(); i++) {
                Comment sonComment = comments.get(i);
                if (sonComment.getParentCommentId() == null) {
                    continue;
                }
                for (int j = 0; j < comments.size(); j++) {
                    Comment fatherComment = comments.get(j);
                    if (sonComment.getParentCommentId().equals(fatherComment.getCommentId())) {
                        User parentCommentUser = User.builder()
                                .nickname(fatherComment.getUser().getNickname())
                                .avatar(fatherComment.getUser().getAvatar())
                                .build();
                        sonComment.setParentCommentUser(parentCommentUser);
                    }
                }
            }
            Map<Comment, List<Comment>> temComments = new HashMap<>();
            //先筛选出根评论，并且将根评论的id映射出一个map，key为根评论id，value为其子孙评论id
            for (Comment comment : comments) {
                if (comment.getParentCommentId() == null) {
                    //根评论
                    temComments.put(comment, new ArrayList<>());
                }
            }
            //遍历剩余评论，如果其parentId即为key的id或者 是key对应的value的id，将其扔进key对应的value里
            for (Comment comment : comments) {
                Integer curCommentParentId = comment.getParentCommentId();
                for (Map.Entry<Comment, List<Comment>> entry : temComments.entrySet()) {
                    Comment rootComment = entry.getKey();
                    List<Comment> subComments = entry.getValue();
                    Boolean isSubComment = curCommentParentId != null
                            && (curCommentParentId.equals(rootComment.getCommentId())
                            || hasParent(subComments,comment));
                    if (isSubComment) {
                        temComments.get(rootComment).add(comment);
                    }
                }
            }
            //处理一下temComments  key--comment有一个blogId
            //将key对应的value设置到replements
            for (Map.Entry<Comment, List<Comment>> entry : temComments.entrySet()) {
                Comment comment = entry.getKey();
                comment.setReplyComments(entry.getValue());
                resultComments.add(comment);
            }
            blog.setComments(resultComments);
        }
        return blog;
    }

    /**
     *如果子评论中有其直接父评论，也加入进此根评论的子评论中
     * @param subComments
     * @param comment
     * @return
     */
    private boolean hasParent(List<Comment> subComments, Comment comment) {
        for (Comment subComment : subComments) {
            if (subComment.getCommentId().equals(comment.getParentCommentId())) {
                return true;
            }
        }
        return false;
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

    /**
     *
     * 调用 addBlog 、 addBlogUser 、addBlogType
     */
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
