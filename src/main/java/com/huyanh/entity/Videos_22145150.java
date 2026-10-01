package com.huyanh.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "Videos")
@Table(name = "Videos")
public class Videos_22145150 implements Serializable {
    @Id @Column(name = "VideoId", length = 50) private String videoId;
    @Column(name = "Title", length = 200) private String title;
    @Column(name = "Poster", length = 50) private String poster;
    @Column(name = "Views") private Integer views;
    @Column(name = "Description", length = 500) private String description;
    @Column(name = "Active") private Boolean active;
    @Column(name = "Price") private java.math.BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "CategoryId") private Category_22145150 category;

    @Transient private long shareCount;
    @Transient private long likeCount;

    /** Poster là URL (http...) hoặc tên file nằm trong webapp/assets/images */
    public String getPosterUrl() {
        if (poster == null || poster.trim().isEmpty()) return "https://placehold.co/300x200?text=No+Image";
        return poster.startsWith("http") ? poster : "/assets/images/" + poster;
    }

    public String getVideoId() { return videoId; }
    public void setVideoId(String videoId) { this.videoId = videoId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
    public Integer getViews() { return views; }
    public void setViews(Integer views) { this.views = views; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public java.math.BigDecimal getPrice() { return price; }
    public void setPrice(java.math.BigDecimal price) { this.price = price; }
    public Category_22145150 getCategory() { return category; }
    public void setCategory(Category_22145150 category) { this.category = category; }
    public long getShareCount() { return shareCount; }
    public void setShareCount(long shareCount) { this.shareCount = shareCount; }
    public long getLikeCount() { return likeCount; }
    public void setLikeCount(long likeCount) { this.likeCount = likeCount; }
}
