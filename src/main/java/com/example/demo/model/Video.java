package com.example.demo.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Video implements Serializable {
	/**
     *视频信息
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String email;
	private String type;
	private String size;
	private String path;
	private String title;
	private Timestamp uploadTime;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
        this.id = id;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(final String email){
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(final String size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(final Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Override
	public String toString() {
		return "Video{" +
				"id=" + id +
				", type='" + type + '\'' +
				", size='" + size + '\'' +
				", path='" + path + '\'' +
				", title='" + title + '\'' +
				", uploadTime=" + uploadTime +
				'}';
	}
}
