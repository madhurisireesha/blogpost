package com.blog.bolgpostproject.controllers;

import com.blog.bolgpostproject.entities.User;
import com.blog.bolgpostproject.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/userValidation")
    public String validateUser(@RequestParam("email") String username, @RequestParam("password") String password, Model model, HttpSession session){
        User user=userService.validateUser(username,password);
        if(user!=null){
            System.out.println("valid user");
            session.setAttribute("username",user.getName());
            System.out.println(session.getAttribute("username"));
            return "posts";
        }
        System.out.println("Invalid user");
        return "temp";
    }

}
