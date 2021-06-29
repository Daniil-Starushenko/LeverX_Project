package com.leverx.blog.service.impl;

import com.leverx.blog.exception.entity.EntityNotFoundException;
import com.leverx.blog.model.entity.Tag;
import com.leverx.blog.model.entity.TagValue;
import com.leverx.blog.repository.mysql.TagRepository;
import com.leverx.blog.service.TagService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    @Override
    public Tag saveTag(Tag tag) {
        log.info("save Tag");
        return tagRepository.save(tag);
    }

    @Override
    @Transactional(readOnly = true)
    public Tag getByTagValue(TagValue tagValue) {
        log.info("find tag by tag value: {}", tagValue.name());
        return tagRepository.getTagByTagValue(tagValue);
    }

}
