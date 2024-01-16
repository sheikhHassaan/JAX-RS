package Domain;

import java.util.UUID;
public class Inventory {
    private UUID id;
    private String item_name;
    private int item_quantity;
    private Category item_category;
    private Location item_location;



    public Inventory(){}

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
