import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Created by Knight_JXNU on 2016/10/14.
 */
@TargetUrl("http://meishi.qq.com/beijing/c/all[\\-p2]*")
@ExtractBy(value = "//url[@id=\"promos_list2\"]/li", multi = true)
public class QQMeishi {

    @ExtractBy("div[@class=info]/a[@class=title]/h4/text()")
    private String shopName;

    @ExtractBy("//div[@class=info]/a[@class=title]/text()")
    private String promo;

    public static void main(String[] args){
        OOSpider.create(Site.me(), new ConsolePageModelPipeline(),
                QQMeishi.class).addUrl("http://meishi.qq.com/beijing/c/all")
                .thread(4).run();
    }

}
