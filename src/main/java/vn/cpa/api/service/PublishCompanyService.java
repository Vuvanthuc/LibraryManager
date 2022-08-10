package vn.cpa.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.cpa.api.entity.PublishCompany;
import vn.cpa.api.repository.publish_company.PublishCompanyRepository;
import vn.cpa.api.request.PublishCompanyCreateRequest;
import vn.cpa.api.util.DateUtil;

import java.sql.Date;
import java.sql.Timestamp;

@Service
public class PublishCompanyService {

    @Autowired
    private PublishCompanyRepository publishCompanyRepository;

    public void create(PublishCompanyCreateRequest request) {
        PublishCompany publishCompany = new PublishCompany();
        publishCompany.setPublishName(request.getName());
        publishCompany.setAddress(request.getAddress());
        publishCompany.setEmail(request.getEmail());
        publishCompany.setDateFounding(DateUtil.getCurrentTimes());
        publishCompanyRepository.save(publishCompany);
    }
}
