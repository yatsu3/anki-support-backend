package com.example.demo.settings;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        System.out.println("----initialize----");
        String firebaseConfig = System.getenv("FIREBASE_CONFIG"); // 環境変数から取得
        // System.out.println("firebasecongfg" + firebaseConfig);
        // String fixedJson = firebaseConfig.replace("\\n", "\n");
        // System.out.println("fixedJson" + fixedJson);
        if (firebaseConfig == null) {
            throw new IllegalStateException("FIREBASE_CONFIG environment variable is not set");
        }
        // サービスアカウントキーのファイルを読み込む
        // FileInputStream serviceAccount =
        //     new FileInputStream("src/main/resources/serviceAccountKey.json");

        ByteArrayInputStream serviceAccount = new ByteArrayInputStream(firebaseConfig.getBytes(StandardCharsets.UTF_8));

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
