package com.xjtu.hoooo.mapper;

import com.xjtu.hoooo.dao.News;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper {
    int insertNews(News news);
}
