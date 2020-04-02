package com.example.demo.mapper;

import com.example.demo.model.Video;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/*
auther:yunfei_fan
*/
@Mapper
public interface VideoMapper{
    @Insert(value="INSERT INTO TB_VIDEO (EMAIL,TITLE,SIZE,TYPE,PATH,UPLOADTIME) values (#{email},#{title},#{size},#{type},#{path},#{uploadTime})")
    boolean addVideo(Video video);//存储视频信息

    @Select("SELECT * FROM TB_VIDEO WHERE EMAIL = #{email}")
    List<Video> getVideoList(Video video);//查询视频列表

    @Select("SELECT * FROM TB_VIDEO WHERE ID = #{id}")
    Video getVideoById(Video video);//查询单个视频

    @Delete("DELETE FROM TB_VIDEO WHERE ID=#{id}")
    boolean deleteVideoById(Integer id);//删除视频

	Video getVideoById(Integer id);

}