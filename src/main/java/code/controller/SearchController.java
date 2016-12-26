package code.controller;

import code.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Knight_JXNU on 2016/12/22.
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController {
    @Autowired
    SearchService searchService;

    @RequestMapping(value = "/getFilms", method = RequestMethod.POST)
    public String getFilms(@RequestParam String name, Model model){
        model.addAttribute("list", searchService.getFilms(name));
        return "results";
    }

    @RequestMapping(value = "/re")
    public String re(){
        return "results";
    }
}
