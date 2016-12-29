package code.controller;

import code.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Knight_JXNU on 2016/12/29.
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController extends BaseController{
    @Autowired
    SearchService searchService;

    @RequestMapping(value = "/getBlogs", method = RequestMethod.POST)
    public String getBlogs(@RequestParam String title, Model model){
        model.addAttribute("list", searchService.getBlogs(title));
        return "results";
    }
}
