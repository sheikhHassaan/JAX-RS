package services;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.ws.rs.core.Application;
import org.resources.InventoryResources;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.core.Response;

public class JerseyTesting extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(InventoryResources.class);
    }

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
    }

    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    void integrationTest() {
        Response response;
        response = target("/inventory/{inventory_id}")
                .resolveTemplate("inventory_id", "65cc75b2-e697-4d45-a170-ac4db3a63980")
                .request().delete();

        Assert.assertNotNull("Response is null", response);
        System.out.println(response.getEntity().toString());
        Assert.assertEquals("Response status is not 200", 200, response.getStatus());

        String entity = response.readEntity(String.class);
        Assert.assertEquals("Unexpected response entity", "Inventory deleted successfully.", entity);
    }
}