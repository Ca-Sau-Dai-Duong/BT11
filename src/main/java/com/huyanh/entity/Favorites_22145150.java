package com.huyanh.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity(name = "Favorites")
@Table(name = "Favorites")
public class Favorites_22145150 implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FavoriteId") private Integer favoriteId;
    @Temporal(TemporalType.DATE)
    @Column(name = "LikedDate") private Date likedDate;
    @ManyToOne @JoinColumn(name = "VideoId") private Videos_22145150 video;
    @ManyToOne @JoinColumn(name = "Username") private Users_22145150 user;

    public Integer getFavoriteId() { return favoriteId; }
    public void setFavoriteId(Integer favoriteId) { this.favoriteId = favoriteId; }
    public Date getLikedDate() { return likedDate; }
    public void setLikedDate(Date likedDate) { this.likedDate = likedDate; }
    public Videos_22145150 getVideo() { return video; }
    public void setVideo(Videos_22145150 video) { this.video = video; }
    public Users_22145150 getUser() { return user; }
    public void setUser(Users_22145150 user) { this.user = user; }
}
