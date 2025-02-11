package com.blog.bolgpostproject.services;

import com.blog.bolgpostproject.entities.Post;

import java.util.List;

public interface PostService {

     void addPost(Post post, String[] tagList);

     List<Post> getAllPosts();

     Post getPostById(Long id);

     List<Post> getPostsByLoggedInUser(String username);

     void deletePost(Long id);

     Post getPost(Long id);

     void savePost(Post existingPost);
}
