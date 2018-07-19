package cn.lhzs.web.controller.admin;

import cn.lhzs.common.aop.log.OpLog;
import cn.lhzs.data.bean.Product;
import cn.lhzs.common.vo.ProductSearchCondition;
import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.service.intf.ProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.aop.log.OpEnum.ADD;
import static cn.lhzs.common.aop.log.OpEnum.DEL;
import static cn.lhzs.common.aop.log.OpEnum.EDIT;
import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
 * Created by ZHX on 2017/12/6.
 */
@Controller
@RequestMapping("/admin/prod")
public class AdminProductController {

    @Autowired
    public ProductService productService;

    @RequestMapping(value = "/getProduct", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getProduct(Long prodId) {
        return generatorSuccessResult(productService.getProductByProdId(prodId));
    }

    @OpLog(type = ADD, descp = "增加商品")
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult addProduct(@RequestBody Product product) {
        productService.addProd(product);
        return generatorSuccessResult();
    }

    @OpLog(type = DEL, descp = "删除商品")
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseResult deleteProduct(Long prodId) {
        productService.deleteProdByProdId(prodId);
        return generatorSuccessResult();
    }

    @OpLog(type = EDIT, descp = "更新商品")
    @RequestMapping("/update")
    @ResponseBody
    public ResponseResult updateProduct(@RequestBody Product product) {
        productService.updateProd(product);
        return generatorSuccessResult();
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseResult search(@RequestBody ProductSearchCondition productSearchCondition) {
        return generatorSuccessResult(productService.searchProduct(productSearchCondition));
    }

    @OpLog(type = DEL, descp = "删除所有商品")
    @RequestMapping("/all/delete")
    @ResponseBody
    public ResponseResult deleteTable() {
        productService.deleteTable();
        return generatorSuccessResult();
    }

    @OpLog(type = DEL, descp = "批量删除商品")
    @RequestMapping("/batch/delete")
    @ResponseBody
    public ResponseResult batchDelete(@RequestBody ProductSearchCondition productSearchCondition) {
        productService.batchDeleteProduct(productSearchCondition);
        return generatorSuccessResult();
    }
}
