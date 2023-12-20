package org.example.mvc.controller;

import org.example.mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@org.example.reflect.annotation.Controller
public class HomeController {

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "home";
    }
}
