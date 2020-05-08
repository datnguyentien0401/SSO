package com.tsolution.sso._3service;

import com.tsolution.sso._1Entities.OauthClientEntity;
import com.tsolution.sso.exceptions.BusinessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface I_OauthClientService {

    ResponseEntity<Object> getAllClientId();

    ResponseEntity<Object> getOne(String clientId);

    ResponseEntity<Object> getAll();

    ResponseEntity<Object> create(OauthClientEntity oauthClientEntity) throws BusinessException;

    ResponseEntity<Object> update(String clientId, OauthClientEntity oauthClientEntityInput) throws Exception;

    ResponseEntity<Object> delete(String clientId) throws Exception;

    ResponseEntity<Object> find(OauthClientEntity oauthClientEntity, Pageable pageable);
}
