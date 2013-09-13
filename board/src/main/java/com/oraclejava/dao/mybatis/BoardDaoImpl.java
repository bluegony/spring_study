package com.oraclejava.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oraclejava.dao.BoardDao;
import com.oraclejava.domain.Board;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(Board board) {
		// TODO Auto-generated method stub
		session.insert("mybatis.sql.Board.createBoard",board);

	}

	@Override
	public List<Board> list() {
		// TODO Auto-generated method stub
		List<Board> list = session.selectList("mybatis.sql.Board.listBoard");
		return list;
	}

	@Override
	public List<Board> list(Board searchParam) {
		// TODO Auto-generated method stub
		List<Board> list = session.selectList("mybatis.sql.Board.listBoard",searchParam);
		System.out.println(searchParam.getTitle());
		return list;
	}

	@Override
	public Board view(int boardId) {
		// TODO Auto-generated method stub
		Board board = session.selectOne("mybatis.sql.Board.viewBoard",boardId);
		return board;
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		session.update("mybatis.sql.Board.updateBoard",board);
	}

	@Override
	public void increaseViewCount(Board board) {
		// TODO Auto-generated method stub

		session.update("mybatis.sql.Board.increaseViewCount",board);	
	}


	@Override
	public void increaseRecomCount(Board board) {
		// TODO Auto-generated method stub
		session.update("mybatis.sql.Board.increaseRecomCount",board);	
		
	}
}
