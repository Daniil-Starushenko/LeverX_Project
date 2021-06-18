package com.leverx.blog.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "article")
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "created_at")
    private LocalDate creationDate;

    @Column(name = "changed_at")
    private LocalDate changingDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "article")
    private Set<Comment> comments;

    @ManyToMany()
    @JoinTable(name = "each_tag",
        joinColumns = @JoinColumn(name = "article_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @PrePersist
    public void setCreationDate() {
        this.creationDate = LocalDate.now();
    }

    @PreUpdate
    public void setChangingDate() {
        this.changingDate = LocalDate.now();
    }
}
