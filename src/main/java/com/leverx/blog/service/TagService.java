package com.leverx.blog.service;

import com.leverx.blog.model.entity.Tag;
import com.leverx.blog.model.entity.TagValue;

public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTagByTagValue(TagValue tagValue);

}
