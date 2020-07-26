package cn.edu.pku.blog.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_comment")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String nickName;
    private String email;
    private String content;
    private String avatar;

    @Temporal(TemporalType.TIMESTAMP) //生成数据库表中对应的类型
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne
    private Blog blog;

    @OneToMany(mappedBy = "parentComment")  //从父类对象来考虑，一个父类Comment对应多个子类Comment
    private List<Comment> replayComments = new ArrayList<>();

    @ManyToOne
    private Comment parentComment;

    private boolean isAdminComment;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Comment> getReplayComments() {
        return replayComments;
    }

    public void setReplayComments(List<Comment> replayComments) {
        this.replayComments = replayComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public boolean isAdminComment() {
        return isAdminComment;
    }

    public void setAdminComment(boolean adminComment) {
        isAdminComment = adminComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", blog=" + blog +
                ", replayComments=" + replayComments +
                ", parentComment=" + parentComment +
                ", isAdminComment=" + isAdminComment +
                '}';
    }
}
