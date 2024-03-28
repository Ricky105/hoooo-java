package com.xjtu.hoooo.service.impl;

import com.xjtu.hoooo.dao.News;
import com.xjtu.hoooo.mapper.NewsMapper;
import com.xjtu.hoooo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public int insertNews(News news) {
        return newsMapper.insertNews(news);
    }

    @Override
    public int updateNews() {
//        TODO
        return 0;
    }
}
