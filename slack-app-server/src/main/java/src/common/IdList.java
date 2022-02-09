package src.common;

public class IdList {
    private IdList() {}

    private static final String VALUE = "_value";

    private static final String ADD_TODO = "add_todo";
    public static final String ADD_TODO_CALLBACK_ID = "callback_add_todo";
    public static final String ADD_TODO_BTN = ADD_TODO + "_btn";
    public static final String ADD_TODO_TEXT_BLOCK = ADD_TODO + "_text";
    public static final String ADD_TODO_DATE_BLOCK = ADD_TODO + "_date";
    public static final String ADD_TODO_TIME_BLOCK = ADD_TODO + "_time";
    public static final String ADD_TODO_TEXT_VALUE = ADD_TODO_TEXT_BLOCK + VALUE;
    public static final String ADD_TODO_DATE_VALUE = ADD_TODO_DATE_BLOCK + VALUE;
    public static final String ADD_TODO_TIME_VALUE = ADD_TODO_TIME_BLOCK + VALUE;
}
