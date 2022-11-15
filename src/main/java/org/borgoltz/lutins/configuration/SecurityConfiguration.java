package org.borgoltz.lutins.configuration;

import org.borgoltz.lutins.family.FamilyMember;
import org.borgoltz.lutins.family.FamilyMemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private FamilyMemberDAO dao;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var authenticator = auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .and();
        String[] userRoles = new String[] { "USER" };
        String[] adminRoles = new String[] { "ADMIN", "USER" };


        dao.findByOrganisateur(true).forEach(org -> {
            authenticator.withUser(org.getName()).password(passwordEncoder().encode(password(org)))
                    .roles(org.isAdmin() ? adminRoles : userRoles);
        });

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // .antMatchers("/login").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/init").hasAnyRole("ADMIN")
                .antMatchers("/list").hasAnyRole("ADMIN")
                .antMatchers("/missions").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .httpBasic();
    }

    public static String password(FamilyMember org) {
        String passwd = "Lutin" + (Math.abs(org.getName().hashCode()) % 10000);
        System.out.println("Mot de passe de " + org.getName() + " (" + org.getEmail() + ") : \t" + passwd);
        return passwd;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder;
    }
}
