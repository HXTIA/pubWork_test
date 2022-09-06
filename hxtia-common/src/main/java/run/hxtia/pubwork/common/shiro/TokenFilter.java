package run.hxtia.pubwork.common.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import run.hxtia.pubwork.common.utils.JsonVos;
import run.hxtia.pubwork.pojo.vo.result.CodeMsg;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class TokenFilter extends AccessControlFilter {

    public static final String HEADER_TOKEN = "Token";

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        // 取出Token
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader(HEADER_TOKEN);
        log.debug("onAccessDenied - {}", token);

        // 如果没有Token
        if (token == null) return JsonVos.raise(CodeMsg.NO_TOKEN);

        //TODO: 取出缓存的Token
        // 如果Token过期了

        //TODO: 这里决定是否需要去鉴权
        SecurityUtils.getSubject().login(new Token(token));

        return true;
    }
}
