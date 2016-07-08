package com.venu.springws;

import org.springframework.ws.server.EndpointInterceptor;

import org.springframework.ws.context.MessageContext;

/**
 * Created by venusurampudi on 7/8/16.
 */
public class CustomEndPointInterceptor  implements EndpointInterceptor {


    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        System.out.println("Global Request Handling" + messageContext.);
        return true;
    }


    public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
        System.out.println("Global Response Handling");
        return true;
    }


    public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
        System.out.println("Global Exception Handling");
        return true;
    }


    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
        System.out.println("Execute Code After Completion");
    }


}
