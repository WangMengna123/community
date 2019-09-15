package com.program.community.service;

import com.program.community.dto.PageDto;
import com.program.community.dto.QuestionDto;
import com.program.community.mapper.QuestionMapper;
import com.program.community.mapper.UserMapper;
import com.program.community.model.Question;
import com.program.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PageDto list(Integer pageNum, Integer pageSize) {
        Integer totalCount = questionMapper.countQuestions();
        PageDto pageDto = new PageDto();
        pageDto.setPagination(totalCount,pageNum,pageSize);

        if (pageNum < 1){
            pageNum = 1;
        }
        if (pageNum > pageDto.getTotalPage()){
            pageNum = pageDto.getTotalPage();
        }


        Integer offset = (pageNum - 1) * pageSize ;
        List<Question> questionList = questionMapper.list(offset, pageSize);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for(Question question :  questionList){
            User user =  userMapper.findUserById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        pageDto.setQuestionDtosList(questionDtoList);
        return pageDto;
    }
}
