package vn.cpa.api.repository.publish_company.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import vn.cpa.api.mapper.EntityMapper;
import vn.cpa.api.repository.publish_company.PublishCompanyRepositoryCustom;
import vn.cpa.api.request.PublishCompanyFindAllRequest;
import vn.cpa.api.response.PublishComFindAllResponse;
import vn.cpa.api.util.PageUtils;
import vn.cpa.api.util.ValueUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PublishCompanyRepositoryImpl implements PublishCompanyRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Page<PublishComFindAllResponse> findAll(PublishCompanyFindAllRequest request, Pageable pageable) {
        StringBuilder sb = new StringBuilder();
        sb.append("select pc.company_id, pc.code, pc.publish_name, pc.email, pc.address " +
                "from publish_company pc");
        Query query = entityManager.createNativeQuery(sb.toString());

//        PageUtils.buildQuery(pageable, query);

        List<Object[]> objects = query.getResultList();
        List<PublishComFindAllResponse> res = new ArrayList<>();

        for (Object[] obj : objects) {
            PublishComFindAllResponse response = new PublishComFindAllResponse();
            response.setId(ValueUtil.getLongByObject(obj[0]));
            response.setCode(ValueUtil.getStringByObject(obj[1]));
            response.setName(ValueUtil.getStringByObject(obj[2]));
            response.setEmail(ValueUtil.getStringByObject(obj[3]));
            response.setAddress(ValueUtil.getStringByObject(obj[4]));
            res.add(response);
        }

        return new PageImpl<>(res, pageable, countSearch(request).longValue());
    }

    private BigInteger countSearch(PublishCompanyFindAllRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(0) " +
                "from publish_company pc");
        Query query = entityManager.createNativeQuery(sb.toString());
        return (BigInteger) query.getSingleResult();
    }
}
