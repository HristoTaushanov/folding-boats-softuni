package bg.softuni.boats.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "google.recaptcha")
public class ReCaptchaConfig {
    private String siteKey;
    private String secretKey;

    public ReCaptchaConfig(String siteKey, String secretKey) {
        this.siteKey = siteKey;
        this.secretKey = secretKey;
    }

    public ReCaptchaConfig() {
    }

    public String getSiteKey() {
        return siteKey;
    }

    public ReCaptchaConfig setSiteKey(String siteKey) {
        this.siteKey = siteKey;
        return this;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public ReCaptchaConfig setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }
}
