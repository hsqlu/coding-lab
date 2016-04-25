package com.hsqlu.coding.web.config;

import com.hsqlu.coding.web.util.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created: 2016/4/11.
 * Author: Qiannan Lu
 */
public class DefaultWebApplicationConfig {
    private static final boolean jaxb2Present = ClassUtils.isPresent("javax.xml.bind.Binder", DefaultWebApplicationConfig.class.getClassLoader());
    private static final boolean jackson2Present = ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", DefaultWebApplicationConfig.class.getClassLoader()) && ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", DefaultWebApplicationConfig.class.getClassLoader());
//    private static final boolean jacksonPresent = ClassUtils.isPresent("org.codehaus.jackson.map.ObjectMapper", WebApplicationConfig.class.getClassLoader()) && ClassUtils.isPresent("org.codehaus.jackson.JsonGenerator", WebApplicationConfig.class.getClassLoader());

    public DefaultWebApplicationConfig() {
    }

    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).favorParameter(false);
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        stringConverter.setWriteAcceptCharset(false);
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(stringConverter);
        converters.add(new ResourceHttpMessageConverter());
        converters.add(new SourceHttpMessageConverter<>());
        converters.add(new AllEncompassingFormHttpMessageConverter());
        if(jaxb2Present) {
            converters.add(new Jaxb2RootElementHttpMessageConverter());
        }

        if(jackson2Present) {
            MappingJackson2HttpMessageConverter convert = new MappingJackson2HttpMessageConverter();
            convert.setObjectMapper(JsonMapper.getMapper());
            ArrayList<MediaType> supportedMediaTypes = new ArrayList<>();
            supportedMediaTypes.add(MediaType.APPLICATION_JSON);
            convert.setSupportedMediaTypes(supportedMediaTypes);
            converters.add(convert);
        }
        this.customMediaTypeSupport(converters);
    }

    public void customMediaTypeSupport(List<HttpMessageConverter<?>> converters) {
    }

    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setMaxUploadSize(5242880L);
        resolver.setMaxInMemorySize(524288);
        return resolver;
    }

    public HandlerExceptionResolver handlerExceptionResolver() {
        return new HandlerExceptionResolverComposite();
    }

/*    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(this.serverTraceInterceptor());
//        registry.addInterceptor(this.visitorsSecurityInterceptor());
    }*/
}
