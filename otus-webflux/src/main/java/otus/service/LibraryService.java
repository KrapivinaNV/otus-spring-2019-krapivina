package otus.service;

import java.util.Set;
import otus.domain.Book;
import otus.domain.Comment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LibraryService {

	Mono<Book> addBook(String name, Set<String> authors, Set<String> genres);

	Mono<Comment> addComment(String text, String bookId);

	Mono<Void> deleteBook(String bookId);

	Flux<Book> getAllBooks();

	Mono<Book> getBook(String id);

	Flux<Comment> getAllCommentByBookId(String bookId);

}
