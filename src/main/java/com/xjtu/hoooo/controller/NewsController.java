package com.xjtu.hoooo.controller;

import com.xjtu.hoooo.dao.News;
import com.xjtu.hoooo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @PostMapping("/insert_news")
    public int insertNews(@RequestBody News news) {
        return newsService.insertNews(news);
    }
}
