package src.component;

import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.model.block.element.ButtonElement;

import java.util.List;

public class ChatPostMessage {

    private ChatPostMessage() {}

    public static ChatPostMessageRequest commandList(String username) {
        return ChatPostMessageRequest.builder()
                .channel(username)
                .text("🎮 커맨드가 도착했어요!")
                .blocks(
                        List.of(
                                SectionBlock.builder().text(MarkdownTextObject.builder().text("할일과 알림을 추가하세요!").build())
                                        .accessory(ButtonElement.builder().text(PlainTextObject.builder().text("할일 추가").build()).actionId("add_btn").build()).build(),
                                SectionBlock.builder().text(MarkdownTextObject.builder().text("할일과 알림을 조회하세요!").build())
                                        .accessory(ButtonElement.builder().text(PlainTextObject.builder().text("할일 조회").build()).actionId("add_btn").build()).build(),
                                SectionBlock.builder().text(MarkdownTextObject.builder().text("주 리포트 확인하기!").build())
                                        .accessory(ButtonElement.builder().text(PlainTextObject.builder().text("리포트 조회").build()).actionId("add_btn").build()).build()
                        )
                ).build();
    }

    public static ChatPostMessageRequest getSayHello(String channel, String username) {
        return ChatPostMessageRequest.builder()
                .channel(channel) // 채널 ID가 아닌 유저ID로 설정하면 봇이 개인 DM으로 보냄
                .text("Hello?") // 알림 표시에서 보여질 내용
                .blocks(
                        List.of(
                                SectionBlock.builder().text(MarkdownTextObject.builder().text("Hello, <@" + username + ">").build()).build()
                        )).build();
    }
}
