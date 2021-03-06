package ru.balmukanov.comradeship.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.balmukanov.comradeship.entity.User;
import ru.balmukanov.comradeship.repository.UserRepository;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso//todo - replace
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**")
                .fullyAuthenticated()
                .antMatchers("/", "/login**", "/js/**", "/static/**", "error**").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            String id = (String)map.get("sub");
            User user = userRepository.findById(id).orElseGet(() -> {
                User newUser = new User();

                newUser.setId(id);
                newUser.setName((String)map.get("name"));
                newUser.setEmail((String)map.get("email"));
                newUser.setLocale((String)map.get("locale"));
                newUser.setPicture((String)map.get("picture"));
                newUser.setGender((String)map.get("gender"));

                return newUser;
            });

            user.setLastVisitAt(LocalDateTime.now());
            return userRepository.save(user);
        };
    }
}
