package com.blog.bolgpostproject.repositories;

import com.blog.bolgpostproject.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);
}
