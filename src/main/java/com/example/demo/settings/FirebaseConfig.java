package com.example.demo.settings;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        System.out.println("----initialize----");
        // サービスアカウントキーのファイルを読み込む
        FileInputStream serviceAccount =
            new FileInputStream("src/main/resources/serviceAccountKey.json");

        // Firebase オプションの設定
        FirebaseOptions options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build();

        // Firebase アプリケーションの初期化
        if (FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.initializeApp(options);
        }

        // すでに初期化済みの場合、既存のインスタンスを返す
        return FirebaseApp.getInstance();
    }
}
