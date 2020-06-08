package com.example.demo.mapper.xudayang_mapper;

import com.example.demo.model.xudayang_model.Word;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;



@Mapper
public interface WordMapper{
    @Insert(value="INSERT INTO TB_WORD (EMAIL,TITLE,SIZE,TYPE,PATH,UPLOADTIME) values (#{email},#{title},#{size},#{type},#{path},#{uploadTime})")
    boolean addWord(Word word);//存储word信息

    @Select("SELECT * FROM TB_WORD WHERE EMAIL = #{email}")
    List<Word> getWordList(Word word);//查询word列表

    @Select("SELECT * FROM TB_WORD WHERE ID = #{id}")
    Word getWordById(Word word);//查询单个word

    @Delete("DELETE FROM TB_WORD WHERE ID=#{id}")
    boolean deleteWordById(Integer id);//删除word

	Word getWordById(Integer id);

}