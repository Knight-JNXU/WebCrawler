package code.controller;

import code.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Knight_JXNU on 2016/12/19.
 */
@Controller
@RequestMapping(value = "/manag")
public class ManagerController {

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

    @ResponseBody
    @RequestMapping(value = "/isRunning")
    public void isRunning(HttpServletResponse response) throws Exception{
        response.setContentType("text/xml;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw=response.getWriter(); //输出中文，这一句一定要放到
        if(managerService.isRunning()){
            pw.print("录入中，请稍等!");
        }else{
            pw.print("录入结束!");
        }
        pw.close();
    }

    @ResponseBody
    @RequestMapping(value = "/stop")
    public void stop(HttpServletResponse response) throws Exception{
        managerService.stopInput();
        response.setContentType("text/xml;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw=response.getWriter(); //输出中文，这一句一定要放到
        if(managerService.isRunning()){
            pw.print("录入停止成功!");
        }else{
            pw.print("录入停止失败!");
        }
    }

}
