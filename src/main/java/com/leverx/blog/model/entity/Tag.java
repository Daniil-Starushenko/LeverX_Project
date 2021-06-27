package com.leverx.blog.model.entity;

import lombok.AllArgsConstructor;
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
    @Enumerated(EnumType.STRING)
    private TagValue tagValue;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    private Set<Article> articles;
}
