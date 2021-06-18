package com.leverx.blog.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tag")
@Data
public class Tag {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value")
    private TagValue tagValue;

    @ManyToMany(mappedBy = "tags")
    private Set<Article> articles;
}
