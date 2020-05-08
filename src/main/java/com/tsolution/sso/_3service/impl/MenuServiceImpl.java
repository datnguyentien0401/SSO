package com.tsolution.sso._3service.impl;

import com.tsolution.sso._1Entities.MenuEntity;
import com.tsolution.sso._2Repository.I_MenuRepository;
import com.tsolution.sso._3service.I_MenuService;
import com.tsolution.sso.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class MenuServiceImpl implements I_MenuService {

    @Autowired
    I_MenuRepository menuRepository;

    @Override
    public ResponseEntity<Object> getOne(Long menuId) {
        return new ResponseEntity<>(this.menuRepository.findById(menuId), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Object> find(MenuEntity menuEntity, Pageable pageable) {
        return new ResponseEntity<>(this.menuRepository.findMenuEntityCustom(menuEntity, pageable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(this.menuRepository.findAll(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> findByClientId(String clientId) {
        clientId = clientId == null ? "" : clientId;
        List<MenuEntity> results = this.menuRepository.findAllByClientId(clientId);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Object> create(MenuEntity menuEntity) throws BusinessException {
        return new ResponseEntity<>(this.menuRepository.save(menuEntity), HttpStatus.OK);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Object> update(Long id, MenuEntity menuEntityInput) throws Exception {
        MenuEntity menuEntity = this.menuRepository.getOne(id);
        menuEntity.setUrl(menuEntityInput.getUrl());
        menuEntity.setCode(menuEntityInput.getCode());
        menuEntity.setAppType(menuEntityInput.getAppType());
        menuEntity.setClientId(menuEntityInput.getClientId());
        menuEntity.setParentMenu(menuEntityInput.getParentMenu());
        return new ResponseEntity<>(this.menuRepository.save(menuEntity), HttpStatus.OK);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Object> delete(Long id) throws Exception {
        this.menuRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
