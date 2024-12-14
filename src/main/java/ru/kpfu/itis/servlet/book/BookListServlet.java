package ru.kpfu.itis.servlet.book;

import ru.kpfu.itis.service.BookService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/books/list")
public class BookListServlet extends HttpServlet {

    private BookService bookService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bookService = (BookService) getServletContext().getAttribute("bookService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получение списка книг по категориям (sort)
        req.setAttribute("booksFiction", bookService.getBooks("Fiction"));
        req.setAttribute("booksNonFiction", bookService.getBooks("Non-Fiction"));
        req.setAttribute("booksFantasy", bookService.getBooks("Fantasy"));
        req.getRequestDispatcher("/WEB-INF/view/books/listBook.jsp").forward(req, resp);
    }
}
