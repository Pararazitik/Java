package client.controllers.brand;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PUT {

    public void changeBrand(Long id,String brandname) throws IOException {
        URL url = new URL("http://localhost:8080/brand");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("PUT");

        String brand = "{\"id\":\""+id+"\", \"brandname\":\""+brandname+"\"}";

        OutputStream os = connection.getOutputStream();
        os.write(brand.getBytes(StandardCharsets.UTF_8));
        os.close();

        InputStream in = new BufferedInputStream(connection.getInputStream());
        String result = IOUtils.toString(in, StandardCharsets.UTF_8);

        in.close();
        connection.disconnect();
    }

}
