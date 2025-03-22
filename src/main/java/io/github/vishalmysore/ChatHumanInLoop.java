package io.github.vishalmysore;

import com.t4a.detect.FeedbackLoop;
import com.t4a.detect.HumanInLoop;
import lombok.Getter;

import java.util.Map;

public class ChatHumanInLoop implements HumanInLoop ,ChatOutput{

    @Getter
    private String methodName;

    @Getter
    private String promptText;

    @Getter
    private Map<String, Object> params;
    @Getter
    private String paramsString;
    private FeedbackLoop fl = new FeedbackLoop() {

        @Override
        public boolean isAIResponseValid() {
            return false;
        }
    };

    @Override
    public FeedbackLoop allow(String promptText, String methodName, Map<String, Object> params) {
        this.promptText = promptText;
        this.methodName = methodName;
        this.params = params;
        return fl;
    }

    @Override
    public FeedbackLoop allow(String promptText, String methodName, String params) {
        this.promptText = promptText;
        this.methodName = methodName;
        this.paramsString = params;
        return fl;
    }

}
