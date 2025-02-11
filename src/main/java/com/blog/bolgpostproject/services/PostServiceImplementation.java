package com.blog.bolgpostproject.services;

import com.blog.bolgpostproject.entities.Post;
import com.blog.bolgpostproject.entities.Tag;
import com.blog.bolgpostproject.entities.User;
import com.blog.bolgpostproject.repositories.PostRepository;
import com.blog.bolgpostproject.repositories.TagRepository;
import com.blog.bolgpostproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImplementation implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        Post post=postRepository.findById(id).get();
        return post;
    }

    @Override
    public void addPost(Post post, String[] tagList) {
        List<Tag> postTags=new ArrayList<>();

        for(String currentTag:tagList){
            Tag tag=tagRepository.findByName(currentTag);
            if(tag==null){
                tag=new Tag();
                tag.setName(currentTag);
                tag=tagRepository.save(tag);
            }
            postTags.add(tag);
        }
        post.setTags(postTags);
        postRepository.save(post);
    }

    @Override
    public List<Post> getPostsByLoggedInUser(String username) {
        List<Post> posts=postRepository.findByAuthor(username);

        return posts;
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public void savePost(Post existingPost) {
        postRepository.save(existingPost);
    }
}
