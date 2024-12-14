package ru.kpfu.itis.servlet.comment;

import ru.kpfu.itis.dto.CommentUpdateDto;
import ru.kpfu.itis.model.Account;
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
import java.util.Date;
import java.util.UUID;

@WebServlet("/comment/edit")
public class CommentEditServlet extends HttpServlet {
    CommentService commentService;
    AccountService accountService;

    String uuid;
    Comment comment;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        commentService = (CommentService) getServletContext().getAttribute("commentService");
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        uuid = req.getParameter("id");
        if (uuid == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Bad request. No uuid has been provided");
        }
        comment = commentService.getById(UUID.fromString(uuid));
        if (comment == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
        req.setAttribute("comment", comment);
        req.getRequestDispatcher("/WEB-INF/view/comments/commentEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String content = req.getParameter("content");
        Date dateOfUpdate = new Date();
        Account author = accountService.getCurrentAccount(req);

        if (author.uuid().equals(comment.author().uuid())) {
            CommentUpdateDto commentUpdate = new CommentUpdateDto(UUID.fromString(uuid), content, dateOfUpdate);
            commentService.update(commentUpdate);
            resp.sendRedirect(req.getContextPath() + "/posts/detail?id=" + comment.post().uuid());
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
    }
}
