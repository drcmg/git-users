package pl.grunwald.users.integration.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UserInfoConfiguration {

    private final ObjectMapper objectMapper;
    private final UserInfoResultErrorHandler errorHandler;

    public UserInfoConfiguration(ObjectMapper objectMapper, UserInfoResultErrorHandler errorHandler) {
        this.objectMapper = objectMapper;
        this.errorHandler = errorHandler;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .messageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .errorHandler(errorHandler)
                .build();
    }
}
