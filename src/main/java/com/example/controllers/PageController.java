package com.example.controllers;

import com.example.model.EntityClass;
import com.example.repository.EntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class PageController {

    @Autowired
    EntRepository entRepository;

    @GetMapping(value = {"/", "/redir*"})  // редирект на другую страницу с корня или страниц с префиксом redir
    public String redirect() {
        return "redirect:/hello"; //"redirect:/print.html"
    }

    @GetMapping("/forward")  // форвард на другую страницу
    public String forward() {
        return "forward:/hello"; //"forward:/print.html"
    }

    @GetMapping("hello")  // открытие старницы с динамическим аттрибутом
    public String hello(Model model) {
        model.addAttribute("title", "USER");
        return "hello";
    }

    @GetMapping("findViaForm")  // поиск по имени через форму и отправка ч1
    public String findViaForm(Model model) {
        Iterable<EntityClass> enti = entRepository.findAll();
        model.addAttribute("elements", enti);
        return "formpage";
    }

    @PostMapping("findViaForm")  // поиск по имени через форму и отправка ч2
    public String findViaForm(Model model, @RequestParam String name) {
        EntityClass enti = entRepository.findByName(name);
        if (enti == null) {
            model.addAttribute("elem", "НЕТ ТАКОГО");
        } else
            model.addAttribute("elements", enti);
        return "print";
    }

    @GetMapping("print")  // вывод всех записей БД
    public String print(Model model) {
        Iterable<EntityClass> enti = entRepository.findAll();
        model.addAttribute("elements", enti);
        return "print";
    }

    @GetMapping("modelAndView")  // работа с modelAndView
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "Spring Security Hello World Tutorial");
        modelAndView.setViewName("hello");
        return modelAndView;
    }
}