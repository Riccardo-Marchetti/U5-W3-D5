package riccardo.U5W3D5.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecFilterChainConfig {

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        // DISABILITO COMPORTAMENTI DI DEFAULT
        // DISABILITO IL FORM DI LOGIN
        httpSecurity.formLogin(http -> http.disable());
        // DISABILITO LA PROTEZIONE DA CSRF
        httpSecurity.csrf(http -> http.disable());
        // TOLGO LE SESSIONI
        httpSecurity.sessionManagement(http -> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // CONSENTO A TUTTE LE RICHIESTE DI PASSARE ATTRAVERSO LA FILTER CHAIN, TUTTE LE RISORSE SONO ACCESSIBILI A TUTTI GLI UTENTI CON AUTENTICAZIONE O MENO
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/**").permitAll());

        return httpSecurity.build();
    }
}
