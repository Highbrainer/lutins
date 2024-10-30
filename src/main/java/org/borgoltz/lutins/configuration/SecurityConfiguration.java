package org.borgoltz.lutins.configuration;

import java.util.ArrayList;

import org.borgoltz.lutins.family.FamilyMember;
import org.borgoltz.lutins.family.FamilyMemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() {
		@Override
		public String encode(CharSequence rawPassword) {
			return super.encode(rawPassword);
		}
	};

	@Autowired
	private FamilyMemberDAO dao;

	@Bean
	public UserDetailsService userDetailsService() {
		String[] userRoles = new String[] { "USER" };
		String[] adminRoles = new String[] { "ADMIN", "USER" };

		var admin = User.withUsername("admin").password(passwordEncoder().encode("admin")).roles(adminRoles).build();

		var users = new ArrayList<UserDetails>();
		users.add(admin);

		dao.findByOrganisateur(true).stream()
				.map(org -> User.withUsername(org.getName()).password(passwordEncoder().encode(password(org)))
						.roles(org.isAdmin() ? adminRoles : userRoles).build())
				.forEach(users::add);

		return new InMemoryUserDetailsManager(users);
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http.cors(customizer -> customizer.disable()).csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(authorize -> authorize
						// .antMatchers("/login").permitAll()
						.requestMatchers("/public/**").permitAll().requestMatchers("/images/**").permitAll()
						.requestMatchers("/init").hasAnyRole("ADMIN").requestMatchers("/list").hasAnyRole("ADMIN")
						.requestMatchers("/missions").hasAnyRole("ADMIN").anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").permitAll().defaultSuccessUrl("/home", true).permitAll())
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login").permitAll())
				.httpBasic(Customizer.withDefaults()).build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
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
