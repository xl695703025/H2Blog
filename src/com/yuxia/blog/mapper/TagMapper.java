package com.yuxia.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxia.blog.entity.Tag;

public interface TagMapper {

	public List<Tag> selectTagUserName(@Param("userName")String userName);
	
	public int delTagById(@Param("tagId")Integer tagId);
	public int addTag(@Param("userName")String userName,@Param("tagName")String tagName,@Param("tagDescription")String tagDescription);
	public Tag selectTagByTagName(@Param("userName")String userName,@Param("tagName")String tagName);
	public Tag selectTagByTagId(Integer tagId);
	public int updateTagByTagId(Tag tag);
	public int insertTag(Tag tag);
	public List<Tag> selectHotTags(String userName);
}
