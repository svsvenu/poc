package venu;


import javax.ws.rs.Produces;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Path("/purchaseorder")
public class PurchaseOrder {

    @NotNull
    @Size(min = 2, max = 50)
   private String name;
   private BigDecimal price;
   private int amount;

@GET
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

private String getName() {
   return name;
}

public void setName(String name) {
   this.name = name;
}

   @GET
   @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

public BigDecimal getPrice() {
   return price;
}

public void setPrice(BigDecimal price) {
   this.price = price;
}

   @GET
   @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

public int getAmount() {
   return amount;
}

public void setAmount(int amount) {
   this.amount = amount;
}
}


