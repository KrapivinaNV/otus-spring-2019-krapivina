package otus.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.domain.Author;
import otus.domain.Book;
import otus.domain.Comment;
import otus.domain.Genre;
import otus.repository.AuthorRepository;
import otus.repository.BookRepository;
import otus.repository.CommentRepository;
import otus.repository.GenreRepository;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService {

	private final BookRepository bookRepository;

	private final AuthorRepository authorRepository;

	private final GenreRepository genreRepository;

	private final CommentRepository commentRepository;

	@Autowired
	public LibraryServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
			GenreRepository genreRepository, CommentRepository commentRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.genreRepository = genreRepository;
		this.commentRepository = commentRepository;
	}

	@Override
	public String addBook(String name, Set<String> authors, Set<String> genres) {
		Set<Author> authorList = ImmutableSet.copyOf(authorRepository.findAllById(authors));
		Set<Genre> genreList = ImmutableSet.copyOf(genreRepository.findAllById(genres));
		Book book = new Book(name, authorList, genreList);
		return bookRepository.save(book).getId();
	}

	@Override
	public String addComment(String text, String bookId) {
		return bookRepository.findById(bookId)
				.map(book -> commentRepository.save(new Comment(text, book)).getId())
				.orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public void deleteBook(String bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);
		commentRepository.deleteByBook(book);
		bookRepository.delete(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return ImmutableList.copyOf(bookRepository.findAll());
	}

	@Override
	public Optional<Book> getBook(String id) {
		return bookRepository.findById(id);
	}

	@Override
	public List<Comment> getAllCommentByBookId(String bookId) {
		return bookRepository.findById(bookId)
				.map(commentRepository::getAllByBook)
				.orElse(ImmutableList.of());
	}

}
