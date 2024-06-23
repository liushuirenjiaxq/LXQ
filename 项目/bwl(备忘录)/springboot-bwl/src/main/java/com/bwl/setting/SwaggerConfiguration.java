package com.bwl.setting;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Swagger配置文件
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("备忘录")
                        .description("功能简单")
                        .contact(new Contact().name("李小桥").email("2823283343@qq.com").url("https://blog.csdn.net/weixin_67770123?spm=1000.2115.3001.5343"))
                        .version("v1.0"));
    }
}

