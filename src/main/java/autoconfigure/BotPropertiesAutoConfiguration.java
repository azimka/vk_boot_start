package autoconfigure;

import vkbot.VkBot;
import webhook.CallbackHandler;
import webhook.WebhookServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "rest_vk_properties", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(BotProperties.class)
public class BotPropertiesAutoConfiguration {

    @Configuration
    @ConditionalOnMissingBean(VkBot.class)
    protected static class MessengerConfiguration {

        @Autowired(required = false)
        private CallbackHandler callbackHandler;

        @Autowired
        private BotProperties properties;

        @Bean
        public VkBot messenger() {
            return new VkBot(properties, callbackHandler);
        }
    }

    @Configuration
    @ConditionalOnWebApplication
    @ConditionalOnProperty(prefix = "rest_vk_properties.webhook", name = "enabled",
            matchIfMissing = true)
    protected static class WebhookConfiguration {

        @Autowired
        private BotProperties properties;

        @Bean
        public ServletRegistrationBean webhookServlet(VkBot botRequestHandler) {
            String path = properties.getWebhook().getPath();
            String urlMapping = (path.endsWith("/") ? path + "*" : path + "/*");
            WebhookServlet servlet = new WebhookServlet(botRequestHandler);
            return new ServletRegistrationBean(servlet, urlMapping);
        }
    }
}
