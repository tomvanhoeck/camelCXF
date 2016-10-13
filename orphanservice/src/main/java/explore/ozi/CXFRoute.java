package explore.ozi;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CXFRoute extends RouteBuilder {
    // CXF webservice using code first approach
    private String uri = "cxf:/incident?serviceClass=" + OZICxfService.class.getName();

    @Override
    public void configure() throws Exception {

            from(uri)
                    .to("log:input")
                    // send the request to the route to handle the operation
                    // the name of the operation is in that header
                    .recipientList(simple("direct:${header.operationName}"));

            // report incident
            from("direct:read")
                    .process(new Processor() {
                        public void process(Exchange exchange) throws Exception {
                            // get the id of the input
                            String input = exchange.getIn().getBody(InputPOJOForCXF.class).getInput();

                            // set reply including the id
                            OutputPOJOForCxf output = new OutputPOJOForCxf();
                            output.setMessage("OK;" + input);
                            exchange.getOut().setBody(output);
                        }
                    })
                    .to("log:output");
    }
}
