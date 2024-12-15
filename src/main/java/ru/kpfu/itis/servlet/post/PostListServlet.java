package ru.kpfu.itis.servlet.post;

import ru.kpfu.itis.service.PostService;
import ru.kpfu.itis.model.Post;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/posts/list")
public class PostListServlet extends HttpServlet {

    private PostService postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        postService = (PostService) getServletContext().getAttribute("postService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts = postService.getAll();
        System.out.println("Number of posts: " + posts.size()); // Для отладки
        for (Post post : posts) {
            System.out.println("Post Title: " + post.title()); // Для отладки
        }
        req.setAttribute("posts", posts); // Устанавливаем атрибут с именем 'posts'
        req.getRequestDispatcher("/WEB-INF/view/posts/listPost.jsp").forward(req, resp);
    }
}
