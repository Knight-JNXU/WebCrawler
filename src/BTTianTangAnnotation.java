import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Created by Knight_JXNU on 2016/12/22.
 */
@TargetUrl("http://www\\.bttt99\\.com/v/\\d+")
public class BTTianTangAnnotation {
    @ExtractBy(value = "//div[@class=\"moviedteail_tt\"]/h1/text()")
    private String name;
    @ExtractByUrl
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000).
                setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36")
                .setDomain("www.bttt99.com")
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .addHeader("Accept-Encoding", "gzip, deflate, sdch")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
                .addHeader("Cache-Control", "max-age=0")
                .addHeader("Connection", "keep-alive")
                , new ConsolePageModelPipeline(), BTTianTangAnnotation.class)
                .addUrl("http://www.bttt99.com/").thread(5).run();
    }
}
