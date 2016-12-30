import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by Knight_JXNU on 2016/12/30.
 */
public class MonitoringStopped {
    private static class MySpider extends Spider {

        public MySpider(PageProcessor pageProcessor) {
            super(pageProcessor);
        }

        public static MySpider create(PageProcessor pageProcessor) {
            return new MySpider(pageProcessor);
        }

//        @Override
//        public void close() {
//            super.close();
//            System.out.println("I am over!");
//        }
    }
    public static void main(String[] args){
        MySpider.create(new CsdnBlogPageProcessor()).addUrl("http://blog.csdn.net/qq598535550").thread(5).run();
    }
}
