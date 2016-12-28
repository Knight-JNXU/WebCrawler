package code.controller;

import code.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Knight_JXNU on 2016/12/28.
 */
@Controller
@RequestMapping(value = "/manag")
public class ManagerController extends BaseController{
    @Autowired
    ManagerService managerService;

    @RequestMapping(value = "/index")
    public String index(){
        return "manager";
    }

    @RequestMapping(value = "/input")
    public String input(){
        managerService.input();
        return "manager";
    }
}
