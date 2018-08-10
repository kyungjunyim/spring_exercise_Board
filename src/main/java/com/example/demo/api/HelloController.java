package com.example.demo.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BoardDto;
import com.example.demo.mapper.BoardMapper;

@RestController
public class HelloController {
	private BoardMapper mapper;
	private SqlSession session;

	public void connect() {
		String config = "com/example/demo/config/config.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(config);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		session = factory.openSession();
		mapper = session.getMapper(BoardMapper.class);
	}

	public void commit() {
		session.commit();
	}

	public void close() {
		session.close();
	}

	@RequestMapping("/")
	public String index() {
		return "Hello World!";
	}

	@RequestMapping("/board/list")
	public List<BoardDto> list() {
		connect();
		List<BoardDto> list = mapper.findAll();
		close();
		return list;
	}

	@RequestMapping(value = "/board/get", method = RequestMethod.GET)
	public List<BoardDto> listByIdx(@RequestParam(name = "where", required = false) String where) {
		connect();
		List<BoardDto> list = null;
		if (where != null && !where.isEmpty()) {
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			List<String> whereList = new ArrayList<String>();
			String[] wheres = where.split(",");
			for(String str : wheres) {
				whereList.add(str);
			}
			map.put("list", whereList);
			list = mapper.findAllByIdx(map);
		} else {
			list = mapper.findAll();
		}
		close();
		return list;
	}

	@RequestMapping(value = "/board/add", method = RequestMethod.GET)
	public String add(@RequestParam(name = "title", required = true) String title,
			@RequestParam(name = "contents", required = true) String contents) {
		connect();
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", title);
		map.put("contents", contents);
		mapper.addBoard(map);
		commit();
		close();
		return "{\"result\": \"OK\"}";
	}

	@RequestMapping("/board/search")
	public List<BoardDto> search(@RequestParam String condition, @RequestParam String keyword) {
		connect();
		List<BoardDto> list = null;
		List<BoardDto> list2 = null;
		Map<String, String> map = new HashMap<String, String>();

		if (condition.equals("all")) {
			map.put("condition", "title");
			map.put("keyword", keyword);
			list = mapper.findAllWithCondition(map);
			map.put("condition", "contents");
			list2 = mapper.findAllWithCondition(map);
			for (int i = 0; i < list2.size(); i++) {
				list.add(list2.get(i));
			}
		} else {
			map.put("condition", condition);
			map.put("keyword", keyword);
			list = mapper.findAllWithCondition(map);
		}
		close();
		return list;
	}

	@RequestMapping("/board/searchIdx")
	public BoardDto searchIdx(@RequestParam int index) {
		connect();
		BoardDto obj = mapper.findOneByIdx(index);
		close();
		return obj;
	}

	@RequestMapping("/board/view")
	public void view(@RequestParam int index) {
		connect();
		mapper.updateViewCount(index);
		commit();
		close();
	}

	@RequestMapping("/board/update")
	public void update(@RequestParam String title, @RequestParam String contents, @RequestParam int index) {
		connect();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("contents", contents);
		map.put("idx", index);
		mapper.modifyBoard(map);
		commit();
		close();
	}

	@RequestMapping("/board/remove")
	public void remove(@RequestParam int index) {
		connect();
		mapper.removeBoard(index);
		commit();
		close();
	}

	@RequestMapping("/board/removeAll")
	public void removeAll() {
		connect();
		mapper.removeAll();
		commit();
		close();
	}

}
