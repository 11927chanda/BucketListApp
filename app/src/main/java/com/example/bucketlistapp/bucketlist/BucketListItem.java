package com.example.bucketlistapp.bucketlist;

import static androidx.room.ForeignKey.CASCADE;

import java.io.Serializable;
import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.example.bucketlistapp.category.Category;


@Entity(tableName= "BUCKETLIST",
    foreignKeys = {
        @ForeignKey(entity = Category.class,
            parentColumns = "ID",
        childColumns = "CATEGORY_ID",
        onDelete = CASCADE
        ),
},
        //makes search  by category id easy
        indices = {
        @Index(value ="CATEGORY_ID")
        }
)
public class BucketListItem implements Serializable {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name="ID")
    private Integer id;
    @ColumnInfo(name="TITLE")
    private String title;
    @ColumnInfo(name="DESCRIPTION")
    private String description;
    @ColumnInfo(name="LAST_UPDATE")
    private Date lastUpdated;
    @ColumnInfo(name="STATUS")
    private String status;
    @ColumnInfo(name="BUDGET")
    private Double budget;
    @ColumnInfo(name="PRIORITY_LVL")
    private Integer priorityLvl;
    @ColumnInfo(name="CATEGORY_ID")
    private Integer categoryId;
    @ColumnInfo(name="USER_ID")
    private Integer userId;

    @Ignore
    public BucketListItem() {
    }

    public BucketListItem(String title, String description, Date lastUpdated, String status, Double budget, Integer priorityLvl, Integer categoryId, Integer userId) {
        this.title = title;
        this.description = description;
        this.lastUpdated = lastUpdated;
        this.status = status;
        this.budget = budget;
        this.priorityLvl = priorityLvl;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Integer getPriorityLvl() {
        return priorityLvl;
    }

    public void setPriorityLvl(Integer priorityLvl) {
        this.priorityLvl = priorityLvl;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BucketListItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", status='" + status + '\'' +
                ", budget=" + budget +
                ", priorityLvl=" + priorityLvl +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                '}';
    }
}
