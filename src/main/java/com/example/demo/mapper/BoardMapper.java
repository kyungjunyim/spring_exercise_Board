package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import com.example.demo.dto.BoardDto;

public interface BoardMapper {
	public List<BoardDto> findAll();
	public List<BoardDto> findAllByIdx(Map<String, List<String>> map);
	public List<BoardDto> findAllWithCondition(Map<String, String> map);
	public BoardDto findOneByIdx(int idx);
	public void addBoard(Map<String, String> map);
	public void updateViewCount(int idx);
	public void modifyBoard(Map<String, Object> map);
	public void removeBoard(int idx);
	public void removeAll();
}
