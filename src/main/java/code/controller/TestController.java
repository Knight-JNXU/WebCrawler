package code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Knight_JXNU on 2016/12/19.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    @ResponseBody
    @RequestMapping(value = "/test1")
    public String test1(){
        return "test1";
    }
    @ResponseBody
    @RequestMapping(value = "/test2")
    public String test2(){
        return "test2";
    }
}
