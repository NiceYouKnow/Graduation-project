package com.example.demo.service.xudayang_service;

import com.example.demo.model.xudayang_model.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.xudayang_mapper.ImageMapper;


import java.util.List;


@Service
public class ImageService{

    @Autowired
    private ImageMapper ImageMapper;

    public boolean addImage(Image image){
        return ImageMapper.addImage(image);
        //视频存入数据库
    }
    
    public List<Image> getImageList(Image image){
        return ImageMapper.getImageList(image);
        //查询视频列表
    }

    public Image getImageById(Image image){
        return ImageMapper.getImageById(image);
        //查询单个视频
    }

    public boolean deleteImageById(Integer id){
        return ImageMapper.deleteImageById(id);
        //删除视频
    }

	public List<Image> getImageList() {
		return null;
	}


}