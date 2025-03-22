package io.github.vishalmysore;

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
public class MessageOutput implements ChatOutput{
    private String permission;
    private String message;
    private String methodName;
    private String jsonStr;

}
