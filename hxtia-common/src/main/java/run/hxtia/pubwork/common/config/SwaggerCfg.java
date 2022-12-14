package run.hxtia.pubwork.common.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import run.hxtia.pubwork.common.shiro.TokenFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Swagger文档配置
 * 访问地址：%{项目地址}%/swagger-ui/index.html
 */
@Configuration
@EnableOpenApi
public class SwaggerCfg implements InitializingBean {

    /**
     * 拿到当前环境
     */
    @Autowired
    private Environment environment;
    /**
     * 是否生成文档
     */
    private boolean enable;

    @Bean
    public Docket userDocket() {
        return groupDocket(
            "01_用户",              // 分组模块
            "/users.*",             // 正则表达式，想要的模块。
            "用户模块文档",          // 模块标题
            "测试文档");        // 描述信息
    }

    @Bean
    public Docket skillDocket() {
        return groupDocket(
                "02_技巧",
                "/skills.*",
                "技巧模块文档",
                "测试文档");
    }

    // 构建分组模块
    private Docket groupDocket(String group,
                               String regex,
                               String title,
                               String description) {

        return baseDocket()
                .groupName(group)
                .apiInfo(apiInfo(title, description))
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.regex(regex))
                .build();
    }

    // 基础的配置
    private Docket baseDocket() {
        // 每个接口都要传token
        RequestParameter token = new RequestParameterBuilder()
                .name(TokenFilter.HEADER_TOKEN)
                .description("用户登录令牌")
                .in(ParameterType.HEADER)
                .build();

        return new Docket(DocumentationType.OAS_30)
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .ignoredParameterTypes(
                        HttpSession.class,
                        HttpServletRequest.class,
                        HttpServletResponse.class
                ).enable(enable);
    }

    // 文档的总配置
    private ApiInfo apiInfo(String title, String description) {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version("1.0.0")
                .build();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 配置只能在 dev、test环境使用文档
        enable = environment.acceptsProfiles(Profiles.of("dev", "test"));
    }

    // 配置全局的token
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList= new ArrayList<>();
        apiKeyList.add(new ApiKey("Token", "Token", "header"));
        return apiKeyList;
    }

    // 配置默认的安全上下文
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts=new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("Token", authorizationScopes));
        return securityReferences;
    }

}
