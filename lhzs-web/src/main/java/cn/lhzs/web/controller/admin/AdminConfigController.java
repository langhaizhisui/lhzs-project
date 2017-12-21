package cn.lhzs.web.controller.admin;

import cn.lhzs.common.aop.log.OpLog;
import cn.lhzs.common.result.ResponseResult;
import cn.lhzs.service.intf.ConfigService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.lhzs.common.aop.log.OpEnum.DEL;
import static cn.lhzs.common.result.ResponseResultGenerator.generatorSuccessResult;

/**
 * Created by ZHX on 2017/12/6.
 */
@Controller
@RequestMapping("/admin/config")
public class AdminConfigController {

    @Autowired
    public ConfigService configService;

    @RequestMapping("/slideshowPicture/list")
    @ResponseBody
    public ResponseResult getSlideshowPicture() {
        return generatorSuccessResult(new PageInfo(configService.getSlideShowPictureList()));
    }

    @OpLog(type = DEL, descp = "删除轮播图")
    @RequestMapping("/slideshowPicture/delete")
    @ResponseBody
    public ResponseResult deleteSlideshowPicture(Integer id) {
        configService.deleteSlideShowPicture(id);
        return generatorSuccessResult();
    }
}
