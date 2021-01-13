package by.babanin.ems.config;

import by.babanin.ems.model.Role;
import by.babanin.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(EmployeeService employeeService, PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/registration**").not().fullyAuthenticated()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/admin/**").hasRole(Role.ADMIN.getAuthority())
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(employeeService).passwordEncoder(passwordEncoder);
        /*auth.authenticationProvider(authenticationProvider());*/
    }

    /*@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(employeeService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }*/
}
