package code.utils;

import code.model.BlogModel;
import code.dao.ManagerDao;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * Created by Knight_JXNU on 2016/11/25.
 */
public class CsdnBlogCrawler implements PageProcessor,Closeable {
    private boolean hasInput = false;
    private ManagerDao managerDao;

    public CsdnBlogCrawler(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
            .setDomain("http://blog.csdn.net/?ref=toolbar_logo")  //设置域名，设置好了域名cookie才能生效
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate, sdch")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("Connection", "keep-alive");
    private static final String URL_LIST = "/?ref=toolbar_logo&page=\\d+";

    public void process(Page page) {
        hasInput = true;
        page.putField("title", page.getHtml().xpath("//h3[@class='tracking-ad']/a/text()").all());
        page.putField("author", page.getHtml().xpath("//a[@class='nickname']/text()").all());
        page.putField("url", page.getHtml().xpath("//h3[@class='tracking-ad']/a").links().all());

        List<String> titles = page.getHtml().xpath("//h3[@class='tracking-ad']/a/text()").all();
        List<String> authors = page.getHtml().xpath("//a[@class='nickname']/text()").all();
        List<String> urls = page.getHtml().xpath("//h3[@class='tracking-ad']/a").links().all();
        for(int i=0; i<titles.size(); i++){
            managerDao.insertBlog(new BlogModel(titles.get(i), urls.get(i), authors.get(i)));
        }
        page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
    }

    public Site getSite() {
        return site;
    }

    public void close() throws IOException {
        if(hasInput){
            managerDao.sendEmail();
        }
        hasInput = false;
    }

}
