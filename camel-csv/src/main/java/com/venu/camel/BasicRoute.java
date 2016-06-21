package com.venu.camel;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
 
@Startup
@ApplicationScoped
@ContextName("cdi-context")
 
public class BasicRoute extends RouteBuilder {
 
    @Override
    public void configure() throws Exception {
       from("timer://foo?fixedRate=true&period=30000").transform(body().prepend("Hi ")).log(body().toString()); 
    }
 
} 