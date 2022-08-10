package vn.cpa.api.repository.publish_company;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.cpa.api.entity.PublishCompany;

public interface PublishCompanyRepository extends JpaRepository<PublishCompany, Long> {
}
