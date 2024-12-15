package ge.RecruitCandidateAutomation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class ResourceConfig {

    @Bean
    public Resource successEmailTemplate() {
        return new ClassPathResource("templates/successEmailTemplate.html");
    }

    @Bean
    public Resource failEmailTemplate() {
        return new ClassPathResource("templates/failEmailTemplate.html");
    }
}
