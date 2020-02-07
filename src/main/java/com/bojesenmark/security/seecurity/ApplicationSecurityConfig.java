package com.bojesenmark.security.seecurity;

import com.bojesenmark.security.student.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.bojesenmark.security.seecurity.ApplicationUserRole.*;
import static com.bojesenmark.security.seecurity.ApplicationUserRole.STUDENT;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/", "index", "/css/*", "/js/*")
            .permitAll()
            .antMatchers("/api/**").hasRole(STUDENT.toString())
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
         UserDetails jamesBondUser = User.builder()
            .username("jamesbond")
            .password(passwordEncoder.encode("123456"))
            .roles(STUDENT.toString())
            .build();

        UserDetails frankHvamUser = User.builder()
            .username("frankhvam")
            .password(passwordEncoder.encode("123456"))
            .roles(ADMIN.toString())
            .build();

        UserDetails tomJonesUser = User.builder()
            .username("frankhvam")
            .password(passwordEncoder.encode("123456"))
            .roles(ADMIN_TRAINEE.toString())
            .build();

        return new InMemoryUserDetailsManager(
            jamesBondUser,
            frankHvamUser,
            tomJonesUser
        );
    }
}
