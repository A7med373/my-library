package ru.kpfu.itis.servlet.admin;

import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.service.AccountService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admins/add")
public class AdminAddServlet extends HttpServlet {

    AccountService accountService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        Account account = accountService.getByUsername(req.getParameter("username"));
        if (account == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
        accountService.addAdmin(account);
        resp.sendRedirect(getServletContext().getContextPath() + "/admins");

    }
}
