package otus.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import otus.domain.Author;
import otus.domain.Book;
import otus.domain.Genre;
import otus.repository.AuthorRepository;
import otus.repository.BookRepository;
import otus.repository.GenreRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LibraryServiceImplTest {

    @InjectMocks
    private LibraryServiceImpl libraryService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private GenreRepository genreRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addBook() {
        when(authorRepository.findAllById(ImmutableSet.of("a1", "a2")))
                .thenReturn(Flux.just(new Author("Author 1"), new Author("Author 2")));
        when(genreRepository.findAllById(ImmutableSet.of("g1", "g2")))
                .thenReturn(Flux.just(new Genre("Genre1"), new Genre("Genre2")));

        Book book = new Book(
                "book1",
                ImmutableSet.of(new Author("Author 1"), new Author("Author 2")),
                ImmutableSet.of(new Genre("Genre1"), new Genre("Genre2"))
        );
        when(bookRepository.save(any(Book.class))).thenReturn(Mono.just(book));

        StepVerifier.create(libraryService
                .addBook("book1", ImmutableSet.of("a1", "a2"), ImmutableSet.of("g1", "g2")))
                .expectNext(book)
                .expectNextCount(1)
                .expectComplete();
    }

    @Test
    public void addComment() {
    }

    @Test
    public void deleteBook() {
    }

    @Test
    public void getAllBooks() {
    }

    @Test
    public void getBook() {


    }

    @Test
    public void getAllCommentByBookId() {
    }
}