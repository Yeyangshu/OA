package com.yeyangshu.oa.filter;

import com.yeyangshu.oa.entity.Account;
import com.yeyangshu.oa.entity.Permission;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 账户登录过滤器，拦截所有请求
 * @author yeyangshu
 * @version 1.0
 * @date 2020/8/27 23:41
 */
@Component
@WebFilter(urlPatterns = "/")
public class AccountFilter implements Filter {

    /** 需要排除的，不需要登录操作的url */
    private final String[] IGNORE_URL = {"/index", "/account/login", "/account/validateAccount", "/css/", "/js/", "/errorPage/"};

    /**
     * 过滤器
     * @param req
     * @param resp
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String uri = request.getRequestURI();
        System.out.println("request uri is：" + uri);
        boolean pass = canPassIgnore(uri);
        if (pass) {
            filterChain.doFilter(request, response);
            return;
        }
        // 从session获取Account判断账户是否登陆
        Account account = (Account) request.getSession().getAttribute("account");
        System.out.println("get session account：" + account);
        if (account == null) {
            // 如果用户没有登录，重新跳转到登录页面
            response.sendRedirect("/account/login");
            return;
        }

        // 判断已登录用户是否具有访问当前页面权限
        if (!hasAuth(account.getPermissionList(), uri)) {
            request.setAttribute("msg", "您无权访问当前页面:" + uri);
            // getRequestDispatcher服务器端跳转，浏览器没有感知，url不变
            request.getRequestDispatcher("/errorPage").forward(request, response);
            return;
        }

        // 控制台输出请求的url
        System.out.println("-------filter--------" + uri);
        filterChain.doFilter(request, response);
    }

    /**
     * 判断当前访问的url起始部分是否在IGNORE_URL里
     * 下级目录资源也能访问
     * @param uri
     * @return boolean
     */
    private boolean canPassIgnore(String uri) {
        for (String val : IGNORE_URL) {
            if (uri.startsWith(val)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /* 加载filter，启动之前需要的资源 */
        System.out.println("-------AccountFilter init---------");
        Filter.super.init(filterConfig);
    }

    private boolean hasAuth(List<Permission> permissionList, String uri) {
        for (Permission permission : permissionList) {
            if (uri.startsWith(permission.getUri())) {
                return true;
            }
        }
        return false;
    }
}