package io.github.herbert;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class JacksonXmlListsTest extends CamelTestSupport {
    @Test
    public void test() throws Exception {
        MockEndpoint resultEndpoint = resolveMandatoryEndpoint("mock:result", MockEndpoint.class);
        resultEndpoint.expectedMessageCount(1);

        final String xml = "<root><list><item>ONE</item><item>TWO</item><item>THREE</item></list></root>";
        template.sendBody("direct:jacksonxml", xml);

        resultEndpoint.assertIsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                from("direct:jacksonxml")
                        .unmarshal().jacksonxml()
                        .log("Body: ${body}")
                        .to("mock:result");
            }
        };
    }
}


