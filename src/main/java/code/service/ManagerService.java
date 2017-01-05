package code.service;

import code.dao.ManagerDao;
import code.model.ShRdModel;
import code.model.StaticModel;
import code.utils.CsdnBlogCrawler;
import code.utils.MySpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Knight_JXNU on 2016/12/28.
 */
@Service
public class ManagerService extends BaseService{
    private MySpider mySpider;
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private CsdnBlogCrawler csdnBlogCrawler;

    //录入
    public void input(){
        StaticModel.startTime = new Date().getTime();
        mySpider = new MySpider(csdnBlogCrawler);
        mySpider.addUrl(BT_URL).thread(THREAD_NUM).run();
    }

    //检查是否正在录入
    public boolean isRunning(){
        if(mySpider==null){
            return false;
        }else{
            return mySpider.ifRunning();
        }
    }

    //停止录入
    public void stopInput(){
        if(isRunning()){
            mySpider.stop();
        }
    }

    public boolean login(String username, String password){
        if(username.equals(ADMIN_NAME) && password.equals(ADMIN_PASS)){
            return true;
        }else{
            return false;
        }
    }

    public List<ShRdModel> getRecords(){
        return managerDao.getRecords();
    }

//    @Scheduled(cron = "${EVE_FIVE_MINUTE}")
    @Scheduled(cron = "${SCHEDULE}")
    public void targetMethod(){
        System.out.println("**********************************targetMethod start!*********************************");
        System.out.println("time:" + new Date());
        input();
    }

}
