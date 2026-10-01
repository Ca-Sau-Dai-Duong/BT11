package com.huyanh.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity(name = "Shares")
@Table(name = "Shares")
public class Shares_22145150 implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShareId") private Integer shareId;
    @Column(name = "Emails", length = 50) private String emails;
    @Temporal(TemporalType.DATE)
    @Column(name = "SharedDate") private Date sharedDate;
    @ManyToOne @JoinColumn(name = "Username") private Users_22145150 user;
    @ManyToOne @JoinColumn(name = "VideoId") private Videos_22145150 video;

    public Integer getShareId() { return shareId; }
    public void setShareId(Integer shareId) { this.shareId = shareId; }
    public String getEmails() { return emails; }
    public void setEmails(String emails) { this.emails = emails; }
    public Date getSharedDate() { return sharedDate; }
    public void setSharedDate(Date sharedDate) { this.sharedDate = sharedDate; }
    public Users_22145150 getUser() { return user; }
    public void setUser(Users_22145150 user) { this.user = user; }
    public Videos_22145150 getVideo() { return video; }
    public void setVideo(Videos_22145150 video) { this.video = video; }
}
