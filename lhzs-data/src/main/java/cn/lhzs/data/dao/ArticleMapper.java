package cn.lhzs.data.dao;

import cn.lhzs.data.base.Mapper;
import cn.lhzs.data.bean.Article;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {

    List<Article> getArticleList(Article article);

    int getArticleCount(Article article);
}