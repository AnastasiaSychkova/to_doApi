package com.example.to_do_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HTTPController {
    @GetMapping("/task/add")
    public String addTask() {
        return "addTask";
    }
    @GetMapping("/task/update")
    public String updateTask() {
        return "updateTask";
    }

}
