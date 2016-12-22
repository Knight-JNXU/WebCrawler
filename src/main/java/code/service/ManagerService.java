package code.service;

import code.dao.ManagerDao;
import code.utils.BtTianTangPageProcesser;
import code.utils.MySpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Knight_JXNU on 2016/12/19.
 */
@Service
public class ManagerService extends BaseService{

    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private MySpider mySpider;

//    private MySpider mySpider = MySpider.create(btTianTangPageProcesser);

    public void input(){
//        if(!isRunning()){
//            mySpider = MySpider.create(new BtTianTangPageProcesser(managerDao));
//            mySpider.addUrl(BT_URL).thread(THREAD_NUM).run();
//        }
        mySpider.addUrl(BT_URL).thread(THREAD_NUM).run();
    }

    public boolean isRunning(){
        if(mySpider==null){
            return false;
        }else{
            return mySpider.ifRunning();
        }
//        return true;
    }
}
