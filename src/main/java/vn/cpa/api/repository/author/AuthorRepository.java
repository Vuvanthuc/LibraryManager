package vn.cpa.api.repository.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.cpa.api.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
