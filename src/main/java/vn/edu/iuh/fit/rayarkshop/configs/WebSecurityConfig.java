package vn.edu.iuh.fit.rayarkshop.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/", "/home", "/login", "/signup", "/logout", "/api/accounts/signup/user-account").permitAll()
                .antMatchers("/contacts", "/products", "/products/**").permitAll()
                .antMatchers("/admin/**", "/api/products/**").access("hasAnyRole('ROLE_ADMI')")
                .antMatchers("/login/success", "/shopping-cart", "/shopping-cart/**", "/accounts", "/accounts/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMI')")
                .antMatchers("/sales-orders", "/sales-orders/**", "/orders", "/orders/**", "/api/favorite-product-list", "/api/favorite-product-list/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMI')")
                .antMatchers("/api/sales-order", "/api/sales-order/**", "/api/shipping-address", "/api/shipping-address/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMI')")
                .antMatchers("/api/shopping-cart", "/api/shopping-cart/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMI')")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/error/access-denied")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/login/success")
                .failureUrl("/login?success=false")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/j_spring_security_check")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
