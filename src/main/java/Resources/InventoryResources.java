package Resources;

import Common.ConnectionNotFoundException;
import Common.SecurityFilter;
import Domain.Inventory;
import Services.InventoryServiceImpl;
import Services.Queries;
import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

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


    // note: 1. Fetch By ID, http://localhost:8080/inventory/{inventory_id}/ (HTTP GET)

    //Sample response JSON: {"id":1,"item_name":"iPhone 13","item_quantity":10,"item_category":
    //{"id":24,"category_name":"Phone"},"item_location":{"id":7,"location_name":"Phoenix"}}

    @GET
    @Path("/{inventory_id}")
    @Produces("text/plain")
    public Response FetchByID(@PathParam("inventory_id") String inventory_id){
        // note: Have to return "Inventory", no payload.
        try{
//            securityFilter.filter(requestContext); @Context ContainerRequestContext requestContext,
            Inventory inventory = inventoryService.fetchByID(UUID.fromString(inventory_id));
            if (inventory != null)
                return Response.ok().entity(gson.toJson(inventory)).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("Record not found!").build();
        }
//        catch (IOException e) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Wrong credentials.").build();
//        }
        catch (ConnectionNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Connection could not be established.").build();
        }
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("SQL Exception occurred.").build();
        }
        catch (ClassNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Class not found.").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong.").build();
        }
    }

    // note: 2. Fetch All, http://localhost:8080/inventory/list (HTTP GET)

    @GET
    @Path("/list")
    @Produces("text/plain")
    public Response FetchAll(){
        // note: Have to return "ArrayList<Inventory>", no payload.
        try{
            ArrayList<Inventory> inventories = inventoryService.fetchAll();
            if (inventories != null)
                return Response.ok().entity(gson.toJson(inventories)).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("No records found!").build();
        }
        catch (ConnectionNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Connection could not be established.").build();
        }
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("SQL Exception occurred.").build();
        }
        catch (ClassNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Class not found.").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong.").build();
        }
    }

    // note: 3. Fetch All By Category, http://localhost:8080/inventory/listByCategory?category=22 (HTTP GET)

    @GET
    @Path("/listByCategory")
    @Produces("text/plain")
    public Response FetchAllByCategory(@QueryParam("category") String category_id){
        // note: Have to return "ArrayList<Inventory>", no payload.
        try{
            ArrayList<Inventory> inventories = inventoryService.fetchAllByCategory(UUID.fromString(category_id));
            if (inventories != null)
                return Response.ok().entity(gson.toJson(inventories)).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("No records found!").build();
        }
        catch (ConnectionNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Connection could not be established.").build();
        }
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("SQL Exception occurred.").build();
        }
        catch (ClassNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Class not found.").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong.").build();
        }
    }

    // note: 4. Fetch All By Location, http://localhost:8080/inventory/list?location=7 (HTTP GET)

    @GET
    @Path("/list")
    @Produces("text/plain")
    public Response FetchAllByLocation(@QueryParam("location") String location_id){
        // note: Have to return "ArrayList<Inventory>", no payload.
        try{
            ArrayList<Inventory> inventories = inventoryService.fetchAllByLocation(UUID.fromString(location_id));
            if (inventories != null)
                return Response.ok().entity(gson.toJson(inventories)).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("No records found!").build();
        }
        catch (ConnectionNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Connection could not be established.").build();
        }
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("SQL Exception occurred.").build();
        }
        catch (ClassNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Class not found.").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong.").build();
        }
    }

    // note: 5. Fetch All By Location And Category, http://localhost:8080/inventory/list?location=7&category=22 (HTTP GET)

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
    public Response FetchAllByLocationAndCategory(@QueryParam("location") String location_id, @QueryParam("category") String category_id){
        // note: Have to return "ArrayList<Inventory>", no payload.
        try{
            ArrayList<Inventory> inventories = inventoryService.fetchAllByLocationAndCategory(UUID.fromString(location_id), UUID.fromString(category_id));
            if (inventories != null)
                return Response.ok().entity(gson.toJson(inventories)).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("No records found!").build();
        }
        catch (ConnectionNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Connection could not be established.").build();
        }
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("SQL Exception occurred.").build();
        }
        catch (ClassNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Class not found.").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong.").build();
        }
    }

    // note: 6. Add New Inventory Item, http://localhost:8080/inventory/add (HTTP POST)

    //Sample Payload:  {"item_name":"iPhone 12","item_quantity":10,"item_category":
    //{"id":24,"category_name":"Phone"},"item_location":{"id":7,"location_name":"Phoenix"}}

    //Sample Response:  {"id":11,"item_name":"iPhone 12","item_quantity":10,"item_category":
    //{"id":24,"category_name":"Phone"},"item_location":{"id":7,"location_name":"Phoenix"}}

    @POST
    @Path("/add")
    @Produces("text/plain")
    public Response addNew(String inventoryJson){
        // note: Inventory-Json received as payload. Have to parse it and run the add-function on it.
        //  Have to return "Inventory" in response.
        try{
            Inventory inventory = gson.fromJson(inventoryJson, Inventory.class);
            inventory.setId(UUID.randomUUID());
            Inventory returnInventory = inventoryService.addInventory(inventory);
            if (returnInventory != null)
                return Response.ok().entity(gson.toJson(returnInventory)).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("The record could not be inserted!").build();
        }
        catch (ConnectionNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Connection could not be established.").build();
        }
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("SQL Exception occurred.").build();
        }
        catch (ClassNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Class not found.").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong.").build();
        }
    }


    // note: 7. Update Existing Inventory Item By Id, http://localhost:8080/inventory/{inventory_id}/ (HTTP PUT)

    //Sample Payload:  {"item_name":"iPhone 12","item_quantity":15,"item_category":
    //{"id":24,"category_name":"Phone"},"item_location":{"id":7,"location_name":"Phoenix"}}

    //Sample Response:  {"id":11,"item_name":"iPhone 12","item_quantity":15,"item_category":
    //{"id":24,"category_name":"Phone"},"item_location":{"id":7,"location_name":"Phoenix"}}

    @PUT
    @Path("/{inventory_id}")
    @Produces("text/plain")
    public Response updateByID(@PathParam("inventory_id") String inventory_id, String inventoryJson){
        // note: Inventory-Json received as payload. Have to parse it and run the update-function on it.
        //  Have to return "Inventory" in response.
        try{
            Inventory inventory = gson.fromJson(inventoryJson, Inventory.class);
            inventory.setId(UUID.fromString(inventory_id));
            Inventory returnInventory = inventoryService.updateInventory(inventory);
            if (returnInventory != null)
                return Response.ok().entity(gson.toJson(returnInventory)).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("The record could not be updated!").build();
        }
        catch (ConnectionNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Connection could not be established.").build();
        }
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("SQL Exception occurred.").build();
        }
        catch (ClassNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Class not found.").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong.").build();
        }
    }

    // note: 8. Delete Existing Inventory Item By Id, http://localhost:8080/inventory/{inventory_id}/ (HTTP DELETE)

    //Sample Payload: NA

    //Sample Response: {"message" : "OK"}

    @DELETE
    @Path("/{inventory_id}")
    @Produces("text/plain")
    public Response Delete(@PathParam("inventory_id") String inventory_id){
        // note: Have to run delete-function on the inventory_id & return "Message" in response. No payload.
        try{
            boolean isSuccessful = inventoryService.delete(UUID.fromString(inventory_id));
            if (isSuccessful)
                return Response.ok().entity("Inventory deleted successfully.").build();
            return Response.status(Response.Status.BAD_REQUEST).entity("The record could not be deleted!").build();
        }
        catch (ConnectionNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Connection could not be established.").build();
        }
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("SQL Exception occurred.").build();
        }
        catch (ClassNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Class not found.").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong.").build();
        }
    }
}