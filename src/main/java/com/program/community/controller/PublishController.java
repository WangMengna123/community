package com.program.community.controller;

import com.program.community.mapper.QuestionMapper;
import com.program.community.mapper.UserMapper;
import com.program.community.model.Question;
import com.program.community.model.User;
import com.program.community.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tags",required = false) String tags,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tags",tags);
        if (!StringUtils.isNotNullOrEmpty(title)){
            model.addAttribute("error","标题不能为空！");
            return "publish";
        }
        if (!StringUtils.isNotNullOrEmpty(description)){
            model.addAttribute("error","问题补充不能为空！");
            return "publish";
        }
        if (!StringUtils.isNotNullOrEmpty(tags)){
            model.addAttribute("error","标签不能为空！");
            return "publish";
        }
        User user = null;
        Cookie[] cookies = request.getCookies();
        String token ;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")){
                token = cookie.getValue();
                user = userMapper.findUserByToken(token);
                if (user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        if (user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTags(tags);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(System.currentTimeMillis());
        question.setCreator(user.getId());
        questionMapper.createQuestion(question);
        return "redirect:/";
    }
}
