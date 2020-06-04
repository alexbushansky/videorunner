package com.videorunner.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "video")
public class Video {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "path_to_video")
    @NotNull
    private String path;

    @Column(name = "front_image")
    @NotNull
    private String frontImage;

    @Column(name = "name_of_video")
    @NotNull
    private String name;

    @Column(name = "describe_of_video")
    @NotNull
    private String describe;


    @Basic
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany
    @JoinColumn(name = "video_id")
    private Set<Comment> comments;






    public Video() {
    }

    public Video(String path, String frontImage,String name, String describe, User user) {
        this.path = path;
        this.name = name;
        this.describe = describe;
        this.user = user;
        this.dateCreated = new Date();
        this.frontImage = frontImage;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    @Override
    public String toString() {
        return "Video{" +
                "path='" + path + '\'' +
                ", frontImage='" + frontImage + '\'' +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", dateCreated=" + dateCreated +
                ", user=" + user +
                '}';
    }
}
