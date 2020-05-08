package com.tsolution.sso._2Repository;

import com.tsolution.sso._1Entities.OauthClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface I_OauthClientRepositoryCustom {

    List<String> getAllClientId();
    Page<OauthClientEntity> findOauthClients(OauthClientEntity oauthClientEntity, Pageable pageable);
}
