package com.springbootserver.global.config;

import com.slack.api.bolt.App;
import com.slack.api.bolt.AppConfig;
import com.springbootserver.global.slack.event.BlockActionEvent;
import com.springbootserver.global.slack.event.MessageEvent;
import com.springbootserver.global.slack.event.ViewSubmissionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SlackAppConfig {

    private final ViewSubmissionEvent viewSubmissionEvent;

    @Value("${slack.single.token}")
    private String SINGLE_TOKEN;

    @Value("${slack.oauth.token}")
    private String OAUTH_TOKEN;

    @Bean
    public App initSlackApp() {
        AppConfig appConfig = AppConfig.builder().singleTeamBotToken(OAUTH_TOKEN).signingSecret(SINGLE_TOKEN).build();
        App app = new App(appConfig);
        MessageEvent.setAppEvent(app);
        BlockActionEvent.setAppEvent(app);
        viewSubmissionEvent.addTodoViewSub(app);
        return app;
    }
}