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

@WebServlet("/books/add")
public class BookAddServlet extends HttpServlet {

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
        req.getRequestDispatcher("/WEB-INF/view/books/addBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String genre = req.getParameter("genre");
        String review = req.getParameter("review");

        Account authorAccount = accountService.getCurrentAccount(req);
        UUID uuid = UUID.randomUUID();

        // Создание объекта Book без изображения
        Book book = new Book(uuid, title, author, genre, false, review);

        if (authorAccount != null && "admin".equals(authorAccount.role().name())) {
            if (bookService.save(book, req)) {
                resp.sendRedirect(getServletContext().getContextPath() + "/books/list");
            } else {
                req.setAttribute("error", "Failed to add book. Please try again.");
                req.getRequestDispatcher("/WEB-INF/view/books/addBook.jsp").forward(req, resp);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
        }
    }
}
