package code.service;

import code.utils.BtTianTangPageProcesser;
import code.utils.MySpider;
import org.springframework.stereotype.Service;

/**
 * Created by Knight_JXNU on 2016/12/19.
 */
@Service
public class ManagerService extends BaseService{

    private MySpider mySpider = MySpider.create(new BtTianTangPageProcesser());

    public void input(){
        mySpider.addUrl(BT_URL).thread(THREAD_NUM).run();
    }

    public boolean isRunning(){
        return mySpider.ifRunning();
//        return true;
    }
}
