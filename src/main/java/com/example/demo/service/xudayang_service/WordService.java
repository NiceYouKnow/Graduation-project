package com.example.demo.service.xudayang_service;

import com.example.demo.model.xudayang_model.Word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.xudayang_mapper.WordMapper;


import java.util.List;


@Service
public class WordService{

    @Autowired
    private WordMapper wordMapper;

    public boolean addWord(Word word){
        return wordMapper.addWord(word);
        //word存入数据库
    }
    
    public List<Word> getWordList(Word word){
        return wordMapper.getWordList(word);
        //查询word列表
    }

    public Word getWordById(Word word){
        return wordMapper.getWordById(word);
        //查询单个word
    }

    public boolean deleteWordById(Integer id){
        return wordMapper.deleteWordById(id);
        //删除word
    }

	public List<Word> getWordList() {
		return null;
	}


}