package otus.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otus.dao.BookDao;
import otus.domain.Book;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	private final BookDao bookDao;


	@Autowired
	public BookServiceImpl(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public UUID addBook(String name, Set<UUID> authors, Set<UUID> genres) {
		return bookDao.addBook(name, authors, genres);
	}

	@Override
	public UUID addComment(String text, UUID bookId) {
		return bookDao.addComment(bookId, text);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	@Override
	public Book getBook(UUID id) {
		return bookDao.getBookById(id);
	}
}
