package com.venu;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

import javax.inject.Named;
import java.util.Map;

/**
 * Created by venusurampudi on 8/19/16.
 */
@Named
public class VenuComponent extends DefaultComponent {

    public VenuComponent(CamelContext ctx){

        super(ctx);
    }

    protected Endpoint createEndpoint(
            String uri, String remaining,
            Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new VenuEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
