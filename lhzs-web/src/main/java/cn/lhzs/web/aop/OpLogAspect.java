package cn.lhzs.web.aop;

import cn.lhzs.common.aop.log.OpLog;
import cn.lhzs.common.result.ResponseResult;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhx on 2017/11/23.
 * @descp 管理后台日志操作
 */

@Aspect
@Component
public class OpLogAspect {

    Logger logger = Logger.getLogger(OpLogAspect.class);

    private static String ERROR_FORMAT = "【操作日志】suid:%s, 方法:%s, 操作:%s, 参数:%s, 返回值:%s, 异常状态:%s";

    private static final String LOGIN_PWD = "password";
    private static final String LOGIN_METHOD = "login";

    @AfterReturning(pointcut = "execution(* cn.lhzs.web.controller.admin.*.*(..)) && @annotation(ol)", returning = "rvt")
    public void addLogSuccess(JoinPoint jp, OpLog ol, Object rvt) {
        addLog(jp, ol, rvt, null);
    }

    @AfterThrowing(pointcut = "execution(* cn.lhzs.web.controller.admin.*.*(..)) && @annotation(ol)", throwing = "ex")
    public void addLog(JoinPoint jp, OpLog ol, Throwable ex) {
        addLog(jp, ol, null, ex);
    }

    private void addLog(JoinPoint jp, OpLog ol, Object rvt, Throwable ex) {
        try {
            Object[] arguments = jp.getArgs();//获取目标方法体参数
            String methodName = jp.getSignature().getName();
            String[] paramNames = ((CodeSignature) jp.getSignature()).getParameterNames();
            StringBuilder sb = new StringBuilder(1024);
            logParamValues(sb, paramNames, arguments);
            if (rvt != null && rvt instanceof ResponseResult) {
                logger.error(String.format(ERROR_FORMAT, "admin", methodName, ol.descp(), sb.toString(), ((ResponseResult) rvt).getCode(), "正常"));
                return;
            }
            logger.error(String.format(ERROR_FORMAT, "admin", methodName, ol.descp(), sb.toString(), "", ex.getMessage()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void logParamValues(StringBuilder logLine, String[] paramNames, Object[] paramValues) {
        for (int i = 0; i < paramValues.length; i++) {
            if (paramValues[i] instanceof HttpServletRequest ||
                    paramValues[i] instanceof HttpServletResponse) {
                continue;
            }
            if (paramValues[i] instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) paramValues[i];
                logLine.append(paramNames[i]).append("=").append(file.getOriginalFilename());
                continue;
            }
            //过滤登录中密码字段
            if (LOGIN_PWD.equals(paramNames[i])) {
                logLine.append(LOGIN_PWD).append("=").append("***, ");
                continue;
            }
            logLine.append(paramNames[i]).append("=").append(JSONObject.toJSONString(paramValues[i]));
            if (i < paramValues.length - 1) {
                logLine.append(", ");
            }
        }
    }
}
