package io.github.vishalmysore;


import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
@Agent
public class CompareCarService  {
    public CompareCarService() {
        log.info("created compare car service");
    }

    @Action( description = "compare two cars")
    public String compareCar(String car1 , String car2) {
        log.info(car2);
        log.info(car1);
        // implement the comparison logic here
        return " this is better - "+car2;
    }
}
