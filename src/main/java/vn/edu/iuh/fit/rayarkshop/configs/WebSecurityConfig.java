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
import vn.edu.iuh.fit.rayarkshop.securities.FirebaseAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FirebaseAuthenticationProvider customAuthenticationProvider;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/", "/home", "/login", "/signup", "/logout", "/api/accounts/signup/user-account", "/error", "/error/**").permitAll()
                .antMatchers("/contacts", "/products", "/products/**", "/new-login", "/new-login/**", "/new-signup", "/new-signup/**", "/new-signup-account", "/account-signup-complete").permitAll()
                .antMatchers("/admin/**", "/api/products/**").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/login/success", "/shopping-cart", "/shopping-cart/**", "/accounts", "/accounts/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/sales-orders", "/sales-orders/**", "/orders", "/orders/**", "/api/favorite-product-list", "/api/favorite-product-list/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/api/sales-order", "/api/sales-order/**", "/api/shipping-address", "/api/shipping-address/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/api/shopping-cart", "/api/shopping-cart/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
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
        auth.authenticationProvider(customAuthenticationProvider);
    }

}
