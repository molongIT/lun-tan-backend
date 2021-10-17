package com.pxl.controller;

import com.pxl.common.annotation.AnonymousAccess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/index")
    @AnonymousAccess
    public String index(){
        return "Hello World.";
    }

}
