package com.xjtu.hoooo.utils.crawlers;

import com.xjtu.hoooo.dao.News;

import java.util.List;

public interface Spider {
    default List<News> crawl() {
        throw new UnsupportedOperationException("Crawl method not implemented.");
    };
    default List<News> crawl(String url) {
        throw new UnsupportedOperationException("Crawl method with URL not implemented.");
    };
}
