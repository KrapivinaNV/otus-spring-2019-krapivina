package otus.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import otus.domain.Book;
import otus.domain.Comment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

	Flux<Comment> getAllByBook(Book book);

	Mono<Void> deleteByBook(Book book);

}
