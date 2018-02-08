package cn.lhzs.web.controller;

import cn.lhzs.common.constant.Constants;
import cn.lhzs.common.util.StringUtil;
import cn.lhzs.common.util.WebUtil;
import cn.lhzs.common.util.WechatUtil;
import cn.lhzs.common.util.XMLUtil;
import cn.lhzs.service.intf.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by ZHX on 2018/2/8.
 */
@Controller
@RequestMapping("/app/weixin")
public class WechatController {

    @Autowired
    WechatService wechatService;

    @RequestMapping(value = "/checkSignature")
    public void checkSignature(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> paramFromRequest = WebUtil.getAllRequestParam(request,Constants.UTF8);
        paramFromRequest.put("requestUrl", request.getRequestURL().toString().replace(request.getRequestURI(), "").replace(":8000", "") + "/app/weixin/oauthuser");

        try{
            Map<String,String> paramFromInputStream = XMLUtil.getMapFromInputStreamXML(request.getInputStream(), Constants.UTF8);
            String xmlMsg = wechatService.reply(paramFromRequest, paramFromInputStream);
            if (StringUtil.isNotEmpty(xmlMsg)){
                response.getWriter().write(xmlMsg);
            }else{
                response.getWriter().write(" ");
            }
        }catch (Exception ex){
            if (WechatUtil.checkSign(paramFromRequest)){
                String echostr = request.getParameter("echostr");
                if (StringUtil.isNotEmpty(echostr)) {
                    response.getWriter().print(echostr);
                }else{
                    response.getWriter().print("");
                }
            }
        }
    }
}
