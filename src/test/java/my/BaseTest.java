package my;

import com.thoughtworks.selenium.SeleneseTestBase;
import com.thoughtworks.selenium.SeleneseTestCase;
import my.webapp.action.SignupForm;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yinghao_niu on 2016/3/27 for Project.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:applicationContext-resources.xml",
        "classpath:applicationContext-service.xml",
        "classpath:/**/applicationContext.xml"
})

@WebAppConfiguration
public class BaseTest extends SeleneseTestBase {
    public String doPost(String reference, String data) throws IOException {
        URL url = null;
        HttpURLConnection conn = null;
        BufferedReader rd = null;
        DataOutputStream wr = null;
        InputStream is = null;
        String line = null;
        StringBuffer response = null;

        url = new URL(reference);
        conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");

        conn.setRequestProperty("Authorization", "Basic dGVzdDphc2Rm");
        conn.setRequestProperty("Content-Type", "application/xml");

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        // Send response
        wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();

        // Get response
        is = conn.getInputStream();
        rd = new BufferedReader(new InputStreamReader(is));
        response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        return response.toString();
    }

}
