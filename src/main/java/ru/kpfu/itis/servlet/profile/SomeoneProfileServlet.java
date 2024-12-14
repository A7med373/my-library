package ru.kpfu.itis.servlet.profile;

import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.model.Post;
import ru.kpfu.itis.service.AccountService;
import ru.kpfu.itis.service.impl.PostServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/someone")
public class SomeoneProfileServlet extends HttpServlet {

    private AccountService accountService;
    private PostServiceImpl postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        accountService = (AccountService) getServletContext().getAttribute("accountService");
        postService = (PostServiceImpl) getServletContext().getAttribute("postService");
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
        List<Post> posts = postService.getByAuthor(account.uuid());
        req.setAttribute("account", account);
        req.setAttribute("post", posts);
        req.getRequestDispatcher("/WEB-INF/view/profile/someoneProfile.jsp").forward(req, resp);
    }
}
