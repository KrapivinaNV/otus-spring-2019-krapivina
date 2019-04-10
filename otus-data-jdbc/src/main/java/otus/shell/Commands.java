package otus.shell;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.dao.AuthorDaoJdbc;
import otus.dao.BookDaoJdbc;
import otus.dao.GenreDaoJdbc;

@ShellComponent
public class Commands {

	private final BookDaoJdbc bookDaoJdbc;

	private final AuthorDaoJdbc authorDaoJdbc;

	private final GenreDaoJdbc genreDaoJdbc;

	@Autowired
	public Commands(BookDaoJdbc bookDaoJdbc, AuthorDaoJdbc authorDaoJdbc, GenreDaoJdbc genreDaoJdbc) {
		this.bookDaoJdbc = bookDaoJdbc;
		this.authorDaoJdbc = authorDaoJdbc;
		this.genreDaoJdbc = genreDaoJdbc;
	}

	@ShellMethod("get-all-books")
	public String getAllBooks() {
		return bookDaoJdbc.getAllBooks().toString();
	}

	@ShellMethod("add-new-book")
	public void addNewBook(String name, List<UUID> authors, List<UUID> genres) {
		bookDaoJdbc.addBook(name, authors, genres);
	}

	@ShellMethod("get-book")
	public String getBook(UUID id) {
		return bookDaoJdbc.getBookById(id).toString();
	}

	@ShellMethod("get-all-authors")
	public String getAllAuthors() {
		return authorDaoJdbc.getAllAuthors().toString();
	}

	@ShellMethod("get-all-genres")
	public String getAllGenres() {
		return genreDaoJdbc.getAllGenres().toString();
	}

}
