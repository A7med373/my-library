package ru.kpfu.itis.filter;

import ru.kpfu.itis.service.AccountService;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {

    public static String[] securedPaths = new String[]{"/profile", "/create"};

    AccountService accountService;

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        boolean prot = false;
        for (String path : securedPaths) {
            if (path.equals(req.getRequestURI().substring(req.getContextPath().length()))) {
                prot = true;
                break;
            }
        }
        if (prot && !accountService.isNonAnonymous(req)) {
            res.sendRedirect(req.getContextPath() + "/sign-in");
        } else {
            if (accountService.isNonAnonymous(req)) {
                req.setAttribute("account", accountService.getCurrentAccount(req));
            }
            chain.doFilter(req, res);
        }
    }
}
