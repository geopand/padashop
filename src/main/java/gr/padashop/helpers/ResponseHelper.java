package gr.padashop.helpers;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;

public class ResponseHelper {
    public static HttpHeaders getDownloadHeaders(String fileName) {
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(fileName)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);
        return headers;
    }
}
