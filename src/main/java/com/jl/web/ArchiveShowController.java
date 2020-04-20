package com.jl.web;

import com.jl.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author J-Lei
 * @date 2020/3/19 11:04
 */
@Controller
public class ArchiveShowController {


    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogService.archiveBLOG());
        model.addAttribute("blogCount", blogService.countBlog());
        return "archives";
    }
}
