package otus.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import otus.domain.Book;

public interface BookService {

	UUID addBook(String name, Set<UUID> authors, Set<UUID> genres);

	UUID addComment(String text, UUID bookId);

	List<Book> getAllBooks();

	Book getBook(UUID id);

}
