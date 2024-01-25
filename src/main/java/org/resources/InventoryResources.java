package org.resources;

import org.domain.*;
import javax.ws.rs.*;
import org.services.*;
import java.sql.Connection;
import java.util.ArrayList;
import com.google.gson.Gson;
import java.sql.SQLException;
import javax.ws.rs.core.Response;
import org.common.HikariConnection;
import com.google.gson.JsonSyntaxException;
import javassist.tools.rmi.ObjectNotFoundException;

@Path("/inventory")
public class InventoryResources {
    InventoryServiceImpl inventoryService = InventoryServiceImpl.getInstance();
    Gson gson = new Gson();


    @GET
    @Path("/list")
    @Produces("text/plain")
    public Response fetchAll(){
        try{
            ArrayList<Inventory> inventories = inventoryService.fetchAll();
            if (inventories != null)
                return Response.ok().entity(gson.toJson(inventories)).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("No records found!").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error!").build();
        }
    }


    @GET
    @Path("/{inventory_id}")
    @Produces("text/plain")
    public Response fetchByID(@PathParam("inventory_id") String inventoryId){
        try{
            Inventory inventory = inventoryService.fetchByID(inventoryId);
            if (inventory != null)  // inventory.getId() != null
                return Response.ok().entity(gson.toJson(inventory)).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid ID in request.").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error!").build();
        }
    }



    @GET
    @Path("/listByCategory")
    @Produces("text/plain")
    public Response fetchAllByCategory(@QueryParam("category") String categoryId){
        try{
            ArrayList<Inventory> inventories = inventoryService.fetchAllByCategory(categoryId);
            if (inventories != null)
                return Response.ok().entity(gson.toJson(inventories)).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("No records found!").build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error!").build();
        }
    }



    @GET
    @Path("/listByLocation")        //  "/list"
    @Produces("text/plain")
    public Response fetchAllByLocation(@QueryParam("location") String locationId){
        try{
            ArrayList<Inventory> inventories = inventoryService.fetchAllByLocation(locationId);
            if (inventories != null)
                return Response.ok().entity(gson.toJson(inventories)).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("No records found!").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error!").build();
        }
    }



    @GET
    @Path("/listByLocationAndCategory")        //  "/list"
    @Produces("text/plain")
    public Response fetchAllByLocationAndCategory(@QueryParam("location") String locationId, @QueryParam("category") String categoryId){
        try{
            ArrayList<Inventory> inventories = inventoryService.fetchAllByLocationAndCategory(locationId, categoryId);
            if (inventories != null)
                return Response.ok().entity(gson.toJson(inventories)).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("No records found!").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error!").build();
        }
    }



    @POST
    @Path("/add")
    @Produces("text/plain")
    public Response addNew(String inventoryJson){
        try{
            Inventory inventory = gson.fromJson(inventoryJson, Inventory.class);
            Inventory returnInventory = inventoryService.addInventory(inventory);
            if (returnInventory != null)
                return Response.ok().entity(gson.toJson(returnInventory)).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("The record could not be inserted!").build();
        }
        catch (JsonSyntaxException e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid payload.").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error!").build();
        }
    }



    @PUT
    @Path("/{inventory_id}")
    @Produces("text/plain")
    public Response updateByID(@PathParam("inventory_id") String inventoryId, String inventoryJson){
        try{
            Inventory inventory = gson.fromJson(inventoryJson, Inventory.class);
            inventory.setId(inventoryId);
            Inventory returnInventory = new Inventory(inventoryService.updateInventory(inventory));
            if (returnInventory.getId() != null)    // returnInventory != null
                return Response.ok().entity(gson.toJson(returnInventory)).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("The record could not be updated!").build();
        }
        catch (ObjectNotFoundException e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid ID in request.").build();
        }
        catch (JsonSyntaxException e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid payload.").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error!").build();
        }
    }



    @DELETE
    @Path("/{inventory_id}")
    @Produces("text/plain")
    public Response deleteInventoryByID(@PathParam("inventory_id") String inventoryId){
        try{
            boolean isSuccessful = inventoryService.delete(inventoryId);
            if (isSuccessful)
                return Response.ok().entity("Inventory deleted successfully.").build();
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid ID in request.").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error!").build();
        }
    }

    @POST
    @Path("/addLocation")
    @Produces("text/plain")
    public Response addLocation(String locationJson) {
        Location location = gson.fromJson(locationJson, Location.class);
        Connection connection = null;
        try {

            if(inventoryService.isLocationExist(location.getLocationName())){
                return Response.status(Response.Status.BAD_REQUEST).entity("Location already exists.").build();
            } else {
                connection = HikariConnection.getConnection();
                location = inventoryService.addLocation(location.getLocationName(), connection);
                return Response.ok().entity(gson.toJson(location)).build();
            }
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error!").build();
        }
        finally {   // note: is this correct way of handling such exception?
            try {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error!").build();
//                throw new RuntimeException(e);
            }
        }
    }

    @POST
    @Path("/addCategory")
    @Produces("text/plain")
    public Response addCategory(String categoryJson){
        Category category = gson.fromJson(categoryJson, Category.class);
        Connection connection = null;
        try {

            if (inventoryService.isCategoryExist(category.getCategoryName())){
                return Response.status(Response.Status.BAD_REQUEST).entity("Category already exists.").build();
            } else {
                connection = HikariConnection.getConnection();
                category = inventoryService.addCategory(category.getCategoryName(), connection);
                return Response.ok().entity(gson.toJson(category)).build();
            }
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error!").build();
        }
        finally {   // note: is this correct way of handling such exception?
            try {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error!").build();
//                throw new RuntimeException(e);
            }
        }
    }
}