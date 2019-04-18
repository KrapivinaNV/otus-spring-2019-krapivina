package otus.shell;

import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.service.BookService;

@ShellComponent
public class Commands {

	private BookService bookService;


	@Autowired
	public Commands(BookService bookService) {
		this.bookService = bookService;
	}

	@ShellMethod("get-all-books")
	public String getAllBooks() {
		return bookService.getAllBooks().toString();
	}

	@ShellMethod("get-book")
	public String getBook(UUID id) {
		return bookService.getBook(id).toString();
	}

	@ShellMethod("add-new-book")
	public void addNewBook(String name, Set<UUID> authors, Set<UUID> genres) {
		bookService.addBook(name, authors, genres);
	}

	@ShellMethod("add-new-comment")
	public UUID addNewComment(String text, UUID bookId) {
		return bookService.addComment(text, bookId);
	}

}
