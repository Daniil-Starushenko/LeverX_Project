package com.leverx.blog.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comment")
@Data
@EqualsAndHashCode
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "message")
    private String message;

    @JoinColumn(name = "author_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private User user;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Article article;

    @Column(name = "created_at")
    private LocalDate created;

    @PrePersist
    public void setCreated() {
        this.created = LocalDate.now();
    }
}
