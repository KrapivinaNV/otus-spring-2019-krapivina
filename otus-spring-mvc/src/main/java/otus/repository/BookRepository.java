package otus.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import otus.domain.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, UUID> {

	@Modifying
	@Query("update Book book set book.name = :name where book.id = :id")
	void setNameForBook(@Param("name")String name, @Param("id")UUID id);

}
