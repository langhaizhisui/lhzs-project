package cn.lhzs.service.intf;

import cn.lhzs.data.bean.Config;
import cn.lhzs.data.bean.SlideShowPicture;
import cn.lhzs.common.vo.WebGeneralizeCondition;

import java.util.List;

/**
 * Created by ZHX on 2017/5/7.
 */
public interface ConfigService {

    Config getConfigById(Long confId);

    void updateConfigById(Config config);

    void addConfig(Config config);

    List<SlideShowPicture> getSlideShowPictureList();

    void deleteSlideShowPicture(Integer id);

    void addWebGeneralize(WebGeneralizeCondition webGeneralize);

    void deleteWebGeneralize(Integer id);

    void updateWebGeneralize(WebGeneralizeCondition webGeneralize);

    WebGeneralizeCondition getWebGeneralizeDetail(Integer id);

    List<WebGeneralizeCondition> getWebGeneralizeList(WebGeneralizeCondition webGeneralize);
}
