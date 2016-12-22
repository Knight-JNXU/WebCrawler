package code.controller;

import code.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String getFilms(@RequestParam String name){
        return "";
    }
}
