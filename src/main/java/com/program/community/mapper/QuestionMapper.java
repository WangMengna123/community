package com.program.community.mapper;

import com.program.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,tags,gmt_create,gmt_modified,creator) values (#{title},#{description},#{tags},#{gmtCreate},#{gmtModified},#{creator})")
    void createQuestion(Question question);
    @Select("select * from question")
    List<Question> list();
}
