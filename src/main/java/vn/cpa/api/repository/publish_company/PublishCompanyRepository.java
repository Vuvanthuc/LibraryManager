package vn.cpa.api.repository.publish_company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.cpa.api.entity.PublishCompany;
import vn.cpa.api.request.PublishCompanyFindAllRequest;
import vn.cpa.api.response.PublishComFindAllResponse;

public interface PublishCompanyRepository extends JpaRepository<PublishCompany, Long>, PublishCompanyRepositoryCustom {
}
