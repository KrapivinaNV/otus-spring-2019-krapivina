package otus.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import otus.domain.Book;
import otus.domain.Comment;

public interface LibraryService {

	String addBook(String name, Set<String> authors, Set<String> genres);

	String addComment(String text, String bookId);

	void deleteBook(String bookId);

	List<Book> getAllBooks();

	Optional<Book> getBook(String id);

	List<Comment> getAllCommentByBookId(String bookId);

}
