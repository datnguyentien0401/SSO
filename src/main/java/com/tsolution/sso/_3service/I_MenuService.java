package com.tsolution.sso._3service;

import com.tsolution.sso._1Entities.MenuEntity;
import com.tsolution.sso.exceptions.BusinessException;
import org.springframework.http.ResponseEntity;

import org.springframework.data.domain.Pageable;

public interface I_MenuService {
    ResponseEntity<Object> getOne(Long menuId);

    ResponseEntity<Object> find(MenuEntity menuEntity, Pageable pageable) throws Exception;

    ResponseEntity<Object> create(MenuEntity menuEntity) throws BusinessException;

    ResponseEntity<Object> update(Long id, MenuEntity userEntityInput) throws Exception;

    ResponseEntity<Object> delete(Long id) throws Exception;

    ResponseEntity<Object> getAll();

    ResponseEntity<Object> findByClientId(String clientId);
}
