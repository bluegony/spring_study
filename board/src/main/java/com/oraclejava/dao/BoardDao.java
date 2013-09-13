package com.oraclejava.dao;

import java.util.List;

import com.oraclejava.domain.Board;

public interface BoardDao {
	public void insert(Board board);
	public List<Board> list();
	public List<Board> list(Board searchVal);
	public Board view(int boardId);
	public void update(Board board);
	void increaseViewCount(Board board);
	void increaseRecomCount(Board board);

}
