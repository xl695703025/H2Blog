package com.yuxia.blog.service;

import java.util.List;

import com.yuxia.blog.entity.Tag;

public interface TagService {

	public List<Tag> getTagList(String userName);
	public int delTagById(Integer tagId);
	public int addTag(String userName,String tagName,String tagDescription);
	public int updateTag(Integer tagId,String tagName, String tagDescription);
	public Tag getTagByName(String userName,String tagName);
	public List<Tag> getHotTags(String userName);
}
