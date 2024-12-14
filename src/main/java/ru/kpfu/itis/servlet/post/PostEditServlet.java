package ru.kpfu.itis.servlet.post;

import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.model.Post;
import ru.kpfu.itis.service.AccountService;
import ru.kpfu.itis.service.PostService;
import ru.kpfu.itis.util.ImageUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@MultipartConfig
@WebServlet("/posts/edit")
public class PostEditServlet extends HttpServlet {

    PostService postService;
    AccountService accountService;

    String uuid;
    Post post;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        postService = (PostService) getServletContext().getAttribute("postService");
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        uuid = req.getParameter("id");
        if (uuid == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Bad request. No uuid has been provided");
        }
        post = postService.getById(UUID.fromString(uuid));
        if (post == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
        req.setAttribute("post", post);
        req.getRequestDispatcher("/WEB-INF/view/posts/editPost.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Part image = req.getPart("image");
        String[] file = image.getSubmittedFileName().split("\\.");
        Date dateOfUpdate = new Date();
        Account author = accountService.getCurrentAccount(req);

        String imageName = null;

        if (file.length > 1) {
            String fileName = file[0] + "-author-" + author.uuid().toString()+ "." + file[1];
            imageName = ImageUtil.makeFile(image, fileName, req);
        }
        if (author.uuid().equals(post.author().uuid())) {
            Post postUpdate = new Post(UUID.fromString(uuid), author, title, content, imageName, dateOfUpdate);
            postService.update(postUpdate);
            resp.sendRedirect(getServletContext().getContextPath() + "/profile");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
    }
}
