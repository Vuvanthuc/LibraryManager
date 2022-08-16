package vn.cpa.api.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.cpa.api.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
}
