package ru.kpfu.itis.servlet.comment;

import ru.kpfu.itis.model.Comment;
import ru.kpfu.itis.service.AccountService;
import ru.kpfu.itis.service.CommentService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/comment/delete")
public class CommentDeleteServlet extends HttpServlet {

    CommentService commentService;
    AccountService accountService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        commentService = (CommentService) getServletContext().getAttribute("commentService");
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("id");
        Comment comment = commentService.getById(UUID.fromString(uuid));
        if (accountService.getCurrentAccount(req).uuid().equals(comment.author().uuid())) {
            commentService.delete(comment);
            resp.sendRedirect(req.getContextPath() + "/posts/detail?id=" + comment.post().uuid());
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
    }
}
