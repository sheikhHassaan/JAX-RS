package org.domain;

import java.util.UUID;

public class Category {
    private UUID category_id;
    private String category_name;



    public Category(){}

    public Category(UUID cat_id, String cat_name){
        this.category_id = cat_id;
        this.category_name = cat_name;
    }

    public Category(String cat_name){
        this.category_name = cat_name;
    }

    public Category(Category category){
        this.category_id = category.getCategory_id();
        this.category_name = category.getCategory_name();
    }

    public boolean equals(Category category){
        return this.category_name == category.getCategory_name();
    }

    public UUID getCategory_id() {
        return category_id;
    }

    public void setCategory_id(UUID category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
