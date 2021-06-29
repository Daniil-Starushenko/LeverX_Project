package com.leverx.blog.repository.mysql;

import com.leverx.blog.model.entity.Tag;
import com.leverx.blog.model.entity.TagValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Tag getTagByTagValue(TagValue tagValue);

}
