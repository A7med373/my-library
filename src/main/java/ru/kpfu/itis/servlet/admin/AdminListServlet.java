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
import java.util.List;

@WebServlet("/admins")
public class AdminListServlet extends HttpServlet {

    AccountService accountService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Account> admins = accountService.getAdmins();
        req.setAttribute("admins", admins);
        req.getRequestDispatcher("/WEB-INF/view/admin/listAdmin.jsp").forward(req, resp);
    }
}
