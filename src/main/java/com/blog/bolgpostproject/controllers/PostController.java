package com.blog.bolgpostproject.controllers;

import com.blog.bolgpostproject.entities.Post;

import com.blog.bolgpostproject.repositories.TagRepository;
import com.blog.bolgpostproject.services.PostService;
import com.blog.bolgpostproject.services.TagServiceImplementation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private TagServiceImplementation tagService;

    @Autowired
    TagRepository tagRepository;

    @GetMapping("/")
    public String displayLoginForm(){
        return "login";
    }

    @GetMapping("/new")
    public String displayPostForm(HttpSession session){
        String username=(String)session.getAttribute("username");
        if(username!=null) {
            return "createpost";
        }
        return "login";
    }


    @PostMapping("/createpost")
    public String createpost(@Valid @ModelAttribute Post post,BindingResult bindingResult, @RequestParam String tags,HttpSession session){
//        String username=(String)session.getAttribute("username");
//        post.setAuthor(username);
        post.setPublishedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        post.setCreatedAt(LocalDateTime.now());
        String[] tagList=tags.split(",");
        postService.addPost(post,tagList);
        return "redirect:/posts";
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<Post>  posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/myPosts")
    public String getMyPosts(Model model,HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            List<Post> myposts = postService.getPostsByLoggedInUser(username);
            model.addAttribute("myposts", myposts);
            model.addAttribute("currentuser",username);
        }
        return "myposts";
    }
    @GetMapping("/posts/{id}")
    public String viewSinglePost(@PathVariable Long id,Model model){
        Post post=postService.getPostById(id);
        model.addAttribute("post",post);
        return "displaysinglepost";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable Long id,Model model){
        Post post=postService.getPost(id);
        model.addAttribute("post",post);
        return "editpost";
    }

    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable Long id,@ModelAttribute Post updatedPost){
        Post existingPost=postService.getPostById(id);

        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setExcerpt(updatedPost.getExcerpt());
        existingPost.setContent(updatedPost.getContent());

        postService.savePost(existingPost);

        return "redirect:/myPosts";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return "redirect:/myPosts";
    }

}
