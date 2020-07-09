package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.repo.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;


@Controller
public class  BlogController {



    @GetMapping("/blog-main")
    public String blogMain(Model model){
        model.addAttribute("title" , "Blog Page");
        return "blog-main";
    }

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blog(Model model){
        List<Post> posts = postRepository.findAll();
        System.out.println(posts.size());
        model.addAttribute("posts", posts);
        return "blog-main";
    }



    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String name, @RequestParam String anons ,
                              @RequestParam String full_text , Model model){
            Post post = new Post(name, anons, full_text);
            postRepository.save(post);
        return "redirect:/blog";
    }


}
