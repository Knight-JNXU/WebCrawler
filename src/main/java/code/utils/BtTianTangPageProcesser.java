package code.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by shishen on 2016/10/11.
 */
public class BtTianTangPageProcesser implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36")
            .setDomain("www.bttt99.com")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate, sdch")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("Connection", "keep-alive");
//            .addHeader("Referer","http://www.bttiantang99.com/sb/%E5%8A%A8%E7%94%BB.html");

    public void process(Page page) {
        List<String> links = page.getHtml().links().regex("http://www\\.bttt99\\.com/v/\\d+").all();
        page.addTargetRequests(links);
//        page.putField("name",page.getHtml().xpath("//*[@id=\"content\"]/div[1]/h1"));
        page.putField("name",page.getHtml().xpath("//div[@class=\"moviedteail_tt\"]/h1/text()"));
//        List<Map> magnetResults = getMagnetLinks(page);
//        page.putField("links",magnetResults);
        if (page.getResultItems().get("name")==null){
            page.setSkip(true);
        }
    }

    private List<Map> getMagnetLinks(Page page) {
        List<Map> magnetResults = new LinkedList<Map>();
        String rawText = page.getRawText();
        Document document = Jsoup.parse(rawText);
        Elements elements = document.select("#download > ul>li>a[href^=\"magnet\"]");
        for (Element element:elements
             ) {
            Map<String,String> map = new HashMap<String, String>();
            map.put("name",element.text());
            map.put("magnetLinks",element.attr("href"));
            magnetResults.add(map);
        }
        return magnetResults;
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args){
        Spider.create(new BtTianTangPageProcesser())
                .addUrl("http://www.bttt99.com/")
//                .addPipeline(new JsonFilePipeline("F://btTianTangRepository"))
                .thread(5)
                .run();
    }
}
