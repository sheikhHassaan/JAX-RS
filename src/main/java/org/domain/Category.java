package org.domain;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("category_id")
    private String categoryId;
    @SerializedName("category_name")
    private String categoryName;



    public Category(){}

    public Category(String catId, String catName){
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
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
