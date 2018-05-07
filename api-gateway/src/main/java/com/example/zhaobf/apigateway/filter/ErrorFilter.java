package com.example.zhaobf.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
        ctx.set("error.status_code", HttpServletResponse.SC_BAD_GATEWAY);
        ctx.set("error.exception", throwable.getCause());
        return null;
    }
}
