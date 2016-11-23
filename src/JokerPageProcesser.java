import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by Knight_JXNU on 2016/11/23.
 * 爬笑话
 */
public class JokerPageProcesser implements PageProcessor{

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
            .setDomain("http://www.xiaohuayoumo.com")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate, sdch")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("Connection", "keep-alive");;

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("http://www.xiaohuayoumo.com/\\w+/\\d+.html").all());
        page.putField("title", page.getHtml().xpath("//h1[@class='page-title']/text()").toString());
        page.putField("detail", page.getHtml().xpath("//span[@class='submitted']/text()"));
//        page.putField("contents", page.getHtml().xpath("//div[@class='field-item even']/p").all());
        page.putField("type", page.getHtml().xpath("//div[@class='field-item even']/allText()"));
        page.putField("contents", page.getHtml().xpath("//div[@class='field-item even']/p/allText()").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new JokerPageProcesser()).addUrl("http://www.xiaohuayoumo.com/").thread(5).run();
    }
}
