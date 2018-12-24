package cn.lhzs.web.config;

import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by ZHX on 2018/12/23.
 */
public class LogbackFilter implements Filter{
    private static final String UNIQUE_ID = "traceId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        boolean bInsertMDC = insertMDC();
        try {
            chain.doFilter(request, response);
        } finally {
            if (bInsertMDC) {
                MDC.remove(UNIQUE_ID);
            }
        }
    }

    private boolean insertMDC() {
        UUID uuid = UUID.randomUUID();
        String uniqueId = UNIQUE_ID + "=" + uuid.toString().replace("-", "");
        MDC.put(UNIQUE_ID, uniqueId);
        return true;
    }

    @Override
    public void destroy() {

    }
}
