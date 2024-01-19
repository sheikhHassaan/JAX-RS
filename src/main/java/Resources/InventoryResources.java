package Resources;

import Common.ConnectionNotFoundException;
import Domain.Inventory;
import Services.InventoryServiceImpl;
import Services.Queries;
import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

@Path("/inventory")
public class InventoryResources extends Queries {

    InventoryServiceImpl inventoryService = InventoryServiceImpl.getInstance();
    Gson gson = new Gson();


    // note: 1. Fetch By ID, http://localhost:8080/inventory/{inventory_id}/ (HTTP GET)
    @GET
    @Path("/{inventory_id}")
    @Produces("text/plain")
    public Response FetchByID(@PathParam("inventory_id") String inventory_id){
        try{
            Inventory inventory = inventoryService.fetchByID(UUID.fromString(inventory_id));
            if (inventory != null)
                return Response.ok().entity(gson.toJson(inventory)).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("Record not found!").build();
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



    // note: 2. Fetch All, http://localhost:8080/inventory/list (HTTP GET)
    @GET
    @Path("/list")
    @Produces("text/plain")
    public Response FetchAll(){
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
    @Path("/listByLocation")        //  "/list"
    @Produces("text/plain")
    public Response FetchAllByLocation(@QueryParam("location") String location_id){
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
    @GET
    @Path("/listByLocationAndCategory")        //  "/list"
    @Produces("text/plain")
    public Response FetchAllByLocationAndCategory(@QueryParam("location") String location_id, @QueryParam("category") String category_id){
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
    @POST
    @Path("/add")
    @Produces("text/plain")
    public Response addNew(String inventoryJson){
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
    @PUT
    @Path("/{inventory_id}")
    @Produces("text/plain")
    public Response updateByID(@PathParam("inventory_id") String inventory_id, String inventoryJson){
        try{
            Inventory inventory = gson.fromJson(inventoryJson, Inventory.class);
            inventory.setId(UUID.fromString(inventory_id));
            Inventory returnInventory = new Inventory(inventoryService.updateInventory(inventory));
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
    @DELETE
    @Path("/{inventory_id}")
    @Produces("text/plain")
    public Response Delete(@PathParam("inventory_id") String inventory_id){
        try{
            boolean isSuccessful = inventoryService.delete(UUID.fromString(inventory_id));
            if (isSuccessful)
                return Response.ok().entity("Inventory deleted successfully.").build();
            return Response.status(Response.Status.BAD_REQUEST).entity("The specified record doesn't exist!").build();
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