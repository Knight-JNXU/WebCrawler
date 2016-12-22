package code.model;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Created by Knight_JXNU on 2016/11/25.
 */
@TargetUrl("http://www\\.bttt99\\.com/v/\\d+")
public class FilmModel implements AfterExtractor{
    @ExtractBy("//div[@class=\"moviedteail_tt\"]/h1/text()")
    private String name;
    @ExtractByUrl
    private String url;

    public FilmModel() {
    }

    public FilmModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

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

    public void afterProcess(Page page) {

    }
}
