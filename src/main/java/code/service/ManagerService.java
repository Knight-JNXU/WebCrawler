package code.service;

import code.dao.ManagerDao;
import code.model.ShRdModel;
import code.model.StaticModel;
import code.utils.CSDNSpider;
import code.utils.CsdnBlogCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ManagerService.class);

    private CSDNSpider csdnSpider;
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private CsdnBlogCrawler csdnBlogCrawler;

    //录入
    public void input(){
        StaticModel.startTime = new Date().getTime();
        csdnSpider = new CSDNSpider(csdnBlogCrawler);
        csdnSpider.addUrl(BT_URL).thread(THREAD_NUM).run();
    }

    //检查是否正在录入
    public boolean isRunning(){
        if(csdnSpider == null){
            return false;
        }else{
            return csdnSpider.isRunning();
        }
    }

    //停止录入
    public void stopInput(){
        if(isRunning()){
            csdnSpider.stop();
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

    @Scheduled(cron = "${SCHEDULE}")
    public void targetMethod(){
        LOGGER.info("**********************************targetMethod start!*********************************");
        LOGGER.info("time: {}", new Date().toString());
        input();
    }

}
