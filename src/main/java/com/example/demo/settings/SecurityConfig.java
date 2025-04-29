package com.example.demo.settings;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final FirebaseTokenFilter firebaseTokenFilter;

    // コンストラクタでカスタム認証フィルターを注入
    public SecurityConfig(FirebaseTokenFilter firebaseTokenFilter) {
        System.out.println("----SecurityConfig----");
        this.firebaseTokenFilter = firebaseTokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("----securityFilterChain----");
        http.csrf(AbstractHttpConfigurer::disable)
        .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CSRF対策を無効化（APIの場合は一般的）
        .authorizeHttpRequests(auth -> auth
        .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll() // 先にOPTIONSを許可
        .requestMatchers("/**").authenticated() // その後、他のリクエストに認証を要求
    )
            .addFilterBefore(firebaseTokenFilter, UsernamePasswordAuthenticationFilter.class); // 認証フィルターを追加

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        System.out.println("----CorsConfigurationSource----");
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "https://anki-support-frontend.pages.dev")); // フロントエンドのオリジンを許可
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 許可するHTTPメソッド
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type")); // 許可するヘッダー
        configuration.setAllowCredentials(true); // 認証情報を許可
        configuration.setMaxAge(3600L); // プリフライトリクエストのキャッシュ時間（秒）

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
