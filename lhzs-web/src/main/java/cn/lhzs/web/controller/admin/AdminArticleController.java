package cn.lhzs.web.controller.admin;

import cn.lhzs.common.aop.log.OpLog;
import cn.lhzs.data.bean.Article;
import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.service.intf.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.aop.log.OpEnum.ADD;
import static cn.lhzs.common.aop.log.OpEnum.DEL;
import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
* Created by ZHX on 2017/12/6.
*/
@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {

    @Autowired
    public ArticleService articleService;

    @OpLog(type = DEL, descp = "删除文章")
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseResult deleteArticle(Long id) {
        articleService.deleteArticle(id);
        return generatorSuccessResult();
    }

    @RequestMapping("/detail")
    @ResponseBody
    public ResponseResult getArticleDetail(@RequestBody Article article) {
        return generatorSuccessResult(articleService.getArticle(article.getId()));
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseResult searchArticle(@RequestBody Article article) {
        return generatorSuccessResult(new PageInfo(articleService.searchArticle(article)));
    }

    @OpLog(type = ADD, descp = "增加文章")
    @RequestMapping("/gene/article")
    @ResponseBody
    public ResponseResult addArticle(@RequestBody Article article) {
        Assert.notNull(article);
        return articleService.addArticle(article);
    }

}
