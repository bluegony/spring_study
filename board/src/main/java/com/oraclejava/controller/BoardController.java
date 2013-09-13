package com.oraclejava.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.dao.BoardDao;
import com.oraclejava.domain.Board;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardDao boardDao;

	@ModelAttribute
	public Board createInitCommand(Integer boardId)	{

		Board board = null;
		if(boardId !=null){
			board = boardDao.view(boardId);
			board.setNewBoard(false);
		} else {
			board = new Board();
			board.setNewBoard(true);
		}		
		return board;
	}
	
	@RequestMapping(value={"/boardForm","/editBoardForm"})
	public ModelAndView setUpForm(@RequestParam(value="boardId",required=false)Integer boardId){

		Board board = createInitCommand(boardId);
		
		String[] yn = {"y","n"};

		ModelAndView mav = new ModelAndView("board/boardForm");
		mav.addObject("boardForm",board);
		mav.addObject("htmlyns",yn);
		return mav;
	}

	@RequestMapping(value="editBoardForm",method=RequestMethod.POST)
	public String update(@ModelAttribute("boardForm") Board board,HttpServletRequest request){
		board.setInsertDate(new Date());
		//board.setRecom(0);
		//board.setUserid("aaa");
		board.setUserip(request.getRemoteAddr());;
		//board.setVisited(0);

		//boardDao.insert(board);
		boardDao.update(board);
		return "redirect:/board/listBoard.html";
		//	ModelAndView mav = new ModelAndView("board/insertBoardSuccess");
		//	return mav;
	}
	
	@RequestMapping(value="insertBoardForm",method=RequestMethod.POST)
	public String insert(@ModelAttribute("boardForm") Board board,HttpServletRequest request){
		board.setInsertDate(new Date());
		board.setRecom(0);
		board.setUserid("aaa");
		board.setUserip(request.getRemoteAddr());;
		board.setVisited(0);

		boardDao.insert(board);
		return "redirect:/board/listBoard.html";
		//	ModelAndView mav = new ModelAndView("board/insertBoardSuccess");
		//	return mav;
	}
	
	@RequestMapping(value="viewBoard",method=RequestMethod.GET)
	public ModelAndView view(@RequestParam("boardId") Integer boardId){
		Board board = boardDao.view(boardId);
		boardDao.increaseViewCount(board);
		ModelAndView mav = new ModelAndView("board/viewBoard");
		mav.addObject("board", board);
		return mav;
	}
	@RequestMapping(value="recomBoard",method=RequestMethod.GET)
	public ModelAndView recom(@RequestParam("boardId") Integer boardId){
		Board board = boardDao.view(boardId);
		boardDao.increaseRecomCount(board);
		ModelAndView mav = new ModelAndView("board/viewBoard");
		mav.addObject("board", board);
		return mav;
	}
	@RequestMapping(value={"listBoard","searchBoard"})
	public ModelAndView list(@RequestParam(required=false) Integer page,
			@RequestParam(required=false) String searchKey,
			@RequestParam(required=false) String searchVal){
		if(page==null) page=1;
		
		List<Board> list;

		if(searchKey != null){
			if("subject".equals(searchKey)){
				Board searchParam = new Board();
				searchParam.setTitle(searchVal);
				list = boardDao.list(searchParam);
				System.out.println(searchVal);
				
			} else {	// searchKey == writer

				Board searchParam = new Board();
				searchParam.setUserid(searchVal);
				list = boardDao.list(searchParam);
			}
			
		} else{
			 list = boardDao.list();
		}
		
		PagedListHolder holder = new PagedListHolder(list);
		holder.setPageSize(2);
		holder.setPage(page-1);
		
		//StringBuffer sb = makePageLinkSB(holder,page);
		//makePageLink(holder,page);

		long s_time = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		// string 연산을 할때  stringbuffer가 string보다 압도적으로 빠르다. 100배 이상.append()함수 사용
		//100회 이상 반복작업이 있으면
		
		for(int i=0; i<holder.getPageCount(); i++)
		{	
			if(i!=page-1){
				sb.append("<a href='?page=")
				.append(i+1);
				if(searchVal!=null&&searchKey!=null){
				sb.append("&searchKey=")
				.append(searchKey)
				.append("&searchVal=")
				.append(searchVal);
				}
				sb.append("'>");
			}
			sb.append(i+1);
			if(i!=page-1){
				sb.append("</a>");
			}
			if(i!=holder.getPageCount()-1)
				sb.append("|");
		}
		long e_time = System.currentTimeMillis();
		System.out.println((e_time - s_time) +"mil second");
		
		ModelAndView mav = new ModelAndView("board/listBoard");
		mav.addObject("list",holder);
		mav.addObject("pageLink",sb.toString());
		return mav;
	}
	
	public StringBuffer makePageLinkSB(PagedListHolder holder, Integer page)
	{
		long s_time = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		// string 연산을 할때  stringbuffer가 string보다 압도적으로 빠르다. 100배 이상.append()함수 사용
		//100회 이상 반복작업이 있으면
		
		for(int i=0; i<holder.getPageCount(); i++)
		{	
			if(i!=page-1){
				sb.append("<a href='?page=")
				.append(i+1)
//				.append("&searchKey=")
//				.append(searchKey)
//				.append("&searchVal=")
//				.append(searchVal)
				.append("'>");
			}
			sb.append(i+1);
			if(i!=page-1){
				sb.append("</a>");
			}
			if(i!=holder.getPageCount()-1)
				sb.append("|");
		}
		long e_time = System.currentTimeMillis();
		System.out.println((e_time - s_time) +"mil second");
		return sb;
	}
	public String makePageLink(PagedListHolder holder, Integer page)
	{
		long s_time = System.currentTimeMillis();
		String st = new String();
		for(int i=0; i<holder.getPageCount(); i++)
		{	
			if(i!=page-1){
				st+="<a href='?page="+(i+1)+"'>";		
			}
			st+=i+1;
			if(i!=page-1){
				st+="</a>";
			}
			if(i!=holder.getPageCount()-1)
				st+="|";
		}
		long e_time = System.currentTimeMillis();
		System.out.println((e_time - s_time)+"mil second2");
		return st;
	}


}
