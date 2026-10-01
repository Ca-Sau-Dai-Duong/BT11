package com.huyanh.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "Category")
@Table(name = "Category")
public class Category_22145150 implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId") private Integer categoryId;
    @Column(name = "Categoryname", length = 100) private String categoryname;
    @Column(name = "Categorycode", length = 100) private String categorycode;
    @Column(name = "Images", length = 500) private String images;
    @Column(name = "Status") private Boolean status;

    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    public String getCategoryname() { return categoryname; }
    public void setCategoryname(String categoryname) { this.categoryname = categoryname; }
    public String getCategorycode() { return categorycode; }
    public void setCategorycode(String categorycode) { this.categorycode = categorycode; }
    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }
    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
}
