package com.blog.bolgpostproject.repositories;

import com.blog.bolgpostproject.entities.Post;
import com.blog.bolgpostproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailAndPassword(String email, String password);

    User findByEmail(String username);
}
