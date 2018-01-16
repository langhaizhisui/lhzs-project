package cn.lhzs.web.controller.admin;

import cn.lhzs.common.support.thread.JestThreadPool;
import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.service.intf.ProductService;
import cn.lhzs.service.intf.SearchService;
import cn.lhzs.service.intf.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
 * Created by ZHX on 2017/12/6.
 */
@Controller
@RequestMapping("/admin/search")
public class AdminSearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private JestThreadPool jestThreadPool;

    @RequestMapping("/build/products/index")
    @ResponseBody
    public ResponseResult buildProductsSearchIndex() {
        jestThreadPool.addTask(() -> searchService.builderSearchIndex(productService.getAllProduct(), "products", "prod"));
        return generatorSuccessResult();
    }

    @RequestMapping("/build/shops/index")
    @ResponseBody
    public ResponseResult buildShopsSearchIndex() {
        jestThreadPool.addTask(() -> searchService.builderSearchIndex(shopService.getAllShop(), "shops", "shop"));
        return generatorSuccessResult();
    }

}
