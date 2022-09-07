package run.hxtia.pubwork.common.prop;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("pubwork")
public class PubWorkProperties implements ApplicationContextAware {

    /**
     * 1、配置类
     * 2、文件上传路径类
     */
    private Cfg cfg;
    private Upload upload;
    /**
     * 1、如果没有放在Spring容器中的Bean
     * 2、不可以直接使用 @Autowired 注入对象。给那些没有在Spring Ioc中的类使用
     */
    private static PubWorkProperties properties;
    public static PubWorkProperties getProperties() {
        return properties;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        properties = this;
    }

    /**
     * 读取文件上传的配置
     */
    @Data
    public static class Upload {
        private String basePath;
        private String uploadPath;
        private String imagePath;
        private String videoPath;

        // 获取图片相对目录
        public String getImageDir() {
            return uploadPath + imagePath;
        }

        // 获取视频相对目录
        public String getVideoDir() {
            return uploadPath + videoPath;
        }
    }

    /**
     * 读取项目配置
     */
    @Data
    public static class Cfg {
        // 允许通过的域
        private String[] corsOrigins;
    }

}
