package com.venu.standalone;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;
import org.apache.camel.main.MainSupport;
import org.apache.camel.main.MainListenerSupport;
import org.apache.camel.model.rest.RestBindingMode;

/**
 * Created by venusurampudi on 1/1/18.
 * This is a standalone java application that exposes a rest end point that consumes JSON
 */
public class Camel {

 //Leveraging the camel Main as the camel context is non-blocking which cant keep the routes running (The main thread
 //will shut down
            private Main main;

            public static void main(String[] args) throws Exception {

                Camel standalone = new Camel();

                standalone.boot();
        }

    public void boot() throws Exception {
        // create a Main instance
        main = new Main();
        // add routes
            main.addRouteBuilder(new MyRouteBuilder());
        // add event listener





            main.addMainListener(new Events());

        System.out.println("Starting Camel. Use ctrl + c to terminate the JVM.\n");
// Run is a blocking call
        main.run();

        }


    private static class MyRouteBuilder extends RouteBuilder {

        @Override
        public void configure() throws Exception {

            restConfiguration().component("netty4-http").port(8090).bindingMode(RestBindingMode.auto);

          //  restConfiguration().component("restlet").host("localhost").port(8090).bindingMode(RestBindingMode.auto);

            rest("/user")
                    .get("/get").consumes("application/json").to("direct:getuser")
                    .post("/send").type(UserPojo.class).outType(Response.class).to("direct:senduser");

            rest("/hello")
                    .get("/get").produces("text/html").to("direct:getuser");


            from("direct:getuser").routeId("getUserRoute")
                    .transform().constant("Hello World, again 7");

            from("direct:senduser").routeId("sendUserRoute").log("body is " + "${body}")
                    .process(new Processor() {
                        public void process(Exchange exchange) throws Exception {
                            Response cp = new Response();

                            cp.setStatus(0);
                            cp.setMessage("Succesfully added user");

                            exchange.getIn().setBody(cp);
                        }
                    });

        }
    }


 // Track Camel life cycle events
    public static class Events extends MainListenerSupport {

    @Override
    public void afterStart(MainSupport main) {
    System.out.println("MainExample with Camel is now started!");
    }

    @Override
    public void beforeStop(MainSupport main) {
    System.out.println("MainExample with Camel is now being stopped!");
    }
    }
}