package code.controller;

import code.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Knight_JXNU on 2016/12/28.
 */
@Controller
@RequestMapping(value = "/manag")
public class ManagerController extends BaseController{
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    ManagerService managerService;

    @RequestMapping(value = "/index")
    public String index(){
        return "manager";
    }

    @RequestMapping(value = "/input")
    public String input(){
        LOGGER.info("input blog start!");
        managerService.input();
        LOGGER.info("input blog stop!");
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        if(managerService.login(username, password)){
            httpServletRequest.getSession().setAttribute("username", username);
            LOGGER.info("{} login successful!", username);
            return index();
        }else{
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/htmls/login.html");
            LOGGER.info("{} login failed!", username);
            return null;
        }
    }

    @RequestMapping(value = "/analysis")
    public String analysis(Model model){
        model.addAttribute("records", managerService.getRecords());
        return "analysis";
    }
}
