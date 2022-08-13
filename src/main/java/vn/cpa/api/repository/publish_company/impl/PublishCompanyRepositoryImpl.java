package vn.cpa.api.repository.publish_company.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.cpa.api.mapper.EntityMapper;
import vn.cpa.api.repository.publish_company.PublishCompanyRepositoryCustom;
import vn.cpa.api.request.PublishCompanyFindAllRequest;
import vn.cpa.api.response.PublishComFindAllResponse;

import javax.persistence.PersistenceContext;

public class PublishCompanyRepositoryImpl implements PublishCompanyRepositoryCustom {


    @Override
    public Page<PublishComFindAllResponse> findAll(PublishCompanyFindAllRequest request, Pageable pageable) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        return null;
    }
}
