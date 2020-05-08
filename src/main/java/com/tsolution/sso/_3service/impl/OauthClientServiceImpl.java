package com.tsolution.sso._3service.impl;

import com.tsolution.sso._1Entities.OauthClientEntity;
import com.tsolution.sso._2Repository.I_OauthClientRepository;
import com.tsolution.sso._3service.I_OauthClientService;
import com.tsolution.sso.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class OauthClientServiceImpl implements I_OauthClientService {

    @Autowired
    private I_OauthClientRepository oauthClientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<Object> getAllClientId() {
        return new ResponseEntity<>(this.oauthClientRepository.getAllClientId(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getOne(String clientId) {

        Optional<OauthClientEntity> res = this.oauthClientRepository.findById(clientId);
        if (res.isPresent()) {
            String secret = "";

            System.out.println(secret);
        }

        return new ResponseEntity<>(this.oauthClientRepository.findById(clientId), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(this.oauthClientRepository.findAll(), HttpStatus.OK);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Object> create(OauthClientEntity oauthClientEntity) throws BusinessException {
        Optional<OauthClientEntity> oauthClientEntityTemp = this.oauthClientRepository.findById(oauthClientEntity.getClientId());
        if(!oauthClientEntityTemp.isPresent()){
            String encodedClientSecret = passwordEncoder.encode(oauthClientEntity.getClientSecret());
            oauthClientEntity.setClientSecret(encodedClientSecret);
            return new ResponseEntity<>(this.oauthClientRepository.save(oauthClientEntity), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Object> update(String clientId, OauthClientEntity oauthClientEntityInput) throws Exception {
        Optional<OauthClientEntity> oauthClientEntity = this.oauthClientRepository.findById(clientId);
        if (oauthClientEntity.isPresent()) {
            oauthClientEntityInput.setClientId(clientId);
            return new ResponseEntity<>(this.oauthClientRepository.save(oauthClientEntityInput), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Object> delete(String clientId) {
        this.oauthClientRepository.deleteById(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> find(OauthClientEntity oauthClientEntity, Pageable pageable) {
        return new ResponseEntity<>(this.oauthClientRepository.findOauthClients(oauthClientEntity, pageable), HttpStatus.OK);
    }


}
