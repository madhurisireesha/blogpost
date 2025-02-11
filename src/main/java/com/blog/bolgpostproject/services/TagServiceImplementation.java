package com.blog.bolgpostproject.services;

import com.blog.bolgpostproject.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImplementation implements TagService{
    @Autowired
    TagRepository tagRepository;
}
