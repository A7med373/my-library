package ru.kpfu.itis.servlet.comment;

import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.model.Comment;
import ru.kpfu.itis.model.Post;
import ru.kpfu.itis.service.AccountService;
import ru.kpfu.itis.service.CommentService;
import ru.kpfu.itis.service.PostService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@WebServlet("/comment/add")
public class CommentAddServlet extends HttpServlet {

    CommentService commentService;
    AccountService accountService;
    PostService postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        commentService = (CommentService) getServletContext().getAttribute("commentService");
        accountService = (AccountService) getServletContext().getAttribute("accountService");
        postService = (PostService) getServletContext().getAttribute("postService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String postUuid = req.getParameter("postId");
        String content = req.getParameter("content");

        if (accountService.isNonAnonymous(req)) {
            Post post = postService.getById(UUID.fromString(postUuid));
            Account account = accountService.getCurrentAccount(req);

            Date dateOfPublication = new Date();

            Comment comment = new Comment(null, account, post, content, dateOfPublication);
            commentService.save(comment);
            resp.sendRedirect(req.getContextPath() + "/posts/detail?id=" + comment.post().uuid());
        } else {
            resp.sendRedirect(req.getContextPath() + "/sign-in");
        }
    }
}
