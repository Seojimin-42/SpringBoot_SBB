package com.mysite.sbb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());

        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/css/**", "/js/**").permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/member/**").permitAll() // 권한 허용
                .requestMatchers("/question/**").permitAll() // 권한 허용
//                .requestMatchers("/study/**").permitAll()
                .anyRequest().authenticated());

        return http.build();
    }

}
