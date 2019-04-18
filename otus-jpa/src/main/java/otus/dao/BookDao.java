package otus.repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import otus.domain.Book;

public interface BookDao {

	Book getBookById(UUID id);

	List<Book> getAllBooks();

	UUID addBook(String name, Set<UUID> authors, Set<UUID> genress);

	UUID addComment(UUID bookId, String textComment);

}
