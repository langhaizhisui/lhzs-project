package cn.lhzs.service.impl;

import cn.lhzs.base.AbstractBaseService;
import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.data.bean.Article;
import cn.lhzs.common.constant.ArticleTypeEnum;
import cn.lhzs.data.dao.ArticleMapper;
import cn.lhzs.service.intf.ArticleService;
import cn.lhzs.common.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorFailResult;
import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;
import static com.github.pagehelper.PageHelper.startPage;
import static java.util.stream.Collectors.toList;

/**
 * Created by ZHX on 2017/11/10.
 */
@CacheConfig(cacheNames = "Article")
@Service
@Transactional
public class ArticleServiceImpl extends AbstractBaseService<Article> implements ArticleService {

    Logger logger = Logger.getLogger(ArticleServiceImpl.class);

    @Resource
    public ArticleMapper articleMapper;

    @Override
    public ResponseResult addArticle(Article article) {
        article.setWeight(1);
        article.setState(1);
        if (save(article) != null) {
            return generatorSuccessResult();
        }
        return generatorFailResult("添加文章失败");
    }

    @Override
    public Article getArticle(Long id) {
        Article article = findById(id);
        article.setType(getTypeText(article.getType()));
        article.setcTime(DateUtil.formatDate(article.getCreateTime(), DateUtil.DATE_PATTERN_YYYY_MM_DD));
        return article;
    }

    @Override
    public int getArticleCount(Article article) {
        return articleMapper.getArticleCount(article);
    }

    @Override
    public void deleteArticle(Long id) {
        deleteById(id);
    }

    @Override
    public List<Article> searchArticle(Article article) {
        startPage(article.getPage(), article.getSize());
        return articleMapper.getArticleList(article).stream().map(item -> {
            item.setcTime(DateUtil.formatDate(item.getCreateTime(), "yyyy-MM-dd"));
            item.setType(getTypeText(item.getType()));
            return item;
        }).collect(toList());
    }

    @Override
    public void deleteTable() {
        articleMapper.deleteTable();
    }

    private String getTypeText(String type) {
        StringBuilder builder = new StringBuilder();
        Arrays.asList(type.split(",")).stream().forEach(e -> {
            builder.append(ArticleTypeEnum.get(Integer.parseInt(e)).getName() + "，");
        });
        return builder.toString().isEmpty() ? "" : builder.toString().substring(0, builder.toString().length() - 1);
    }

}
