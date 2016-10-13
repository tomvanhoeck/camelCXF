package explore.ozi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by TomVH on 11/10/2016.
 */
@Component("orphanBean")
public class OrphanBean {


    @Value("${greeting}")
    private String say;

    public String saySomething() {
        return say;
    }
}
