package com.example.zhaobf.apigateway.filter;

import com.netflix.discovery.util.StringUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 过滤器
 */
public class AccessFilter extends ZuulFilter {
    Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 判断该过滤器是否需要被执行.
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info("send {} request to {} ",request.getMethod(),request.getRequestURI().toString());

        String accessToken = request.getParameter("accessToken");
        if (StringUtils.isEmpty(accessToken)) {
            logger.warn("access token is empty");
            //过滤该请求,不对其进行路由,
            ctx.setSendZuulResponse(false);
            //设置请求返回的错误码,
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("accessToken is empty");
            return null;
        }
        logger.info("access token ok");
        return null;
    }

    /**
     * 过滤器类型,它决定过虑器在请求的哪个生命周期中执行. 这里定义为pre,代表会在请求被路由之前执行.
     * pre:可以在请求被路由之前调用
     * routing : 在路由请求时被调用
     * post : 在routing和error 过滤器之后被调用
     * error:  处理请求时发生错误时被调用 .
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过虑器的执行顺序. 当请求在一个阶段中存在多个过滤器时,需要根据该方法返回的值依次执行 .
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }
}
