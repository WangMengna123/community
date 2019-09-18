package com.program.community.mapper;

import com.program.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,tags,gmt_create,gmt_modified,creator) values (#{title},#{description},#{tags},#{gmtCreate},#{gmtModified},#{creator})")
    void createQuestion(Question question);
    @Select("select * from question limit #{offset} , #{pageSize}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize);
    @Select("select count(1) from question")
    Integer countQuestions();
    @Select("select * from question where creator = #{id} limit #{offset} , #{pageSize}")
    List<Question> searchQuestionsByUserId(@Param("id") Integer id,@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);
    @Select("select count(1) from question where creator = #{id}")
    Integer countQuestionsOfUserId(@Param("id") Integer id);
}
