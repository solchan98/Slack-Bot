package src.component;

import com.slack.api.model.view.View;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.composition.BlockCompositions.*;
import static com.slack.api.model.block.element.BlockElements.*;
import static com.slack.api.model.view.Views.*;

public class ModalView {
    private ModalView() {}
    private static final String MODAL_TYPE = "modal";
    private static final String CALL_BACK_ADD_TODO = "callback_add_todo";
    private static final String PLAIN_TEXT = "plain_text";

    public static final View ADD_TODO = view(v -> v
                .type(MODAL_TYPE)
                .callbackId(CALL_BACK_ADD_TODO)
                .title(viewTitle(vt -> vt.type(PLAIN_TEXT).text("할일 매니저")))
                .blocks(asBlocks(
                        section(s -> s.text(markdownText(mt -> mt.text("할일과 알림받을 시간을 설정하세요.")))),
                        input(i -> {
                            i.blockId("add_todo_text");
                            i.label(plainText(t -> t.text("할일")));
                            i.element(plainTextInput(ti -> ti.actionId("todo_value")));
                            return i;
                        }),
                        input(i -> {
                            i.blockId("add_todo_date");
                            i.label(plainText(t -> t.text("날짜")));
                            i.element(datePicker(dp -> {
                                dp.placeholder(plainText(t -> t.text("Select a date")));
                                dp.actionId("date_value");
                                return dp;
                            }));
                            return i;
                        }),
                        input(i -> {
                            i.blockId("add_todo_time");
                            i.label(plainText(t -> t.text("시간")));
                            i.element(timePicker(tp -> {
                                tp.placeholder(plainText(t -> t.text("Select a time")));
                                tp.actionId("time_value");
                                return tp;
                            }));
                            return i;
                        })
                ))
                .submit(viewSubmit(submit -> submit.type(PLAIN_TEXT).text("등록").emoji(true))));
}
