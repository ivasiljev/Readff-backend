package com.readff.readff_backend.repository;

import com.readff.readff_backend.entity.Article;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    
}
