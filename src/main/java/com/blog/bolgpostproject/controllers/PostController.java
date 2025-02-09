package com.blog.bolgpostproject.controllers;

import com.blog.bolgpostproject.entities.Post;
import com.blog.bolgpostproject.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.*;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/new")
    public String displayPostForm(){
        return "createpost";
    }

    @PostMapping("/createpost")
    public String createPost(@ModelAttribute Post post){
        postService.createPost(post);
        return "redirect:/posts";
    }

    @GetMapping("/createpost")
    public String showCreatePostForm(Model model) {
        Post post=new Post();
        model.addAttribute("post",post);
        return "createpost";
    }


    @GetMapping("/posts")
    public String displayAllPosts(Model model){
        List<Post> posts=postService.getAllPosts();
        model.addAttribute("posts",posts);
        return "posts";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable Long id,Model model){
        Post post=postService.getPostById(id);
        return "temp";
    }


}
