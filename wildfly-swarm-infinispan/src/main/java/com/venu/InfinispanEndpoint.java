package com.venu;

/**
 * Created by venusurampudi on 10/12/16.
 */

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.infinispan.Cache;

@Path("/infinispan")
public class InfinispanEndpoint {

   // @Inject
   // Cache<String, String> cache;

    @GET
    @Path("cache")
    public Response printContent() {

        return Response.ok("hello").build();
    }

    @POST
    @Path("cache")
    public Response addSomethingToTheCache(String text) {
      //  cache.put(text, text);
        return Response.created(null).build();
    }
}