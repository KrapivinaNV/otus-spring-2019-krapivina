package otus.shell;

import java.util.List;
import java.util.Set;
import java.util.UUID;
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
	public String getBook(UUID id) {
		return libraryService.getBook(id).map(Book::toString).orElse("not found");
	}

	@ShellMethod("add-new-book")
	public void addNewBook(String name, Set<UUID> authors, Set<UUID> genres) {
		libraryService.addBook(name, authors, genres);
	}

	@ShellMethod("add-new-comment")
	public UUID addNewComment(String text, UUID bookId) {
		return libraryService.addComment(text, bookId);
	}

	@ShellMethod("get-comment-by-book")
	public List<Comment> getCommentByBook(UUID bookId) {
		return libraryService.getAllCommentByBookId(bookId);
	}

}
