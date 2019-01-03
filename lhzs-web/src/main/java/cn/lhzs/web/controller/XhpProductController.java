package cn.lhzs.web.controller;

import cn.lhzs.service.intf.XhpProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
* Created by ZHX on 2019/01/03.
*/
@Controller
@RequestMapping("/xhp/product")
public class XhpProductController {

    @Autowired
    private XhpProductService xhpProductService;


}
