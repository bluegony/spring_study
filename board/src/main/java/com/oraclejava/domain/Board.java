package com.oraclejava.domain;

import java.util.Date;

public class Board {		// 이름은 Board지만 실제로는 글(post article)이다
	private int boardId;
	private String title;
	private int visited;
	private int recom;
	private String userip;
	private String htmlyn;
	private Date insertdate;
	private String content;
	private String userid;
	
	public Date getInsertDate() {
		return insertdate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertdate = insertDate;
	}
	private Boolean newBoard;
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getVisited() {
		return visited;
	}
	public void setVisited(int visited) {
		this.visited = visited;
	}
	public int getRecom() {
		return recom;
	}
	public void setRecom(int recom) {
		this.recom = recom;
	}
	public String getUserip() {
		return userip;
	}
	public void setUserip(String userip) {
		this.userip = userip;
	}
	public String getHtmlyn() {
		return htmlyn;
	}
	public void setHtmlyn(String htmlyn) {
		this.htmlyn = htmlyn;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Boolean getNewBoard() {
		return newBoard;
	}
	public void setNewBoard(Boolean newBoard) {
		this.newBoard = newBoard;
	}
}
