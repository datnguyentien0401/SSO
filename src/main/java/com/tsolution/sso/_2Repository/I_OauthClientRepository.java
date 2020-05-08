package com.tsolution.sso._2Repository;

import com.tsolution.sso._1Entities.OauthClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface I_OauthClientRepository extends JpaRepository<OauthClientEntity,String>, I_OauthClientRepositoryCustom {

}
