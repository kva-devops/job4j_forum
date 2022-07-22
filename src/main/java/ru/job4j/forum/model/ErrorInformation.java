package ru.job4j.forum.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

/**
 * DTO class for displaying errors
 */
@Getter
@Setter
@RequiredArgsConstructor
public class ErrorInformation {
    private final String shortMessage;
    private final String longMessage;
    private final String anchor;

    public JsonNode getError() {
        HashMap<String, Object> errorMap = new HashMap<String, Object>() {{
            put("error", new HashMap<String, Object>() {{
                put("shortMessage", shortMessage);
                put("longMessage", longMessage);
                put("anchor", anchor);
            }});
        }};
        ObjectMapper mapper = new ObjectMapper();
        return mapper.valueToTree(errorMap);
    }
}
