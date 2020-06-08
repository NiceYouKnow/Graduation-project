package com.example.demo.mapper.xudayang_mapper;

import com.example.demo.model.xudayang_model.Image;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;



@Mapper
public interface ImageMapper{
    @Insert(value="INSERT INTO TB_IMAGE (EMAIL,TITLE,SIZE,TYPE,PATH,UPLOADTIME) values (#{email},#{title},#{size},#{type},#{path},#{uploadTime})")
    boolean addImage(Image image);//存储图片信息

    @Select("SELECT * FROM TB_IMAGE WHERE EMAIL = #{email}")
    List<Image> getImageList(Image image);//查询图片列表

    @Select("SELECT * FROM TB_IMAGE WHERE ID = #{id}")
    Image getImageById(Image image);//查询单个图片

    @Delete("DELETE FROM TB_IMAGE WHERE ID=#{id}")
    boolean deleteImageById(Integer id);//删除图片

	Image getImageById(Integer id);

}