package explore.ozi;

import org.apache.camel.builder.RouteBuilder;

//@Component
public class OrphanCamelRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:hello?period={{timer.period}}")
                .transform(method("orphanBean", "saySomething"))
                .to("log:out");
    }
}
