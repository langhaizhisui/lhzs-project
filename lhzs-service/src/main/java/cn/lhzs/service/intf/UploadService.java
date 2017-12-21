package cn.lhzs.service.intf;

import cn.lhzs.data.bean.SlideShowPicture;

import java.io.InputStream;

/**
 * Created by IBA-EDV on 2017/4/26.
 */
public interface UploadService {
    String getExcell(String fileName, InputStream inputStream, String type);

    void saveImageInfo(SlideShowPicture slideShowPicture);
}
