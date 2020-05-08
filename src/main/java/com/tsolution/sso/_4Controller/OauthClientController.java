package com.tsolution.sso._4Controller;

import com.tsolution.sso._1Entities.OauthClientEntity;
import com.tsolution.sso._3service.I_OauthClientService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauthClient")
@Api(tags = "Client Rest Controller")
public class OauthClientController {

    @Autowired
    private I_OauthClientService oauthClientService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get one client entity", response = ResponseEntity.class)
    @ApiResponses(value = @ApiResponse(code = 200, message = "OK"))
    ResponseEntity<Object> getOne(@PathVariable("id") String clientId) {
        return this.oauthClientService.getOne(clientId);
    }

    @GetMapping("/find")
    @ApiOperation(value="Find Client with params", response = ResponseEntity.class)
    @ApiParam(name = "text")
    @ApiResponses(value = @ApiResponse(code = 200 , message = "OK"))
    ResponseEntity<Object> findAll(@RequestParam(value = "text", required = false) String text,
                              @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
                              @RequestParam(value = "pageSize", required = true) Integer pageSize) throws Exception {
        text = text == null ? "" : text;
        OauthClientEntity oauthClientEntity = new OauthClientEntity();
        oauthClientEntity.setClientId(text);
        oauthClientEntity.setScope(text);
        oauthClientEntity.setAuthorities(text);
        
        return this.oauthClientService.find(oauthClientEntity, PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/getAll")
    @ApiOperation(value="get all Client", response = ResponseEntity.class)
    @ApiResponses(value = @ApiResponse(code = 200 , message = "OK"))
    ResponseEntity<Object> getAll() {
        return this.oauthClientService.getAll();
    }

    @GetMapping("/getClientIds")
    @ApiOperation(value="get all Client Ids", response = ResponseEntity.class)
    @ApiResponses(value = @ApiResponse(code = 200 , message = "OK"))
    ResponseEntity<Object> getClientIds() {
        return this.oauthClientService.getAllClientId();
    }

    @PostMapping
    ResponseEntity<Object> create(@RequestBody OauthClientEntity oauthClientEntity) throws Exception {
        return this.oauthClientService.create(oauthClientEntity);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Object> update(@PathVariable("id") String clientId,
                             @RequestBody OauthClientEntity oauthClientEntity) throws Exception {
        return this.oauthClientService.update(clientId, oauthClientEntity);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") String clientId) throws Exception {
        return this.oauthClientService.delete(clientId);
    }
}
