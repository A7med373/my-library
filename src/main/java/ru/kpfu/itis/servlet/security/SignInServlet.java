package ru.kpfu.itis.servlet.security;

import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.service.AccountService;
import ru.kpfu.itis.util.PasswordUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    private AccountService accountService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/security/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("login");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("check");

        Account account = accountService.getByUsernameAndPassword(username, password);

        if (account == null) {
            req.setAttribute("error", "Неверный логин или пароль");
            req.getRequestDispatcher("/WEB-INF/view/security/signIn.jsp").forward(req, resp);
        } else {
            if (rememberMe != null) {
                accountService.addCookie(username, resp);
            }
            accountService.auth(account, req);
            resp.sendRedirect(getServletContext().getContextPath() + "/profile");
        }
    }
}
