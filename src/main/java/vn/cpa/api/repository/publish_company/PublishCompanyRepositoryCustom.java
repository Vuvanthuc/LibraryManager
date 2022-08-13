package vn.cpa.api.repository.publish_company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.cpa.api.request.PublishCompanyFindAllRequest;
import vn.cpa.api.response.PublishComFindAllResponse;

public interface PublishCompanyRepositoryCustom {
    Page<PublishComFindAllResponse> findAll(PublishCompanyFindAllRequest request, Pageable pageable);
}
