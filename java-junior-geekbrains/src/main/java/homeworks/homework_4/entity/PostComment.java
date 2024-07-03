package homeworks.homework_4.entity;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "post_comment")
public class PostComment {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "post_comment_date")
    private java.sql.Timestamp postCommentTimestamp;

    public PostComment() {
        this.postCommentTimestamp = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Timestamp getPostCommentTimestamp() {
        return postCommentTimestamp;
    }

    public void setPostCommentTimestamp(Timestamp postCommentTimestamp) {
        this.postCommentTimestamp = postCommentTimestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PostComment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", postCommentTimestamp=" + postCommentTimestamp +
                '}';
    }
}
