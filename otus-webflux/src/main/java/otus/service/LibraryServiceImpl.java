package otus.service;

import com.google.common.collect.ImmutableSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.domain.Book;
import otus.domain.Comment;
import otus.repository.AuthorRepository;
import otus.repository.BookRepository;
import otus.repository.CommentRepository;
import otus.repository.GenreRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final CommentRepository commentRepository;

    @Autowired
    public LibraryServiceImpl(
            BookRepository bookRepository,
            AuthorRepository authorRepository,
            GenreRepository genreRepository,
            CommentRepository commentRepository
    ) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Mono<Book> addBook(String name, Set<String> authors, Set<String> genres) {
        return Mono.zip(
                authorRepository.findAllById(authors).collectList(),
                genreRepository.findAllById(genres).collectList()
        ).flatMap(__ -> bookRepository
                .save(
                        new Book(
                                name,
                                ImmutableSet.copyOf(__.getT1()),
                                ImmutableSet.copyOf(__.getT2())
                        )
                )
        );
    }

    @Override
    public Mono<Comment> addComment(String text, String bookId) {
        return bookRepository.findById(bookId)
                .map(book -> new Comment(text, book))
                .flatMap(commentRepository::save);
    }

    @Override
    public Mono<Void> deleteBook(String bookId) {
        return bookRepository.findById(bookId)
                .flatMap(book -> Mono.when(commentRepository.deleteByBook(book), bookRepository.delete(book)));
    }

    @Override
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> getBook(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public Flux<Comment> getAllCommentByBookId(String bookId) {
        return bookRepository.findById(bookId)
                .flatMapMany(commentRepository::getAllByBook);
    }

}
