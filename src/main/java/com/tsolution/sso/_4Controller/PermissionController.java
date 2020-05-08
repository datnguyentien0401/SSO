package com.tsolution.sso._4Controller;

import com.tsolution.sso._1Entities.PermissionEntity;
import com.tsolution.sso._3service.I_PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    I_PermissionService permissionService;

    @GetMapping("/{id}")
    ResponseEntity<Object> getOne(@PathVariable("id") Long id) {
        return this.permissionService.getOne(id);
    }

    @GetMapping("/find")
    ResponseEntity<Object> findAll(
            @RequestParam(value = "clientId", required = false) String clientId,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "pageNumber") Integer pageNumber,
            @RequestParam(value = "pageSize") Integer pageSize) throws Exception {
        clientId = clientId == null ? "" : clientId;
        text = text == null ? "" : text;
        return this.permissionService.find(clientId, text, text, PageRequest.of(pageNumber,pageSize));
    }

    @GetMapping("/getAll")
    ResponseEntity<Object> getAll() {
        return this.permissionService.getAll();
    }

    @GetMapping("/client-id")
    ResponseEntity<Object> findByClientId(@RequestParam(value = "client-id") String clientId) {
        return this.permissionService.findByClientId(clientId);
    }

    @PostMapping
    ResponseEntity<Object> create(@RequestBody PermissionEntity permissionEntity) throws Exception {
        return this.permissionService.create(permissionEntity);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Object> update(@PathVariable("id") Long id,
                             @RequestBody PermissionEntity permissionEntity) throws Exception {
        return this.permissionService.update(id, permissionEntity);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") Long id) throws Exception {
        return this.permissionService.delete(id);
    }

}
