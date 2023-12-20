//package com.example.SpringBootExample1.controller;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class Example1Controller implements ErrorController
//{
//    public static final String PATH = "/error";
//
//    @RequestMapping(value = "/msg2", method = RequestMethod.GET)
//    public String getMsg2(){
//        return "springboot2";
//    }
//
//    @GetMapping(value = "/msg1")
//    public String getMsg1() {
//        return "springboot1";
//    }
//
//    @GetMapping(value = PATH)
//    public String getErrorMessage() {
//        return "Request Resource Does not Exist";
//    }
//
//    public String getErrorPath() {
//        return PATH;
//    }
//}
