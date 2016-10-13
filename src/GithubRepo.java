import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Created by Knight_JXNU on 2016/10/13.
 */
@TargetUrl("https://github.com/\\w+/\\w")
@HelpUrl("https://github.com/\\w+")
public class GithubRepo {

    @ExtractBy(value = "//h1[@class='entry-title public']/strong/a/text()", notNull = true)
    private String name;

    @ExtractByUrl("https://githbu\\.com/(\\w+)/.")
    private String author;

    @ExtractBy("div[@id='readme']/tidyText()")
    private String readme;

}
