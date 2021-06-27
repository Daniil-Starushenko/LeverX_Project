package com.leverx.blog.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "article")
@Data
@EqualsAndHashCode
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "author_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "created_at")
    private LocalDate creationDate;

    @Column(name = "changed_at")
    private LocalDate changingDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "article")
    private Set<Comment> comments;

    @ManyToMany
    @JoinTable(name = "each_tag",
        joinColumns = @JoinColumn(name = "article_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @PrePersist
    public void setCreationDate() {
        this.creationDate = LocalDate.now();
    }

    @PreUpdate
    public void setChangingDate() {
        this.changingDate = LocalDate.now();
    }
}
