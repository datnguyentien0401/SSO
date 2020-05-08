package com.tsolution.sso._2Repository.impl;

import com.tsolution.sso._1Entities.MenuEntity;
import com.tsolution.sso._2Repository.I_MenuRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class I_MenuRepositoryCustomImpl implements I_MenuRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<MenuEntity> findMenuEntityCustom(MenuEntity menuEntity, Pageable pageable) {
        List<MenuEntity> result = getMenuEntities(menuEntity, pageable);
        Long total = getTotalMenuEntity(menuEntity);
        return new PageImpl<>(result, pageable, total);
    }

    private Long getTotalMenuEntity(MenuEntity menuEntity) {
        Query countQuery = em.createQuery(createHql("COUNT(*)"));
        setParameterQuery(menuEntity, countQuery);
        return (Long) countQuery.getSingleResult();
    }

    private List<MenuEntity> getMenuEntities(MenuEntity menuEntity, Pageable pageable) {
        TypedQuery<MenuEntity> query = em.createQuery(createHql("m"), MenuEntity.class);
        setParameterQuery(menuEntity, query);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    private String createHql(String select) {
        return " SELECT " + select +
                " FROM MenuEntity m " +
                " WHERE " +
                " UPPER(m.clientId) LIKE :clientId " +
                " AND ( UPPER(m.appType) LIKE :appType  " +
                " OR UPPER(m.code) LIKE :code " +
                " OR UPPER(m.url) LIKE :url ) ";
    }

    private void setParameterQuery(MenuEntity menuEntity, Query query) {
        query.setParameter("clientId", "%" + menuEntity.getClientId().toUpperCase() + "%");
        query.setParameter("appType", "%" + menuEntity.getAppType().toUpperCase() + "%");
        query.setParameter("code", "%" + menuEntity.getCode().toUpperCase() + "%");
        query.setParameter("url", "%" + menuEntity.getUrl().toUpperCase() + "%");
    }

}
