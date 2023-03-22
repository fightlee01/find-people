package config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("find people API文档")
                .apiInfo(webApiInfo())
                .pathMapping("/")
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*"))).build();

    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("API文档").
                description(" 本文档描述了接口定义 ")
                .version("1.0")
                .build();
    }
}
