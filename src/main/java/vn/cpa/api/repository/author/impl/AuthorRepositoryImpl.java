package vn.cpa.api.repository.author.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import vn.cpa.api.mapper.EntityMapper;
import vn.cpa.api.repository.author.AuthorRepositoryCustom;
import vn.cpa.api.request.AuthorFindAllRequest;
import vn.cpa.api.response.AuthorFindAllResponse;
import vn.cpa.api.util.PageUtils;
import vn.cpa.api.util.ValueUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Transactional
public class AuthorRepositoryImpl implements AuthorRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Page<AuthorFindAllResponse> findAll(AuthorFindAllRequest request, Pageable pageable) {

        StringBuilder sb = new StringBuilder();
        sb.append("select au.id_author, au.name_author, au.code , au.address , au.position, au.note " +
                " from author as au where 1 = 1 ");

        if (StringUtils.isNotBlank(request.getKeyword())) {
            sb.append(" AND ( au.name like :keyword or au.code like :keyword )");
        }

        setOrderBy(request, sb);

        Query query = entityManager.createNativeQuery(sb.toString());

//        PageUtils.buildQuery(pageable, query);

        if (StringUtils.isNotBlank(request.getKeyword())) {
            query.setParameter("keyword", request.getKeyword());
        }

        List<Object[]> objects = query.getResultList();
        List<AuthorFindAllResponse> result = new ArrayList<>();
        for (Object[] obj : objects) {
            AuthorFindAllResponse res = new AuthorFindAllResponse();
            res.setId(ValueUtil.getLongByObject(obj[0]));
            res.setName(ValueUtil.getStringByObject(obj[1]));
            res.setCode(ValueUtil.getStringByObject(obj[2]));
            res.setAddress(ValueUtil.getStringByObject(obj[3]));
            res.setPosition(ValueUtil.getStringByObject(obj[4]));
            res.setNote(ValueUtil.getStringByObject(obj[5]));
            result.add(res);
        }

        return new PageImpl<>(result, pageable, countSearch(request).longValue());
    }

    private BigInteger countSearch(AuthorFindAllRequest request) {

        StringBuilder sb = new StringBuilder();
        sb.append("select count(0) " +
                "from author au where 1 = 1 ");

        if (StringUtils.isNotBlank(request.getKeyword())) {
            sb.append(" AND ( au.name like :keyword or au.code like :keyword )");
        }

        Query query = entityManager.createNativeQuery(sb.toString());

        if (StringUtils.isNotBlank(request.getKeyword())) {
            query.setParameter("keyword", request.getKeyword());
        }

        return (BigInteger) query.getSingleResult();

    }

    private void setOrderBy(AuthorFindAllRequest request, StringBuilder sb) {

        if (StringUtils.isNotBlank(request.getSortField())) {
            sb.append(" ORDER BY ");
            if (request.getSortField().equalsIgnoreCase("name")) {
                sb.append(" au.name_author ");
            }
            if (request.getSortField().equalsIgnoreCase("code")) {
                sb.append(" au.code ");
            }
            if (request.getSortField().equalsIgnoreCase("position")) {
                sb.append(" au.position ");
            }
            if (request.getSortField().equalsIgnoreCase("address")) {
                sb.append(" au.address ");
            }
            if (request.getSortField().equalsIgnoreCase("note")) {
                sb.append(" au.note ");
            }

            sb.append(request.getSortOrder());
        } else {
            sb.append(" ORDER BY au.id_author DESC");
        }
    }
}
