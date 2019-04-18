package otus.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.Optional;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import otus.domain.Book;
import otus.domain.Comment;
import otus.repository.AuthorRepository;
import otus.repository.BookRepository;
import otus.repository.CommentRepository;
import otus.repository.GenreRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LibraryServiceImpl.class})
public class LibraryServiceTest {

	@Autowired
	private LibraryService libraryService;

	@MockBean
	private AuthorRepository authorRepository;

	@MockBean
	private GenreRepository genreRepository;

	@MockBean
	private BookRepository bookRepository;

	@MockBean
	private CommentRepository commentRepository;

	@Test
	public void addBookTest() {
		UUID bookId = UUID.randomUUID();
		Book book = mock(Book.class);
		when(book.getId()).thenReturn(bookId);

		when(authorRepository.findAllById(ImmutableSet.of()))
				.thenReturn(ImmutableSet.of());
		when(genreRepository.findAllById(ImmutableSet.of()))
				.thenReturn(ImmutableSet.of());
		when(bookRepository.save(any())).thenReturn(book);

		UUID addedBookId = libraryService.addBook("name1", ImmutableSet.of(), ImmutableSet.of());

		verify(authorRepository).findAllById(ImmutableSet.of());
		verify(genreRepository).findAllById(ImmutableSet.of());

		assertEquals(addedBookId, bookId);
	}

	@Test
	public void addCommentTest() {
		UUID bookId = UUID.randomUUID();
		Book book = new Book("book", ImmutableSet.of(), ImmutableSet.of());
		Book mockBook = mock(Book.class);
		when(mockBook.getId()).thenReturn(bookId);

		UUID commentId = UUID.randomUUID();
		String commentText = "comment1";
		Comment comment = mock(Comment.class);
		when(comment.getId()).thenReturn(commentId);

		when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
		when(commentRepository.save(any())).thenReturn(comment);

		UUID addedCommentId = libraryService.addComment(commentText, bookId);

		verify(bookRepository).findById(bookId);

		assertEquals(addedCommentId, commentId);
	}

	@Test
	public void getBookTest() {
		UUID bookId = UUID.randomUUID();
		Book book = new Book("name", ImmutableSet.of(), ImmutableSet.of());
		when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

		assertEquals(libraryService.getBook(bookId).get(), book);
		verify(bookRepository).findById(bookId);
	}

	@Test
	public void getAllBooksTest() {
		ImmutableList<Book> books = ImmutableList.of(
				new Book("name1", ImmutableSet.of(), ImmutableSet.of()),
				new Book("name2", ImmutableSet.of(), ImmutableSet.of())
		);
		when(bookRepository.findAll()).thenReturn(books);

		assertEquals(libraryService.getAllBooks(), books);
		verify(bookRepository).findAll();
	}

}
