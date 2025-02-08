package gr.padashop.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("pada.shop")
public class AppConfig {
    private String imageFilesPath;

    public String getImageFilesPath() {
        return imageFilesPath;
    }

    public void setImageFilesPath(String imageFilesPath) {
        this.imageFilesPath = imageFilesPath;
    }
}
