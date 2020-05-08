package com.tsolution.sso._2Repository;

import com.tsolution.sso._1Entities.MenuEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface I_MenuRepositoryCustom {

   Page<MenuEntity> findMenuEntityCustom(MenuEntity menuEntity, Pageable pageable);

}
