package com.venu.jboss;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;



//http://localhost:8080/jboss-json-1.0-SNAPSHOT/rest/message/1

@Path("/message")
@ValidateRequest
public class JsonService {

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param")
                                     @Pattern(regexp = "[0-9]+", message = "The id must be a valid number")
                                     String msg) {


        String result = "";

        try {

             result = "Restful example : " + msg;

        }

        catch (ConstraintViolationException e) {
            for (ConstraintViolation violation : e.getConstraintViolations()) {
                System.out.println(violation.getMessage());
            }
        }


        return Response.status(200).entity(result).build();

    }

    @GET
    @Path("/get")
    @Produces("application/json")

    public PurchaseOrder getProduct() {

        PurchaseOrder po = new PurchaseOrder();

        po.setAmount(10);
        po.setName("venu");
        po.setPrice(new BigDecimal(10));

        return po;

    }

}