package src.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static src.common.Content.*;

public class HttpTemplate {

    private static final HttpTemplate INSTANCE = new HttpTemplate();

    private HttpTemplate() {}

    public static HttpTemplate getInstance() {
        return INSTANCE;
    }

    public void sendPostByJson(String url, String json) {
        HttpURLConnection conn = getBasicConn("POST", "application/json", url);
        setJsonData(json, conn);
        send(conn);
    }

    private void send(HttpURLConnection conn) {
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            // todo sout -> logging
            System.out.println(response);
        } catch (IOException e) {
            // todo sout -> logging
            System.out.println(e.getMessage());
            throw new RuntimeException(SERVER_ERROR);
        }
    }

    private void setJsonData(String json, HttpURLConnection conn) {
        try {
            OutputStream outputStream = conn.getOutputStream();
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        } catch (IOException e) {
            // todo sout -> logging
            System.out.println(e.getMessage());
        }
    }


    private HttpURLConnection getBasicConn(String method, String contentType, String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", contentType);
            conn.setRequestProperty("charset", "utf-8");
            return conn;
        } catch (IOException e) {
            throw new RuntimeException(MIDDLE_SERVER_ERROR);
        }
    }
}
