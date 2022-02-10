package src.component;

import com.slack.api.model.view.View;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.composition.BlockCompositions.*;
import static com.slack.api.model.block.element.BlockElements.*;
import static com.slack.api.model.view.Views.*;
import static src.common.Content.MODAL_ADD_TODO_MSG;
import static src.common.Content.MODAL_TITLE;
import static src.common.IdList.*;

public class ModalView {
    private ModalView() {}
    private static final String MODAL_TYPE = "modal";
    private static final String PLAIN_TEXT = "plain_text";

    public static final View ADD_TODO = view(v -> v
                .type(MODAL_TYPE)
                .callbackId(ADD_TODO_CALLBACK_ID)
                .title(viewTitle(vt -> vt.type(PLAIN_TEXT).text(MODAL_TITLE)))
                .blocks(asBlocks(
                        section(s -> s.text(markdownText(mt -> mt.text(MODAL_ADD_TODO_MSG)))),
                        input(i -> {
                            i.blockId(ADD_TODO_TEXT_BLOCK);
                            i.label(plainText(t -> t.text("할일")));
                            i.element(plainTextInput(ti -> ti.actionId(ADD_TODO_TEXT_VALUE)));
                            return i;
                        }),
                        input(i -> {
                            i.blockId(ADD_TODO_DATE_BLOCK);
                            i.label(plainText(t -> t.text("날짜")));
                            i.element(datePicker(dp -> {
                                dp.placeholder(plainText(t -> t.text("Select a date")));
                                dp.actionId(ADD_TODO_DATE_VALUE);
                                return dp;
                            }));
                            return i;
                        }),
                        input(i -> {
                            i.blockId(ADD_TODO_TIME_BLOCK);
                            i.label(plainText(t -> t.text("시간")));
                            i.element(timePicker(tp -> {
                                tp.placeholder(plainText(t -> t.text("Select a time")));
                                tp.actionId(ADD_TODO_TIME_VALUE);
                                return tp;
                            }));
                            return i;
                        })
                ))
                .submit(viewSubmit(submit -> submit.type(PLAIN_TEXT).text("등록").emoji(true))));
}
