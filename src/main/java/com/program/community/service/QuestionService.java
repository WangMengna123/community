package com.program.community.service;

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

    public List<QuestionDto> list() {
        List<Question> questionList = questionMapper.list();
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for(Question question :  questionList){
            User user =  userMapper.findUserById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }
}
