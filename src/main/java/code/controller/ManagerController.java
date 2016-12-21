package code.controller;

import code.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Knight_JXNU on 2016/12/19.
 */
@Controller
@RequestMapping(value = "/manag")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @RequestMapping(value = "/input")
    public String input(){
//        managerService.input();
        return "manager";
    }

    @ResponseBody
    @RequestMapping(value = "/isRunning")
    public String isRunning(){
//        return (""+managerService.isRunning());
        return "";
    }

}
