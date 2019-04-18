package otus.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import otus.domain.Book;
import otus.domain.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, UUID> {

	List<Comment> getAllByBook(Book book);

}
