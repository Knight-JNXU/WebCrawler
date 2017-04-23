import code.controller.ManagerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Knight_JXNU on 2016/12/30.
 * 模拟一次面登录攻击
 * 通过set cookie验证是否可以骗过拦截器
 */
public class Attack0 {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(Attack0.class);

    public static void main(String[] args) throws Exception {
        URL getUrl = new URL("http://localhost:8080/CSDNSearch/manag/index");
        HttpURLConnection getConnection = (HttpURLConnection) getUrl.openConnection();
        getConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
//        getConnection.addRequestProperty("Cookie", "token=0e201fc2eaf2407b8c4a4fdcbc985557; uid=22951");
        getConnection.addRequestProperty("Cookie", "username=lw");
        getConnection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(getConnection.getInputStream()));
        String lines;
        while ((lines=reader.readLine())!=null){
            LOGGER.info(lines);
        }
    }
}
