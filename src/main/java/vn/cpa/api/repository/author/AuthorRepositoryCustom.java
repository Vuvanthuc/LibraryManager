package vn.cpa.api.repository.author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.cpa.api.request.AuthorFindAllRequest;
import vn.cpa.api.response.AuthorFindAllResponse;

public interface AuthorRepositoryCustom {
    Page<AuthorFindAllResponse> findAll(AuthorFindAllRequest request, Pageable pageable);
}
