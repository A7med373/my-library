package ru.kpfu.itis.servlet.book;

import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.model.Book;
import ru.kpfu.itis.service.AccountService;
import ru.kpfu.itis.service.BookService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
@WebServlet("/books/edit")
public class BookEditServlet extends HttpServlet {

    private BookService bookService;
    private AccountService accountService;

    private String uuid;
    private Book book;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bookService = (BookService) getServletContext().getAttribute("bookService");
        accountService = (AccountService) getServletContext().getAttribute("accountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        uuid = req.getParameter("id");
        if (uuid == null || uuid.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("error", "Bad request. No book ID has been provided.");
            req.getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
            return;
        }

        try {
            book = bookService.getById(UUID.fromString(uuid));
            if (book == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                req.setAttribute("error", "Book not found.");
                req.getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
                return;
            }

            req.setAttribute("book", book);
            req.getRequestDispatcher("/WEB-INF/view/books/editBook.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("error", "Invalid book ID format.");
            req.getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String genre = req.getParameter("genre");
        String review = req.getParameter("review");
        String isReadParam = req.getParameter("isRead");
        boolean isRead = "on".equalsIgnoreCase(isReadParam);

        Account authorAccount = accountService.getCurrentAccount(req);

        Book updatedBook = new Book(UUID.fromString(uuid), title, author, genre, isRead, review);

        if ("admin".equals(authorAccount.role().name())) {
            if (bookService.update(updatedBook, book, req)) {
                resp.sendRedirect(getServletContext().getContextPath() + "/books/list");
            } else {
                req.setAttribute("error", "Failed to update book. Please try again.");
                req.getRequestDispatcher("/WEB-INF/view/books/editBook.jsp").forward(req, resp);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            req.setAttribute("error", "Access denied.");
            req.getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
    }

}
