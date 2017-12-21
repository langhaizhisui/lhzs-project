package cn.lhzs.web.controller;

import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.service.intf.ConfigService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
 * Created by ZHX on 2017/5/7.
 */
@Controller
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    public ConfigService configService;

    @RequestMapping("/slideshowPicture/list")
    @ResponseBody
    public ResponseResult getSlideshowPicture() {
        return generatorSuccessResult(new PageInfo(configService.getSlideShowPictureList()));
    }

}
