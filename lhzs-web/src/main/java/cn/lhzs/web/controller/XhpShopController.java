package cn.lhzs.web.controller;

import cn.lhzs.service.intf.XhpShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
* Created by ZHX on 2019/01/03.
*/
@Controller
@RequestMapping("/xhp/shop")
public class XhpShopController {

    @Autowired
    private XhpShopService xhpShopService;


}
