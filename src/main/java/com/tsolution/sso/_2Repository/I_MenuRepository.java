package com.tsolution.sso._2Repository;

import com.tsolution.sso._1Entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface I_MenuRepository extends JpaRepository<MenuEntity, Long>, I_MenuRepositoryCustom {

    MenuEntity findOneByCode(String code);

    List<MenuEntity> findAllByClientId(String clientId);

}
