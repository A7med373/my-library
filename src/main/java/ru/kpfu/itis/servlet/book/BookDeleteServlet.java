package ru.kpfu.itis.servlet.book;

import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.model.Book;
import ru.kpfu.itis.service.AccountService;
import ru.kpfu.itis.service.BookService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/books/delete")
public class BookDeleteServlet extends HttpServlet {

    private BookService bookService;
    private AccountService accountService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bookService = (BookService) getServletContext().getAttribute("bookService");
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidParam = req.getParameter("id");

        // Проверка наличия UUID
        if (uuidParam == null || uuidParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("error", "Invalid book ID.");
            req.getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
            return;
        }

        try {
            UUID uuid = UUID.fromString(uuidParam);
            Book book = bookService.getById(uuid);
            if (book == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                req.setAttribute("error", "Book not found.");
                req.getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
                return;
            }

            Account author = accountService.getCurrentAccount(req);
            if ("admin".equals(author.role().name())) {
                bookService.delete(book);
                resp.sendRedirect(getServletContext().getContextPath() + "/books/list");
            } else {
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                req.setAttribute("error", "Access denied.");
                req.getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
            }
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("error", "Invalid book ID format.");
            req.getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
    }
}
