package code.utils;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by Knight_JXNU on 2016/12/19.
 */
public class MySpider extends Spider {
//public class MySpider {
    public MySpider(PageProcessor pageProcessor) {
        super(pageProcessor);
    }
    public static MySpider create(PageProcessor pageProcessor) {
        return new MySpider(pageProcessor);
    }
    //检查spider是否继续运行
    public boolean ifRunning() {
        if(this.stat.get() == 1) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void run() {
        super.run();
    }

}
