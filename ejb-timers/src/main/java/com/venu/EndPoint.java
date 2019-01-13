package com.venu;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/test")
@Stateless
public class EndPoint {

    @EJB
    public PeriodicTimer pt;

    @Resource
    TimerService ts;

    @GET()
    @Produces("application/json")
    public Response getRespone(){
        Response resp = new Response();

        pt.resetTimer(15);

        System.out.println("Timer is " + pt);

       resp.setData(ts.getTimers().size() + "");

        return resp;
    }




}
