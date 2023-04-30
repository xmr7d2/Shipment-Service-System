package com.restful.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 托运人controller
 */
@Controller
public class ShipperController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "ok";
    }
}
