package org.domain;

import com.google.gson.annotations.SerializedName;
import java.util.UUID;

public class Category {
    @SerializedName("category_id")
    private UUID categoryId;
    @SerializedName("category_name")
    private String categoryName;



    public Category(){}

    public Category(UUID catId, String catName){
        this.categoryId = catId;
        this.categoryName = catName;
    }

    public Category(String catName){
        this.categoryName = catName;
    }

    public Category(Category category){
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
    }

    public boolean equals(Category category){
        return this.categoryName == category.getCategoryName();
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
