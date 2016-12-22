package code.utils;

import code.dao.ManagerDao;
import code.model.FilmModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by shishen on 2016/10/11.
 */
public class BtTianTangPageProcesser implements PageProcessor {
//public class BtTianTangPageProcesser {

    private ManagerDao managerDao;

    public BtTianTangPageProcesser(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36")
            .setDomain("www.bttt99.com")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate, sdch")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("Connection", "keep-alive");

    public void process(Page page) {
        List<String> links = page.getHtml().links().regex("http://www\\.bttt99\\.com/v/\\d+").all();
        page.addTargetRequests(links);
        page.putField("name",page.getHtml().xpath("//div[@class=\"moviedteail_tt\"]/h1/text()"));
        String url = page.getUrl().toString();
        String name = page.getHtml().xpath("//div[@class=\"moviedteail_tt\"]/h1/text()").toString();
        if(name!=null && url!=null){
            managerDao.insertFilm(new FilmModel(name,url));
        }
        if (page.getResultItems().get("name")==null){
            page.setSkip(true);
        }
    }

    public Site getSite() {
        return site;
    }

}
