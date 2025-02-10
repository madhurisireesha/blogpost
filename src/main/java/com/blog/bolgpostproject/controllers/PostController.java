package com.blog.bolgpostproject.controllers;

import com.blog.bolgpostproject.entities.Post;
import com.blog.bolgpostproject.entities.Tag;
import com.blog.bolgpostproject.repositories.TagRepository;
import com.blog.bolgpostproject.services.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

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

//    @PostMapping("/createpost")
//    public String createpost(@ModelAttribute Post post,@RequestParam(required=false) String tags){
//        if(tags!=null && !tags.isEmpty()){
//            String[] tagNames=tags.split(",");
//            Set<Tag> tagSet=new HashSet<>();
//
//            for(String tagName:tagNames){
//                Tag tag=new Tag();
//                tag.setName(tagName);
//                tagSet.add(tag);
//            }
//            post.setTags(tagSet);
//        }
//        System.out.println("inside method");
//        post.setCreatedAt(LocalDateTime.now());
//        post.setUpdatedAt(LocalDateTime.now());
//
//        postService.createPost(post);
//        return "temp";
//    }

    @PostMapping("/createpost")
    public String createpost(@RequestParam Map<String, String> allParams, @RequestParam(required = false) String tags,HttpSession session) {
        Post post = new Post();
        String username=(String)session.getAttribute("username");
        post.setTitle(allParams.get("title"));
        post.setExcerpt(allParams.get("excerpt"));
        post.setContent(allParams.get("content"));
        post.setAuthor(username);
        // Set other fields from allParams

        if (tags != null && !tags.isEmpty()) {
            String[] tagNames = tags.split(",");
            Set<Tag> tagSet = new HashSet<>();

            for (String tagName : tagNames) {
                Tag tag = new Tag();
                tag.setName(tagName.trim());

                Tag existingTag = tagRepository.findByName(tagName.trim());
                if (existingTag != null) {
                    tagSet.add(existingTag);
                } else {
                    tagRepository.save(tag);
                    tagSet.add(tag);
                }
            }
            post.setTags(tagSet);
        }

        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        post.setPublishedAt(LocalDateTime.now());
        postService.createpost(post);

        return "temp";
    }


}
