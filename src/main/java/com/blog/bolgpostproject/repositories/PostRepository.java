package com.blog.bolgpostproject.repositories;

import com.blog.bolgpostproject.entities.Post;
import com.blog.bolgpostproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByAuthor(String author);
}
