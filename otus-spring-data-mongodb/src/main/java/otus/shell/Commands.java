package otus.shell;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.domain.Book;
import otus.domain.Comment;
import otus.service.LibraryService;

@ShellComponent
public class Commands {

	private LibraryService libraryService;

	@Autowired
	public Commands(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	@ShellMethod("get-all-books")
	public List<Book> getAllBooks() {
		return libraryService.getAllBooks();
	}

	@ShellMethod("get-book")
	public String getBook(String id) {
		return libraryService.getBook(id).map(Book::toString).orElse("not found");
	}

	@ShellMethod("delete-book")
	public String deleteBook(String id) {
		return libraryService.getBook(id).map(Book::toString).orElse("not found");
	}

	@ShellMethod("add-new-book")
	public String addNewBook(String name, Set<String> authors, Set<String> genres) {
		return libraryService.addBook(name, authors, genres);
	}

	@ShellMethod("add-new-comment")
	public String addNewComment(String text, String bookId) {
		return libraryService.addComment(text, bookId);
	}

	@ShellMethod("get-comment-by-book")
	public List<Comment> getCommentByBook(String bookId) {
		return libraryService.getAllCommentByBookId(bookId);
	}

}
