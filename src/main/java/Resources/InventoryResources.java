package Resources;

import Common.SecurityFilter;
import Services.InventoryServiceImpl;
import Services.Queries;
import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/inventory")
public class InventoryResources extends Queries {

    InventoryServiceImpl inventoryService = InventoryServiceImpl.getInstance();
    Gson gson = new Gson();
    SecurityFilter securityFilter = new SecurityFilter();

    @GET
    @Path("/main")
    @Produces("text/plain")
    public String main() {
        return "Inside inventory resources!";
    }

    /*
    // note: 1. Fetch By ID, http://localhost:8080/inventory/{inventory_id}/ (HTTP GET)

    //Sample response JSON: {"id":1,"item_name":"iPhone 13","item_quantity":10,"item_category":
    //{"id":24,"category_name":"Phone"},"item_location":{"id":7,"location_name":"Phoenix"}}

    @GET
    @Path("/{inventory_id}")
    @Produces("text/plain")
    public Response fetchByID(@PathParam("inventory_id") Integer inventory_id){

        return ;
    }

    // note: 2. Fetch All, http://localhost:8080/inventory/list (HTTP GET)

    @GET
    @Path("/list")
    @Produces("text/plain")
    public Response FetchAll(){

        return ;
    }

    // note: Fetch All By Category, http://localhost:8080/inventory/listByCategory?category=22 (HTTP GET)

    @GET
    @Path("/listByCategory")
    @Produces("text/plain")
    public Response fetchAllByCategory(@QueryParam("category") Integer category_id){

        return ;
    }

    // note: Fetch All By Location, http://localhost:8080/inventory/list?location=7 (HTTP GET)

    @GET
    @Path("/list")
    @Produces("text/plain")
    public Response fetchAllByLocation(@QueryParam("location") Integer location_id){

        return ;
    }

    // note: Fetch All By Location And Category, http://localhost:8080/inventory/list?location=7&category=22 (HTTP GET)

    //Sample response JSON: [{"id":1,"item_name":"iPhone 13","item_quantity":10,"item_category":
    //{"id":24,"category_name":"Phone"},"item_location":{"id":7,"location_name":"Phoenix"}},
    //{"id":2,"item_name":"iPhone 13 Pro Max","item_quantity":5,"item_category":
    //{"id":24,"category_name":"Phone"},"item_location":{"id":7,"location_name":"Phoenix"}},
    //{"id":3,"item_name":"Macbook Air","item_quantity":15,"item_category":
    //{"id":22,"category_name":"Laptop"},"item_location":{"id":9,"location_name":"Arizona"}},
    //{"id":4,"item_name":"Macbook Pro","item_quantity":20,"item_category":
    //{"id":22,"category_name":"Laptop"},"item_location":{"id":9,"location_name":"Arizona"}}]

    @GET
    @Path("/list")
    @Produces("text/plain")
    public Response fetchAllByLocationAndCategory(@QueryParam("location") Integer location_id, @QueryParam("category") Integer category_id){

        return ;
    }

    // note: Add New Inventory Item, http://localhost:8080/inventory/add (HTTP POST)

    //Sample Payload:  {"item_name":"iPhone 12","item_quantity":10,"item_category":
    //{"id":24,"category_name":"Phone"},"item_location":{"id":7,"location_name":"Phoenix"}}

    //Sample Response:  {"id":11,"item_name":"iPhone 12","item_quantity":10,"item_category":
    //{"id":24,"category_name":"Phone"},"item_location":{"id":7,"location_name":"Phoenix"}}

    @POST
    @Path("/add")
    @Produces("text/plain")
    public Response addNew(String inventoryJson){

        return ;
    }


    // note: Update Existing Inventory Item By Id, http://localhost:8080/inventory/{inventory_id}/ (HTTP PUT)

    //Sample Payload:  {"item_name":"iPhone 12","item_quantity":15,"item_category":
    //{"id":24,"category_name":"Phone"},"item_location":{"id":7,"location_name":"Phoenix"}}

    //Sample Response:  {"id":11,"item_name":"iPhone 12","item_quantity":15,"item_category":
    //{"id":24,"category_name":"Phone"},"item_location":{"id":7,"location_name":"Phoenix"}}

    @PUT
    @Path("/{inventory_id}")
    @Produces("text/plain")
    public Response updateByID(@PathParam("inventory_id") Integer inventory_id, String inventoryJson){

        return ;
    }

    // note: Delete Existing Inventory Item By Id, http://localhost:8080/inventory/{inventory_id}/ (HTTP DELETE)

    //Sample Payload: NA

    //Sample Response: {"message" : "OK"}

    @DELETE
    @Path("/{inventory_id}")
    @Produces("text/plain")
    public Response delete(@PathParam("inventory_id") Integer inventory_id){

        return ;
    }

    */
    @POST
    @Path("/add")
    @Produces("text/plain")
    public Response addNew(String inventoryJson){

        String
        return ;
    }

}