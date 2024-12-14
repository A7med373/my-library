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

@WebServlet("/admins/delete")
public class AdminDeleteServlet extends HttpServlet {

    AccountService accountService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        if (username == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Bad request. No uuid has been provided");
        }
        Account account = accountService.getByUsername(username);
        if (account == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
        accountService.deleteAdmin(account);
        resp.sendRedirect(getServletContext().getContextPath() + "/admins");
    }
}
