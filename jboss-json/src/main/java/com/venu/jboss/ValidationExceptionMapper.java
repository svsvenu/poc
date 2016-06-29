package com.venu.jboss;

import org.hibernate.validator.method.MethodConstraintViolation;
import org.hibernate.validator.method.MethodConstraintViolationException;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Created by venusurampudi on 6/27/16.
 */
@Provider
public class ValidationExceptionMapper implements
        ExceptionMapper<MethodConstraintViolationException> {

    @Override
    public javax.ws.rs.core.Response toResponse(MethodConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<String, String>();
        for (MethodConstraintViolation<?> methodConstraintViolation : ex
                .getConstraintViolations()) {
            errors.put(methodConstraintViolation.getParameterName(),
                    methodConstraintViolation.getMessage());
        }
        return Response.status(javax.ws.rs.core.Response.Status.PRECONDITION_FAILED).entity(errors)
                .build();
    }
}