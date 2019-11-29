package williamsadeho.parentalcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class Application {

    /**
     * Ensures can access the API using the supplied credentials
     *
     * @return the user details service
     * @throws Exception
     */
    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        final InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("sky").password("sky").roles("USER").build());
        return manager;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
