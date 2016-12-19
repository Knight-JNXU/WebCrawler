package codes.controller;

import codes.service.JokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Knight_JXNU on 2016/12/19.
 */
@Controller
@RequestMapping(value = "/jokerController")
public class JokerController {

    @Autowired
    private JokerService jokerService;

    @ResponseBody
    @RequestMapping(value = "/test")
    public void test(){
        jokerService.jokerRun();
    }
}
