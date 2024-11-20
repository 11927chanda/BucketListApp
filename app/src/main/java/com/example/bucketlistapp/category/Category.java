package com.example.bucketlistapp.category;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName ="CATEGORY")
public class Category implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private Integer id;
    @ColumnInfo(name= "CATEGORY_NAME")
    private String categoryName;
    @ColumnInfo(name= "CATEGORY_IMAGE")
    private String imageCategory;

    @Ignore
    public Category() {
    }

    public Category(String categoryName,String imageCategory) {
        this.categoryName = categoryName;
        this.imageCategory = imageCategory;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(String imageCategory) {
        this.imageCategory = imageCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", imageCategory='" + imageCategory + '\'' +
                '}';
    }
}

