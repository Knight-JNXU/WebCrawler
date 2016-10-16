import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by Knight_JXNU on 2016/10/14.
 */
public class CsdnBlogPageProcessor implements PageProcessor {
    private static String username = "qq598535550";  //设置csdn用户名
    private static int size = 0;  //共抓取到的文章数量

    //抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private String tempStr;

    @Override
    //process是定制爬虫逻辑的核心接口，在这里便也抽取逻辑
    public void process(Page page) {
        System.out.println(page.getUrl());
        //列表页
        if(!page.getUrl().regex("http://blog\\.csdn\\.net/" + username + "/article/details/\\d+").match()){
            //添加所有文章页
            page.addTargetRequests(page.getHtml()
                    .xpath("//h3[@class='list_c_t']").links()  //限定文章获取区域
                    .all());
            //文章页
        }else{
            if(page.getUrl().regex("http://blog\\.csdn\\.net/qq598535550/article/details/51278225").match()){
                boolean flag = true;
            }
            size++;  //文章数量加1
            //用csdn blog类来存抓取到的数据，方便存入数据库
            CsdnBlog csdnBlog = new CsdnBlog();
            //设置编号
            csdnBlog.setId(Integer.parseInt(page.getUrl().regex("http://blog\\.csdn\\.net/"+username+"/article/details/(\\d+)").get()));
            //设置标题
            csdnBlog.setTitle(page.getHtml().xpath("//h3[@class='list_c_t']/a/text()").get());
            //设置日期
            csdnBlog.setDate(page.getHtml().xpath("//div[@class='date_t']/span/text()").get()+
                            page.getHtml().xpath("//div[@class='date_t']/em/text()").get()+
                    page.getHtml().xpath("//div[@class='date_b']/text()").get());
            //设置类别(可以有多个，用 "," 来分割)
            csdnBlog.setCategory(listToString(page.getHtml().xpath("//p[@class='detail_p']/label//em/text()").all()));
            //设置阅读人数
            List<String> readList = page.getHtml().xpath("//p[@class='read_r']/label/span/allText()").all();
            tempStr = readList.get(0);
            csdnBlog.setView(Integer.parseInt(tempStr.substring(1, tempStr.length()-1)));
            //设置评论人数
            tempStr = readList.get(1);
            csdnBlog.setComments(Integer.parseInt(tempStr.substring(1, tempStr.length()-1)));
            //设置是否原创
            csdnBlog.setCopyright(page.getHtml().xpath("//a[@class='set_old']/text()").get()==null?0:1);
            System.out.println(csdnBlog);
        }
    }

    //把list转换为string，用 "," 分割
    public static String listToString(List<String> stringList){
        if(stringList == null){
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for(String string : stringList){
            if(flag){
                result.append(",");
            }else{
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String args[]){
        long startTime, endTime;
        System.out.println("start:");
        startTime = System.currentTimeMillis();
        //从用户博客首页开始，开启5个线程，启动爬虫
        Spider.create(new CsdnBlogPageProcessor()).addUrl("http://blog.csdn.net/"+username).thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("end");
        System.out.println("result:");
        System.out.println("article number:" + size);
        System.out.println("time consuming:" + (endTime-startTime)/1000 + "s");
    }
}
