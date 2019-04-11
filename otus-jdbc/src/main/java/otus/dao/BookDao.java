package otus.repository;

import java.util.List;
import java.util.UUID;
import otus.domain.Book;

public interface BookDao {

	Book getBookById(UUID id);

	List<Book> getAllBooks();

	UUID addBook(String name, List<UUID> authors, List<UUID> genress);

}
