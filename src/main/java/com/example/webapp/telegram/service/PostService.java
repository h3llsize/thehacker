package com.example.webapp.telegram.service;

import com.example.webapp.telegram.entities.PostEntity;
import com.example.webapp.telegram.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PostService {

    private static PostRepository postRepository;

    @Autowired
    private PostRepository tPostRepository;

    @PostConstruct
    public void init() {
        postRepository = tPostRepository;
    }

    public static PostEntity getPost(Long Id) throws Exception {
        PostEntity postEntity = null;
        try {
            postEntity = postRepository.findById(Id).get();
            return postEntity;
        }

        catch (Exception e)
        {
            throw new Exception("Invalid post ID");
        }
    }

    public static Iterable<PostEntity> getAllPosts()
    {
        return postRepository.findAll();
    }


    public static void savePost(PostEntity postEntity) {
        postRepository.save(postEntity);
    }
}
