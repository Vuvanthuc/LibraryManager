package vn.cpa.api.repository.author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.cpa.api.entity.Author;
import vn.cpa.api.request.AuthorFindAllRequest;
import vn.cpa.api.response.AuthorFindAllResponse;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, AuthorRepositoryCustom {
}
