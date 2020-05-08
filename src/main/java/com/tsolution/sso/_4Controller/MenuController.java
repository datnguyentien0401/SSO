package com.tsolution.sso._4Controller;

import com.tsolution.sso._1Entities.MenuEntity;
import com.tsolution.sso._3service.I_MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    I_MenuService menuService;


    @GetMapping("/{id}")
    ResponseEntity<Object> getOne(@PathVariable("id") Long menuId) {
        return this.menuService.getOne(menuId);
    }

    @GetMapping("/find")
    ResponseEntity<Object> findAll(
            @RequestParam(value = "clientId", required = false) String clientId,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "pageNumber") Integer pageNumber,
            @RequestParam(value = "pageSize") Integer pageSize) throws Exception {
        text = text == null ? "" : text;
        clientId = clientId == null ? "" : clientId;

        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setClientId(clientId);
        menuEntity.setUrl(text);
        menuEntity.setCode(text);
        menuEntity.setAppType(text);
        return this.menuService.find(menuEntity, PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/getAll")
    ResponseEntity<Object> getAll() {
        return this.menuService.getAll();
    }

    @GetMapping("/client-id")
    ResponseEntity<Object> findByClientId(@RequestParam(value = "client-id") String clientId) {
        return this.menuService.findByClientId(clientId);
    }

    @PostMapping
    ResponseEntity<Object> create(@RequestBody MenuEntity menuEntity) throws Exception {
        return this.menuService.create(menuEntity);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Object> update(@PathVariable("id") Long id,
                             @RequestBody MenuEntity menuEntity) throws Exception {
        return this.menuService.update(id, menuEntity);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") Long id) throws Exception {
        return this.menuService.delete(id);
    }

}
