package com.jl.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author J-Lei
 * @date 2020/3/19 11:46
 */
@Controller
public class AboutController {
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
