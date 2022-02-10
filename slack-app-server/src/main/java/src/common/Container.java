package src.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Container {
    private Container() {}
    private final static HttpTemplate HTTP_TEMPLATE = HttpTemplate.getINSTANCE();
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static HttpTemplate getHttpTemplate() {
        return HTTP_TEMPLATE;
    }

    public static ObjectMapper getObjectInstance() {
        return OBJECT_MAPPER;
    }
}
