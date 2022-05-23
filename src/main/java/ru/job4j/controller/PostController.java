package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Post;
import ru.job4j.service.PostService;
import ru.job4j.service.UserService;

@Controller
public class PostController {

    private final PostService postService;

    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "index";
    }

    @GetMapping("/postInfo/{id}")
    public String detailsPost(Model model, @PathVariable("id") int id) {
        model.addAttribute("post", postService.findById(id));
        return "postInfo";
    }

    @GetMapping("/edit/{id}")
    public String editPost(Model model, @PathVariable("id") int id) {
        model.addAttribute("post", postService.findById(id));
        return "edit";
    }

    @GetMapping("/addPost")
    public String newPost() {
        return "addPost";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/index";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/index";
    }

    @GetMapping("deletePost/{postId}")
    public String delete(@PathVariable("postId") int id) {
        postService.deleteById(id);
        return "redirect:/index";
    }

}
