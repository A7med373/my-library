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
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@MultipartConfig
@WebServlet("/create")
public class PostCreateServlet extends HttpServlet {

    private PostService postService;
    private AccountService accountService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        postService = (PostService) getServletContext().getAttribute("postService");
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/posts/createPost.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Part image = req.getPart("image");
        Account author = accountService.getCurrentAccount(req);
        Date dateOfCreation = new Date();

        String imageName = null;

        if (image != null && image.getSubmittedFileName() != null && !image.getSubmittedFileName().isEmpty()) {
            String[] fileParts = image.getSubmittedFileName().split("\\.");
            if (fileParts.length > 1) {
                String fileExtension = fileParts[fileParts.length - 1];
                String baseName = String.join(".", java.util.Arrays.copyOf(fileParts, fileParts.length - 1));
                String fileName = baseName + "-author-" + author.uuid().toString() + "." + fileExtension;
                imageName = ImageUtil.makeFile(image, fileName, req);
            }
        }

        // Генерация нового UUID для поста
        UUID postUuid = UUID.randomUUID();

        Post post = new Post(postUuid, author, title, content, imageName, dateOfCreation);

        try {
            postService.save(post);
            resp.sendRedirect(getServletContext().getContextPath() + "/profile");
        } catch (Exception e) {
            req.setAttribute("error", "Не удалось сохранить пост");
            req.getRequestDispatcher("/WEB-INF/view/posts/createPost.jsp").forward(req, resp);
        }
    }
}
