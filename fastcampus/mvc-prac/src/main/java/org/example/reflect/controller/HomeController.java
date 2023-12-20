package org.example.reflect.controller;

import org.example.reflect.annotation.Controller;
import org.example.reflect.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response){
        return "home";
    }
}
