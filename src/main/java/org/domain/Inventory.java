package org.domain;

import com.google.gson.annotations.SerializedName;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
public class Inventory {
    @SerializedName("id")
    private UUID id;
    @SerializedName("item_name")
    private String itemName;
    @SerializedName("item_quantity")
    private int itemQuantity;
    @SerializedName("item_category")
    private Category itemCategory;
    @SerializedName("item_location")
    private Location itemLocation;



    public Inventory(){
        this.itemCategory = new Category();
        this.itemLocation = new Location();
    }

    public Inventory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        Location location = new Location();

        this.setId(UUID.fromString(resultSet.getString(1)));
        this.setItemName(resultSet.getString(2));
        this.setItemQuantity(resultSet.getInt(3));
        category.setCategoryId(UUID.fromString(resultSet.getString(4)));
        category.setCategoryName(resultSet.getString(5));
        location.setLocationId(UUID.fromString(resultSet.getString(6)));
        location.setLocationName(resultSet.getString(7));
        this.setItemCategory(category);
        this.setItemLocation(location);
    }

    public Inventory(UUID id, String itemName, int itemQuantity, Category itemCategory, Location itemLocation){
        this.id = id;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemCategory = itemCategory;
        this.itemLocation = itemLocation;
    }

    public Inventory(Inventory inventory) {
        this.id = inventory.getId();
        this.itemName = inventory.getItemName();
        this.itemQuantity = inventory.getItemQuantity();
        this.itemCategory = new Category(inventory.itemCategory);
        this.itemLocation = new Location(inventory.itemLocation);
    }

    public boolean equals(Inventory inventory){
//        if(this.id == inventory.getId())
            if (this.itemName == inventory.getItemName())
                if (this.itemQuantity == inventory.getItemQuantity())
                    if (this.itemCategory.equals(inventory.itemCategory))
                        return this.itemLocation.equals(inventory.itemLocation);
        return false;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Category getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(Category category) {
        this.itemCategory = new Category(category);
    }

    public Location getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(Location location) {
        this.itemLocation = new Location(location);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemQuantity=" + itemQuantity +
                ", itemCategory=" + itemCategory +
                ", itemLocation=" + itemLocation +
                '}';
    }
}
