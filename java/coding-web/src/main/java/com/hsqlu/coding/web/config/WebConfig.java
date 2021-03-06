package com.hsqlu.coding.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created: 2016/4/11.
 * Author: Qiannan Lu
 */
@Configuration
@EnableWebMvc
@PropertySource(value = "classpath:druid.properties")

//@ComponentScan(value = "com.hsqlu.coding.web", includeFilters = @ComponentScan.Filter(Controller.class))
@ComponentScan(value = "com.hsqlu.coding.web")
public class WebConfig {
    @Autowired
    private Environment env;
    /*@Autowired
    private ApplicationInterceptor applicationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(applicationInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        //客户端一再要求整型不可以使用null，所以这样子设计，使用得两边都不影响
        JsonMapper.getMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //使可以支持entity对象lazy属性可以toJson
//        JsonMapper.getMapper().registerModule(hibernate4Module());
    }*/
}
