package com.tsolution.sso._2Repository.impl;

import com.tsolution.sso._1Entities.OauthClientEntity;

import com.tsolution.sso._2Repository.I_OauthClientRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class I_OauthClientRepositoryCustomImpl implements I_OauthClientRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<String> getAllClientId() {
        String hql = "SELECT c.clientId FROM OauthClientEntity AS c";
        TypedQuery<String> query = em.createQuery(hql, String.class);
        return query.getResultList();
    }

    @Override
    public Page<OauthClientEntity> findOauthClients(OauthClientEntity oauthClientEntity, Pageable pageable) {
        List<OauthClientEntity> resultList = getOauthClientEntities(oauthClientEntity, pageable);
        Long total = getTotalOauthClientEntities(oauthClientEntity);
        return new PageImpl<>(resultList, pageable, total);
    }

    private Long getTotalOauthClientEntities(OauthClientEntity oauthClientDto) {
        Query countQuery = em.createQuery(createHql("SELECT COUNT(*) "));
        setParameter(oauthClientDto, countQuery);
        return (Long) countQuery.getSingleResult();
    }

    private List<OauthClientEntity> getOauthClientEntities(OauthClientEntity oauthClientEntity, Pageable pageable) {
        TypedQuery<OauthClientEntity> query = em.createQuery(createHql(" SELECT c "), OauthClientEntity.class);
        setParameter(oauthClientEntity, query);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    private String createHql(String select) {
        return select +
                " FROM OauthClientEntity c " +
                " WHERE UPPER(c.clientId) LIKE :clientId " +
                " OR UPPER(c.scope) LIKE :scope OR UPPER(c.authorities) LIKE :authorities ";
    }

    private void setParameter(OauthClientEntity oauthClientEntity, Query query) {
        query.setParameter("clientId", "%" + oauthClientEntity.getClientId().toUpperCase() + "%");
        query.setParameter("scope", "%" + oauthClientEntity.getScope().toUpperCase() + "%");
        query.setParameter("authorities", "%" + oauthClientEntity.getAuthorities().toUpperCase() + "%");
    }
}
