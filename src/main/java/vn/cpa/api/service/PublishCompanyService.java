package vn.cpa.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.cpa.api.entity.PublishCompany;
import vn.cpa.api.repository.publish_company.PublishCompanyRepository;
import vn.cpa.api.request.PublishCompanyCreateRequest;
import vn.cpa.api.request.PublishCompanyFindAllRequest;
import vn.cpa.api.request.PublishCompanyUpdateRequest;
import vn.cpa.api.response.PublishComFindAllResponse;
import vn.cpa.api.util.DateUtil;
import vn.cpa.api.util.PageUtils;

@Service
@Transactional
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

    public void update(PublishCompanyUpdateRequest request) {
        PublishCompany publishCompany = publishCompanyRepository.findById(request.getId()).get();
        publishCompany.setPublishName(request.getName());
        publishCompany.setAddress(request.getAddress());
        publishCompany.setEmail(request.getEmail());
        publishCompany.setDateFounding(DateUtil.getCurrentTimes());
        publishCompanyRepository.save(publishCompany);
    }

    public PublishCompany findDetail(Long id) {
        return publishCompanyRepository.findById(id).get();
    }

    public Page<PublishComFindAllResponse> findAll(PublishCompanyFindAllRequest request) {
        Pageable pageable = PageUtils.buildPage(request.getPage(), request.getSize());
        return publishCompanyRepository.findAll(request, pageable);
    }
}
