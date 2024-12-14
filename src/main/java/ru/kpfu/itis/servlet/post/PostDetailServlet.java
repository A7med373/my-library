package ru.kpfu.itis.servlet.post;

import ru.kpfu.itis.dto.CommentEditDto;
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
import java.util.List;
import java.util.UUID;

@WebServlet("/posts/detail")
public class PostDetailServlet extends HttpServlet {

    PostService postService;
    AccountService accountService;
    CommentService commentService;

    String uuid;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        postService = (PostService) getServletContext().getAttribute("postService");
        accountService = (AccountService) getServletContext().getAttribute("accountService");
        commentService = (CommentService) getServletContext().getAttribute("commentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        uuid = req.getParameter("id");
        if (uuid == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Bad request. No uuid has been provided");
        }
        Post post;
        post = postService.getById(UUID.fromString(uuid));
        if (post == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }

        Account account = accountService.getCurrentAccount(req);
        List<CommentEditDto> comments = commentService.getAllByPostId(UUID.fromString(uuid), account);


        req.setAttribute("post", post);
        req.setAttribute("comment", comments);
        req.getRequestDispatcher("/WEB-INF/view/posts/detailPost.jsp").forward(req, resp);
    }

}
