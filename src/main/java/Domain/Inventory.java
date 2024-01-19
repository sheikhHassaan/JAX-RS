package Domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
public class Inventory {
    private UUID id;
    private String item_name;
    private int item_quantity;
    private Category item_category;
    private Location item_location;



    public Inventory(){
        this.item_category = new Category();
        this.item_location = new Location();
    }

    public Inventory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        Location location = new Location();

        this.setId(UUID.fromString(resultSet.getString(1)));
        this.setItem_name(resultSet.getString(2));
        this.setItem_quantity(resultSet.getInt(3));
        category.setCategory_id(UUID.fromString(resultSet.getString(4)));
        category.setCategory_name(resultSet.getString(5));
        location.setLocation_id(UUID.fromString(resultSet.getString(6)));
        location.setLocation_name(resultSet.getString(7));
        this.setItem_category(category);
        this.setItem_location(location);
    }

    public Inventory(UUID id, String item_name, int item_quantity, Category item_category, Location item_location){
        this.id = id;
        this.item_name = item_name;
        this.item_quantity = item_quantity;
        this.item_category = item_category;
        this.item_location = item_location;
    }

    public Inventory(Inventory inventory) {
        this.id = inventory.getId();
        this.item_name = inventory.getItem_name();
        this.item_quantity = inventory.getItem_quantity();
        this.item_category = new Category(inventory.item_category);
        this.item_location = new Location(inventory.item_location);
    }

    public boolean equals(Inventory inventory){
//        if(this.id == inventory.getId())
            if (this.item_name == inventory.getItem_name())
                if (this.item_quantity == inventory.getItem_quantity())
                    if (this.item_category.equals(inventory.item_category))
                        return this.item_location.equals(inventory.item_location);
        return false;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }

    public Category getItem_category() {
        return item_category;
    }

    public void setItem_category(Category category) {
        this.item_category = new Category(category);
    }

    public Location getItem_location() {
        return item_location;
    }

    public void setItem_location(Location location) {
        this.item_location = new Location(location);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", item_name='" + item_name + '\'' +
                ", item_quantity=" + item_quantity +
                ", item_category=" + item_category +
                ", item_location=" + item_location +
                '}';
    }
}
