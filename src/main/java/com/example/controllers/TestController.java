package com.example.controllers;

import com.example.model.EntityClass;
import com.example.repository.EntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

@Controller
public class TestController {

    @Autowired
    EntRepository entRepository;

    @GetMapping("add") // добавление при посещении
    @ResponseBody
    public String add() {
        EntityClass entityClass = new EntityClass();
        entityClass.setName("Serega");
        entityClass.setAge(44);
        entRepository.save(entityClass);
        return "OK";
    }

    @GetMapping("object") // отправка объекта
    @ResponseBody
    public EntityClass object() {
        EntityClass entityClass = null;
        Optional<EntityClass> optional = entRepository.findById(1);
        if (optional.isPresent()) {
            entityClass = optional.get();
        }
        return entityClass;
    }

    @GetMapping("getbyname")  // поиск по имени и отправка
    @ResponseBody
    public EntityClass getbyname(@RequestBody String name) {
        return entRepository.findByName(name);
    }

    @GetMapping("post")  // получение объекта от клиента и добавление его в БД
    @ResponseBody
    public EntityClass post(@RequestBody EntityClass entityClass) {
        entRepository.save(entityClass);
        return entityClass;
    }

    @GetMapping("headers")  // парсинг заголовков
    @ResponseBody
    public StringBuilder header(@RequestHeader HashMap<String, String> headersMap/*, @RequestHeader (value = "user-agent") String userAgent*/) {
        Iterator<Map.Entry<String, String>> f = headersMap.entrySet().iterator();
        StringBuilder builder = new StringBuilder();
        while(f.hasNext()){
            builder.append(f.next()).append(" ");
        }
        return builder;
    }

    @GetMapping("param")  // парсинг url параметров ?
    @ResponseBody
    public StringBuilder param(@RequestParam HashMap<String, String> params/*, @RequestParam("name") String name*/) {
        Iterator<Map.Entry<String, String>> f = params.entrySet().iterator();
        StringBuilder builder = new StringBuilder();
        while(f.hasNext()){
            builder.append(f.next()).append(" ");
        }
        return builder;
    }

    @GetMapping("/param/{name}/{name2}")  // парсинг url параметров {}
    @ResponseBody
    public String params(/*@PathVariable("name") String name*/@PathVariable Map<String, String> pathVars) {
        return pathVars.get("name") + " " + pathVars.get("name2");
    }

}
