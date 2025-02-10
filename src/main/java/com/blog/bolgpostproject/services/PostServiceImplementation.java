package com.blog.bolgpostproject.services;

import com.blog.bolgpostproject.entities.Post;
import com.blog.bolgpostproject.entities.Tag;
import com.blog.bolgpostproject.repositories.PostRepository;
import com.blog.bolgpostproject.repositories.TagPostRepository;
import com.blog.bolgpostproject.repositories.TagRepository;
import com.blog.bolgpostproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.bolgpostproject.entities.User;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class PostServiceImplementation implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    public Post createpost(Post post) {
        if (post.getTags() != null) {
            Set<Tag> managedTags = new HashSet<>();
            for (Tag tag : post.getTags()) {
                // Try to find existing tag
                Tag existingTag = tagRepository.findByName(tag.getName());
                if (existingTag != null) {
                    managedTags.add(existingTag);
                } else {
                    // Create new tag if doesn't exist
                    managedTags.add(tagRepository.save(tag));
                }
            }
            post.setTags(managedTags);
        }
        return postRepository.save(post);
    }

}
