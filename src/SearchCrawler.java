import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Knight_JXNU on 2016/10/10.
 * 搜索 web 爬虫（起始的URL、处理url的最大数、要搜索的字符串）
 * 命令行输入：如 java SearchCrawler http://www.sina.com 20 a
 */
public class SearchCrawler implements Runnable {

    /**
     * disallowListCache缓存 robot 不允许搜索的URL。Robot协议在web站点的根目录下设置一个robots.txt文件，
     * 规定站点上面的哪些页面是限制搜索的。搜索程序应该在搜索过程中跳过这些区域，下面是 robots.txt 的一个例子：
     * # robots.txt for http://somehost.com/
     * User-agent: *
     * Disallow: /cgi-bin/
     * Disallow: /registration # /Disallow robots on registration page
     * Disallow: /login
     */

    private HashMap<String, ArrayList<String>> disallowListCache = new HashMap<>();
    ArrayList<String> errorList = new ArrayList<>();  //错误信息
    ArrayList<String> result = new ArrayList<>();  //搜索到的结果
    String startUrl;  //开始搜索的起点
    int maxUrl;  //最大处理的 url 数
    String searchString;  //要搜索的字符串（英文）
    boolean caseSensitive = false;  //是否区分大小写
    boolean limitHost = false;  //是否在限制的主机内搜索

    public SearchCrawler(String startUrl, int maxUrl, String searchString) {
        this.startUrl = startUrl;
        this.maxUrl = maxUrl;
        this.searchString = searchString;
    }

    public ArrayList<String> getResult(){
        return result;
    }

    @Override
    public void run() {
        //启动搜索线程
        crawl(startUrl, maxUrl, searchString, limitHost, caseSensitive);
    }

    //检测URL格式
    private URL verifyUrl(String url){
        // 只处理HTTP URLs
        if(!url.toLowerCase().startsWith("http://")){
            return null;
        }
        URL verifiedUrl = null;
        try {
            verifiedUrl = new URL(url);
        }catch (Exception e){
            return null;
        }
        return verifiedUrl;
    }

    //检测robot是否允许访问给出的url
    private boolean isRobotAllowed(URL urlToCheck){
        String host = urlToCheck.getHost().toLowerCase();  //获取给出url的主机

        // 获取主机不允许搜索的 url 缓存
        ArrayList<String> disallowList = disallowListCache.get(host);

        // 如果还没有缓存，下载并缓存
        if(disallowList == null){
            disallowList = new ArrayList<>();
            try {
                URL robotsFileUrl = new URL("http://" + host + "/robots.txt");
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(robotsFileUrl.openStream())
                );
                // 读 robot 文件，创建不允许访问的路径列表
                String line;
                while ((line = reader.readLine()) != null){
                    if(line.indexOf("Disallow:") == 0){ //检查是否包含"Disallow:"
                        String disallowPath = line.substring("Disallow:".length()); //获取不允许访问路径
                        // 检查是否有注释
                        int commentIndex = disallowPath.indexOf("#");
                        if(commentIndex != -1){
                            disallowPath = disallowPath.substring(0, commentIndex);  //去掉注释
                        }
                        disallowPath = disallowPath.trim();
                        disallowList.add(disallowPath);
                    }
                }
                // 缓存次主机不允许访问的路径
                disallowListCache.put(host, disallowList);
            }catch (Exception e){
                return true;
            }
        }

        String file = urlToCheck.getFile();
        for (int i=0; i<disallowList.size(); i++){
            String disallow = disallowList.get(i);
            if(file.startsWith(disallow)){
                return false;
            }
        }
        return true;
    }

    private String downloadPage(URL pageUrl){
        try {
            // open connection to url for reading
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(pageUrl.openStream())
            );

            // read page into buffer
            String line;
            StringBuffer pageBuffer = new StringBuffer();
            while ((line = reader.readLine()) != null){
                pageBuffer.append(line);
            }
            return pageBuffer.toString();
        }catch (Exception e){

        }
        return null;
    }

    // 从url中去掉 www
    private String removeWwwFromUrl(String url){
        int index = url.indexOf("://wwww");
        if(index != -1){
            return url.substring(0, index+3) + url.substring(index+7);
        }
        return url;
    }

    //解析页面并找出链接
    private ArrayList<String> retrieveLinks(URL pageUrl, String pageContents,
                                            HashSet crawledList, boolean limitHost){
        // 用正则表达式编译链接的匹配模式
        Pattern p = Pattern.compile("<a\\s+href\\s*=\\s*\"?(.*?)[\"|>]",
                Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(pageContents);

        ArrayList<String> linkList = new ArrayList<>();
        while (m.find()){
            String link = m.group(1).trim();

            if(link.length() < 1){
                continue;  //结束本层的本次循环
            }

            // 跳过链到本页面的内链接
            if(link.charAt(0) == '#'){
                continue;
            }

            if(link.indexOf("mailto:") != -1){
                continue;
            }

            if(link.toLowerCase().indexOf("javascript") != -1){
                continue;
            }

            if(link.indexOf("://") == -1){
                if(link.charAt(0) == '/'){
                    // 处理绝对的
                    link = "http://" + pageUrl.getHost() + ":"
                            + pageUrl.getPort() + link;
                }else{
                    String file = pageUrl.getFile();
                    if(file.indexOf('/') == -1){
                        // 处理相对地址
                        link = "http://" + pageUrl.getHost() + ":"
                                + pageUrl.getPort() + "/" + link;
                    }else{
                        String path = file.substring(0,
                                file.lastIndexOf('/') + 1);
                        link = "http://" + pageUrl.getHost() + ":"
                                + pageUrl.getPort() + path + link;
                    }
                }
            }
            int index = link.indexOf('#');
            if(index != -1){
                link = link.substring(0, index);
            }

            link = removeWwwFromUrl(link);

            URL verifiedLink = verifyUrl(link);
            if(verifiedLink == null){
                continue;
            }

            // 如果限定主机，排除那些不合条件的url
            if(limitHost && !pageUrl.getHost().toLowerCase().
                    equals(verifiedLink.getHost().toLowerCase())){
                continue;
            }

            //跳过那些已经处理的链接
            if(crawledList.contains(link)){
                continue;
            }
            linkList.add(link);
        }
        return linkList;
    }

    //搜索下载web页面的内容，判断在该页面内有没有指定的搜索字符串
    private boolean searchStringMatches(
            String pageContexts, String searchString, boolean caseSensitive){
        String searchContexts = pageContexts;
        if(!caseSensitive){
            // 如果不区分大小写
            searchContexts = pageContexts.toLowerCase();
        }
        Pattern p = Pattern.compile("[\\s]");
        String[] terms = p.split(searchString);
        for (int i=0; i<terms.length; i++){
            if(caseSensitive){
                if(searchContexts.indexOf(terms[i]) == -1){
                    return false;
                }
            }else{
                if(searchContexts.indexOf(terms[i].toLowerCase()) == -1){
                    return false;
                }
            }
        }
        return true;
    }

    //执行世界的搜索操作
    public ArrayList<String> crawl(String startUrl, int maxUrls,
                                   String searchString, boolean limithost, boolean caseSensitive){
        System.out.println("searchString=" + searchString);
        HashSet<String> crawledList = new HashSet<String>();
        LinkedHashSet<String> toCrawList = new LinkedHashSet<>();

        if(maxUrls < 1){
            errorList.add("Invalid Max URLs value.");
            System.out.println("Invalid Max URLs value.");
        }

        if(searchString.length() < 1){
            errorList.add("Missing Search String.");
            System.out.println("Missing search String.");
        }

        if(errorList.size() > 0){
            System.out.println("err!!!");
            return errorList;
        }

        // 从开始URL中移出www
        startUrl = removeWwwFromUrl(startUrl);

        toCrawList.add(startUrl);
        while (toCrawList.size() > 0){
            if(maxUrls != -1){
                if(crawledList.size() == maxUrls){
                    break;
                }
            }

            // Get URL at bottom of the list
            String url = toCrawList.iterator().next();

            // Remove URL from the crawl list.
            toCrawList.remove(url);

            // Convert string url to URL object
            URL verifiedUrl = verifyUrl(url);

            // Skip URL if robots are not allowed to access it.
            if(!isRobotAllowed(verifiedUrl)){
                continue;
            }

            // 增加已处理的URL到crawledList
            crawledList.add(url);
            String pageContents = downloadPage(verifiedUrl);

            if(pageContents!=null && pageContents.length()>0){
                // 从页面中获取有效的链接
                ArrayList<String> links = retrieveLinks(
                        verifiedUrl, pageContents, crawledList, limitHost);
                toCrawList.addAll(links);

                if(searchStringMatches(pageContents, searchString, caseSensitive)){
                    result.add(url);
                    System.out.println(url);
                }
            }
        }
        return result;
    }

    //主函数
    public static void main(String[] args){
        String inputStr = new Scanner(System.in).nextLine();
        String strs[] = inputStr.split(" ");
        if(strs.length != 3){
            System.out.println("Usage:java SearchCrawler startUrl maxUrl searchString");
            return;
        }
        int max = Integer.parseInt(strs[1]);
        SearchCrawler crawler = new SearchCrawler(strs[0], max, strs[2]);
        Thread search = new Thread(crawler);
        System.out.println("Start searching...");
        System.out.println("result:");
        search.start();
    }
}
