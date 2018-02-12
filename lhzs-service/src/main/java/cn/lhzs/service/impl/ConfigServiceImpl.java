package cn.lhzs.service.impl;

import cn.lhzs.base.AbstractBaseService;
import cn.lhzs.data.bean.Config;
import cn.lhzs.data.bean.SlideShowPicture;
import cn.lhzs.common.vo.WebGeneralizeCondition;
import cn.lhzs.common.constant.Constants;
import cn.lhzs.data.dao.ConfigMapper;
import cn.lhzs.service.intf.ConfigService;
import cn.lhzs.common.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by ZHX on 2017/5/7.
 */
@Service
public class ConfigServiceImpl extends AbstractBaseService<Config> implements ConfigService {

    Logger logger = Logger.getLogger(ConfigServiceImpl.class);

    @Resource
    public ConfigMapper configMapper;

    @Override
    public Config getConfigById(final Long id) {
        return findById(id);
    }

    @Override
    public Config updateConfigById(Config config) {
        return save(config);
    }

    @Override
    public void addConfig(Config config) {
        save(config);
    }

    @Override
    public List<SlideShowPicture> getSlideShowPictureList() {
        String value = getConfigById(Constants.SLIDESHOW_PICTURE).getValue();
        if (StringUtil.isNotEmpty(value)) {
            return JSONObject.parseArray(value, SlideShowPicture.class).stream()
                    .sorted(Comparator.comparing(SlideShowPicture::getCreateTime).reversed())
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteSlideShowPicture(Integer id) {
        Config currentConfig = getConfigById(Constants.SLIDESHOW_PICTURE);
        List<SlideShowPicture> slideShowPictureList = JSONObject.parseArray(currentConfig.getValue(), SlideShowPicture.class);
        slideShowPictureList.remove(id - 1);
        currentConfig.setValue(JSONObject.toJSONString(slideShowPictureList));
        updateConfigById(currentConfig);
    }

    @Override
    public void addWebGeneralize(WebGeneralizeCondition webGeneralize) {
        Config webGeneralizeConfig = getConfigById(Constants.WEB_GENERALIZE);
        List<WebGeneralizeCondition> webGeneralizeList = JSONObject.parseArray(webGeneralizeConfig.getValue(), WebGeneralizeCondition.class);
        webGeneralizeList.add(webGeneralize);
        webGeneralizeConfig.setValue(JSONObject.toJSONString(webGeneralizeList));
        updateConfigById(webGeneralizeConfig);
    }

    @Override
    public void deleteWebGeneralize(Integer id) {
        Config webGeneralizeConfig = getConfigById(Constants.WEB_GENERALIZE);
        List<WebGeneralizeCondition> webGeneralizeList = JSONObject.parseArray(webGeneralizeConfig.getValue(), WebGeneralizeCondition.class);
        webGeneralizeList.remove(id - 1);
        webGeneralizeConfig.setValue(JSONObject.toJSONString(webGeneralizeList));
        updateConfigById(webGeneralizeConfig);
    }

    @Override
    public void updateWebGeneralize(WebGeneralizeCondition webGeneralize) {
        Config webGeneralizeConfig = getConfigById(Constants.WEB_GENERALIZE);
        List<WebGeneralizeCondition> webGeneralizeList = JSONObject.parseArray(webGeneralizeConfig.getValue(), WebGeneralizeCondition.class);
        webGeneralizeList.remove(webGeneralize.getId() - 1);
        webGeneralizeList.add(webGeneralize.getId() - 1, webGeneralize);
        webGeneralizeConfig.setValue(JSONObject.toJSONString(webGeneralizeList));
        updateConfigById(webGeneralizeConfig);
    }

    @Override
    public WebGeneralizeCondition getWebGeneralizeDetail(Integer id) {
        Config webGeneralizeConfig = getConfigById(Constants.WEB_GENERALIZE);
        return JSONObject.parseArray(webGeneralizeConfig.getValue(), WebGeneralizeCondition.class).get(id - 1);
    }

    @Override
    public List<WebGeneralizeCondition> getWebGeneralizeList(WebGeneralizeCondition webGeneralize) {
        List<WebGeneralizeCondition> webGeneralizeList = JSONObject.parseArray(getConfigById(Constants.WEB_GENERALIZE).getValue(), WebGeneralizeCondition.class);
        if (webGeneralize.getWebName() != null) {
            webGeneralizeList = webGeneralizeList.stream()
                    .filter(item -> StringUtil.isNotEmpty(webGeneralize.getWebName()) && webGeneralize.getWebName().equals(item.getWebName()))
                    .collect(toList());
        }
        return webGeneralizeList;
    }
}
