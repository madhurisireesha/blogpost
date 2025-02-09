package com.blog.bolgpostproject.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TagPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="post_id",nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name="tag_id",nullable = false)
    private Tag tag;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TagPost(Long id, Post post, Tag tag, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.post = post;
        this.tag = tag;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TagPost() {
    }

    @Override
    public String toString() {
        return "TagPost{" +
                "id=" + id +
                ", post=" + post +
                ", tag=" + tag +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
