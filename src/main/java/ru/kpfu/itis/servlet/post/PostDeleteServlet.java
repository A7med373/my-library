package ru.kpfu.itis.servlet.post;

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
import java.util.List;
import java.util.UUID;

@WebServlet("/posts/delete")
public class PostDeleteServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("id");
        Post post = postService.getById(UUID.fromString(uuid));
        List<Comment> comments =  commentService.getAllByPostId(UUID.fromString(uuid));
        Account author = accountService.getCurrentAccount(req);

        if (author.uuid().equals(post.author().uuid())) {
            for(Comment comment: comments) {
                commentService.delete(comment);
            }
            postService.delete(post);
            resp.sendRedirect(getServletContext().getContextPath() + "/profile");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
    }
}
