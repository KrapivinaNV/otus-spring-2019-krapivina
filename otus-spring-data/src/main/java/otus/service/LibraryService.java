package otus.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import otus.domain.Book;
import otus.domain.Comment;

public interface LibraryService {

	UUID addBook(String name, Set<UUID> authors, Set<UUID> genres);

	UUID addComment(String text, UUID bookId);

	List<Book> getAllBooks();

	Optional<Book> getBook(UUID id);

	List<Comment> getAllCommentByBookId(UUID bookId);

}
