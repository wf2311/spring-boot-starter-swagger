package com.wf2311.swagger.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.schema.AlternateTypeRule;

/**
 * @author wangfeng
 * @time 2017/04/25 20:32.
 */
@ConfigurationProperties("springbootcamp.swagger")
public class SwaggerConfigurationProperties {
    private String title = "APPLICATION.NAME";
    private String version = "APPLICATION.VERSION";
    private String description;
    private String redirect;
    private String basePackage;
    private AlternateTypeRule[] alternateTypeRules;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public AlternateTypeRule[] getAlternateTypeRules() {
        return alternateTypeRules;
    }

    public void setAlternateTypeRules(AlternateTypeRule[] alternateTypeRules) {
        this.alternateTypeRules = alternateTypeRules;
    }
}
