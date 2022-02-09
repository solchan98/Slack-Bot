package src.component;

import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;

import java.util.List;

public class ChatPostMessage {

    private ChatPostMessage() {}

    public static ChatPostMessageRequest getSayHello(String channel, String username) {
        return ChatPostMessageRequest.builder()
                .channel(channel) // 채널 ID가 아닌 유저ID로 설정하면 봇이 개인 DM으로 보냄
                .blocks(
                        List.of(
                                SectionBlock.builder().text(MarkdownTextObject.builder().text("Hello, <@" + username + ">").build()).build()
                        )).build();
    }
}
