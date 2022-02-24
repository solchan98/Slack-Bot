package com.springbootserver.global.config;

import com.slack.api.bolt.App;
import com.slack.api.bolt.AppConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackAppConfig {

    @Bean
    public App initSlackApp() {
        // TODO: 2022/02/24 change token to env
        String SINGLE_TOKEN = "";
        String OAUTH_TOKEN = "";
        AppConfig appConfig = AppConfig.builder().singleTeamBotToken(OAUTH_TOKEN).signingSecret(SINGLE_TOKEN).build();
        App app = new App(appConfig);
        // TODO: 2022/02/24 기존 slack-app-server 의 컴포넌트 및 이벤트 init구조 그대로 가져오기
        return app;
    }
}