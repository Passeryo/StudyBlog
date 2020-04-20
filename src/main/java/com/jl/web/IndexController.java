package com.jl.web;

import com.jl.bo.BlogQuery;
import com.jl.service.BlogService;
import com.jl.service.TagService;
import com.jl.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author J-Lei
 * @date 2020/3/7 15:14
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    // @PathVariable Integer id ,@PathVariable String name 从 request 中获取
    public String index(@PageableDefault(size = 7,
            sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
        return "index";
    }
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blog";
    }
    @PostMapping("/search")
    public String search(@PageableDefault(size = 7,sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model){
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }
//    @GetMapping("/footer/newblog")
//    public String newBlogs(Model model){
//        model.addAttribute("newblogs",blogService.listRecommendBlogTop(3));
//        return "_fragments :: newblogList";
//    }
        @GetMapping("/footer/newblog")
        public String newblogs(Model model) {
            model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
            return "_fragments :: newblogList";
        }
}
