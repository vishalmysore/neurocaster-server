package io.github.vishalmysore;

import com.t4a.predict.PredictionLoader;
import com.t4a.predict.Tools4AI;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "tools4ai-tools")
//@RestController
public class Tools4AIActions {

    @ReadOperation
    public String listActions() {
        return Tools4AI.getActionListAsJSONRPC();
    }

}
