package com.srum.config;

import com.srum.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author ThoNN1
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login", "/assets/**", "/css/**", "/js/**", "/password/reset/**").permitAll()
                .antMatchers("/default").authenticated()
                .antMatchers("/trainee/**", "/feedback/trainee").hasAnyAuthority(Roles.TRAINEE, Roles.CLASS_ADMIN)
                .antMatchers("/**").hasAuthority(Roles.CLASS_ADMIN);

        http.authorizeRequests().and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/default")
                .permitAll();

        http.authorizeRequests()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");

        http.authorizeRequests().and()
                .rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60 * 60 * 24 * 30);

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }
}
