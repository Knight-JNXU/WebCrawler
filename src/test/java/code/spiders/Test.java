package code.spiders;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Knight_JXNU on 2016/12/20.
 */
public class Test {
    private static final String EWORKPAL_URL = "http://home.eworkpal.com/v3/oa/run/application_record/?wf_state=-100&beginDate=&endDate=&size=20000&index=1&order=desc&name=zzz&age=15&_=1482221005954";
    private static final String EWORKPAL_POST = "http://passport.eworkpal.com/accounts/login";

    public static void main(String[] args) throws Exception{

    }

    @org.junit.Test
    public void post() throws Exception{
        URL postUrl = new URL(EWORKPAL_POST);
        HttpURLConnection postConnection = (HttpURLConnection)postUrl.openConnection();
        postConnection.setDoOutput(true);
        postConnection.setDoInput(true);
        postConnection.setRequestMethod("POST");
        postConnection.setUseCaches(false);
        postConnection.setInstanceFollowRedirects(true);
        postConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        DataOutputStream out = new DataOutputStream(postConnection.getOutputStream());
        out.writeBytes("appId=0&loginname=knight&password=1308094005&ValidCode=&remember=on");
        out.flush();
        out.close();
        BufferedReader reader = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
        String line;
        while ((line=reader.readLine())!=null){
            System.out.println(line);
        }
    }

    @org.junit.Test
    public void get() throws Exception{
        URL getUrl = new URL(EWORKPAL_URL);
        HttpURLConnection getConnection = (HttpURLConnection) getUrl.openConnection();
        getConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
//        getConnection.addRequestProperty("Cookie", "token=0e201fc2eaf2407b8c4a4fdcbc985557; uid=22951");
        getConnection.addRequestProperty("Cookie", "token=99856bf0a0794f9fb7eb3340f97dd654");
        getConnection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(getConnection.getInputStream()));
        String lines;
        while ((lines=reader.readLine())!=null){
            System.out.println(lines);
        }
    }
}
