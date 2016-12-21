package camel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by venusurampudi on 12/19/16.
 */
@Path("/jdg")
public class QueryOperations {


    @GET
    @Produces({MediaType.APPLICATION_JSON })

    public List<CamelExchange> getExchanges() {

        CamelExchange ce = new CamelExchange();

        String test = "bytes as a string";

        ce.setBytes(test.getBytes());

        ce.setName("Name");

        return CacheOperations.getExchangeContents();
    }

}
