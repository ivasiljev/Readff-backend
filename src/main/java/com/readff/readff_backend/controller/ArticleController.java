package com.readff.readff_backend.controller;

import com.readff.readff_backend.entity.Article;
import com.readff.readff_backend.repository.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    @PostMapping
    public Article postArticles(@RequestBody Article article) {
        return articleRepository.save(article);
    }
}
