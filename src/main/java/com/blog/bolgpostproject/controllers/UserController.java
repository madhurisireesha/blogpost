package com.blog.bolgpostproject.controllers;

import com.blog.bolgpostproject.entities.Post;
import com.blog.bolgpostproject.entities.User;
import com.blog.bolgpostproject.services.PostServiceImplementation;
import com.blog.bolgpostproject.services.UserServiceImplementation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserServiceImplementation userService;
    @Autowired
    PostServiceImplementation postService;

    @GetMapping("/userValidation")
    public String validateUser(@RequestParam("email") String username, @RequestParam("password") String password, Model model, HttpSession session){
        User user=userService.validateUser(username,password);
        if(user!=null){
            System.out.println("valid user");
            session.setAttribute("username",user.getName());
            System.out.println(session.getAttribute("username"));
            return "redirect:/posts";
        }
        System.out.println("Invalid user");
        return "temp";
    }

//    @GetMapping("/myposts")
//    public String getMyPosts(HttpSession session,Model model){
//        String loginUser=(String)session.getAttribute("username");
//        System.out.println("IN MYPOSTS "+loginUser);
//        List<Post> posts=postService.getPostByLoginUser(loginUser);
//        System.out.println(posts);
//        model.addAttribute("posts",posts);
//        return "myposts";
//    }

}
