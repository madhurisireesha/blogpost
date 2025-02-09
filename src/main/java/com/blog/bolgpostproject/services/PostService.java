package com.blog.bolgpostproject.services;

import com.blog.bolgpostproject.entities.Post;
import java.util.*;

public interface PostService {

    Post createPost(Post post);

    List<Post> getAllPosts();

    Post getPostById(Long id);
}
