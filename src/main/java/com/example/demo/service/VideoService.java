package com.example.demo.service;

import com.example.demo.model.Video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.VideoMapper;


import java.util.List;

/*
@auther:yunfei_fan
*/
@Service
public class VideoService{

    @Autowired
    private VideoMapper videoMapper;

    public boolean addVideo(Video video){
        return videoMapper.addVideo(video);
        //视频存入数据库
    }
    
    public List<Video> getVideoList(Video video){
        return videoMapper.getVideoList(video);
        //查询视频列表
    }

    public Video getVideoById(Video video){
        return videoMapper.getVideoById(video);
        //查询单个视频
    }

    public boolean deleteVideoById(Integer id){
        return videoMapper.deleteVideoById(id);
        //删除视频
    }

	public List<Video> getVideoList() {
		return null;
	}


}


