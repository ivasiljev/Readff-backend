package com.readff.readff_backend.controller;

import com.readff.readff_backend.entity.Article;
import com.readff.readff_backend.repository.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private ArticleRepository articleRepository;

    
}
