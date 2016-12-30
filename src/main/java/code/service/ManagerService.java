package code.service;

import code.dao.EmailDao;
import code.dao.ManagerDao;
import code.utils.MySpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Knight_JXNU on 2016/12/28.
 */
@Service
public class ManagerService extends BaseService {
    @Autowired
    private MySpider mySpider;

    //录入
    public void input(){
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

}
