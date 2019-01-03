package cn.lhzs.web.controller;

import cn.lhzs.service.intf.XhpFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
* Created by ZHX on 2019/01/03.
*/
@Controller
@RequestMapping("/xhp/file/upload")
public class XhpFileUploadController {

    @Autowired
    private XhpFileUploadService xhpFileUploadService;


}
