package com.example.demo.service.xudayang_service;

import com.example.demo.model.xudayang_model.Video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.xudayang_mapper.VideoMapper;


import java.util.List;


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