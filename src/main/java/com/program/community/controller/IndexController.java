package com.program.community.controller;

import com.program.community.dto.PageDto;
import com.program.community.mapper.UserMapper;
import com.program.community.model.User;
import com.program.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "pageSize",defaultValue = "2") Integer pageSize){
        Cookie[] cookies = request.getCookies();
        String token ;
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    User user = userMapper.findUserByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        PageDto pageDto = questionService.list(pageNum,pageSize);
        model.addAttribute("pageDto",pageDto);


        return "index";
    }
}
