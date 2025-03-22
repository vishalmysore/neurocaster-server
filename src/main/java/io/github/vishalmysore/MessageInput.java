package io.github.vishalmysore;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageInput {
    private String permission;
    private String actualMessage;
    private String action;
    @JsonRawValue
    private String data;
    private String method;
}
