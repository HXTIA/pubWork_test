package run.hxtia.pubwork.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import run.hxtia.pubwork.common.filter.ErrorFilter;
import run.hxtia.pubwork.common.prop.PubWorkProperties;

import javax.servlet.Filter;

@Configuration
public class WebCfg implements WebMvcConfigurer {

    @Autowired
    private PubWorkProperties properties;

    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(properties.getCfg().getCorsOrigins())
            .allowCredentials(true)
            .allowedHeaders("*")
            .exposedHeaders("Content-Type")
            .allowedMethods("GET", "POST");
    }

    /**
     * 注册Filter
     */
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();

        // 设置Filter
        bean.setFilter(new ErrorFilter());
        bean.addUrlPatterns("/*");
        // 将其Filter排在第一个
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return bean;
    }

}
