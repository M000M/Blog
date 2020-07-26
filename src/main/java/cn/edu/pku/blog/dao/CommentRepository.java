package cn.edu.pku.blog.dao;

import cn.edu.pku.blog.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //List<Comment> findByBlogIdAndParentCommentNull(Long BlogId, Sort sort);
    List<Comment> findCommentListByBlogIdAndParentCommentIsNull(Long BlogId, Sort sort);
}
