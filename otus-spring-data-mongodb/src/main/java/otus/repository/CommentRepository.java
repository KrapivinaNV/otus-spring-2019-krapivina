package otus.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import otus.domain.Book;
import otus.domain.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

	List<Comment> getAllByBook(Book book);

	void deleteByBook(Book book);

}
