package ru.kpfu.itis.servlet.search;

import ru.kpfu.itis.model.Post;
import ru.kpfu.itis.service.PostService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/search/add")
public class SearchAddServlet extends HttpServlet {

    PostService postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        postService = (PostService) getServletContext().getAttribute("postService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String title = req.getParameter("title");
        List<Post> posts = postService.getByTitle(title);
        if (title != null && posts != null && !posts.isEmpty()) {
            for (Post post: posts) {
                String href = "\"" + req.getServletContext().getContextPath() + "/posts/detail?id=" +
                        post.uuid().toString() + "\"";
                out.println("<h4>");
                out.println("<a href=" + href + ">" + post.title() +"</a>");
                out.println("</h4>");
                out.println("<br>");
            }
        } else {
            out.println("<h4>Не найдено<h4>");
        }
    }
}
