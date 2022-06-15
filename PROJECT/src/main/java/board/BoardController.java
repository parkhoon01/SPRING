package com.pcwk.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.pcwk.cmn.MessageVO;
import com.pcwk.cmn.SearchVO;
import com.pcwk.cmn.StringUtil;

/**
 * Servlet implementation class BoardController
 */
@WebServlet(description = "게시판 컨트롤러", urlPatterns = { "/board/board.do" })
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    final Logger LOG = Logger.getLogger(this.getClass());
    BoardDao  boardDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        boardDao = new BoardDao();
    }

	/**
	 * VIEW work_div=doSave -> 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		work_div	doSave		
//		            doRetrieve		
//		            doSelectOne		
//		            doUpdate		
//		            doDelete		
		//request에 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		//String workDiv = request.getParameter("work_div")==null?"":request.getParameter("work_div");
		String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
		LOG.debug("=====================================");
		LOG.debug("=workDiv="+workDiv);
		LOG.debug("=====================================");
		
		//DAO분기
		switch(workDiv) {
		case "doUpdate":
			doUpdate(request,response);
			break;				
		case "doDelete":
			doDelete(request,response);
			break;		
		case "doSelectOne":
			doSelectOne(request,response);
			break;
			
		case "moveToReg":
			moveToReg(request,response);
			break;
			
		case "doRetrieve":
			doRetrieve(request,response);
			break;
		
		case "doSave": //등록: http://localhost:8081/studyhtml5/board/board.do?work_div=doSave
			doSave(request,response);
			break;
		}
		
	}
	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("========================================");
		LOG.debug("=doUpdate()=");
		LOG.debug("========================================");
		BoardVO param =new BoardVO();
		//param read
		String seq = StringUtil.nvl(request.getParameter("seq"),"-1");
		String title = StringUtil.nvl(request.getParameter("title"),"");
		String modId = StringUtil.nvl(request.getParameter("modId"),"");
		String contents = StringUtil.nvl(request.getParameter("contents"),"");
		LOG.debug("seq"+seq);
		LOG.debug("title"+title);
		LOG.debug("modId"+modId);
		LOG.debug("contents"+contents);
		//param to BoardVO		
		param.setSeq(Integer.parseInt(seq));
		param.setTitle(title);
		param.setModId(modId);
		param.setContents(contents);
		LOG.debug("param:"+param);
		LOG.debug("========================================");

		//BoardDao 호출
		int flag = this.boardDao.doUpdate(param);
		//return 받은 int를 MessageVO 변환
		MessageVO msgVO=new MessageVO();
		String msg = "";
		
		if(1==flag) {
			msg = "수정 되었습니다.";
		}else {
			msg = "수정 실패.";
		}
		
		msgVO.setMessageId(String.valueOf(flag));
		msgVO.setMsgContents(msg);
		
		//Object to json String
		String jsonString = new Gson().toJson(msgVO);
		LOG.debug("jsonString: "+jsonString);
		
		response.setContentType(StringUtil.CONTENT_UTF_8);
		PrintWriter out= response.getWriter();
		out.print(jsonString);
	}
	
	
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//super.doDelete(request, response);
		LOG.debug("========================================");
		LOG.debug("=doDelete()=");
		LOG.debug("========================================");		
		BoardVO param=new BoardVO();
		String seq = StringUtil.nvl(request.getParameter("seq"),"-1");
		LOG.debug("-seq-"+seq);
		//param read  
		
		//param to BoardVO
		param.setSeq(Integer.parseInt(seq));
		LOG.debug("-param-"+param);
		
		//BoardDAO 호출: BoardVO
		int flag = this.boardDao.doDelete(param);
		MessageVO  msgVO=new MessageVO();
		
		String msg = "";
		if(1==flag) {
			msg = "삭제 되었습니다.";
		}else {
			msg = "삭제 실패!";
		}
		msgVO.setMessageId(String.valueOf(flag));
		msgVO.setMsgContents(msg);
		
		//MessageVO => json
		String jsonString = new Gson().toJson(msgVO);
		LOG.debug("-jsonString-"+jsonString);
		
	    response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out= response.getWriter();
	    out.print(jsonString);
	}
	
	
	protected void doSelectOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("========================================");
		LOG.debug("=doSelectOne()=");
		LOG.debug("========================================");	
		BoardVO  param =new BoardVO();
		
		//param read
		String seq =StringUtil.nvl(request.getParameter("seq"), "-1");
		LOG.debug("----------------------------------------");
		LOG.debug("-seq-"+seq);
		LOG.debug("----------------------------------------");
		
		//param to BoardVO
		 param.setSeq(Integer.parseInt(seq));
		 LOG.debug("-param-"+param);
		
		 //BoardDAO 호출: BoardVO
		 BoardVO outVO=this.boardDao.doSelectOne(param);
		 LOG.debug("-outVO-"+outVO);
		 //조회 카운트 증가         && 최초등록한 사람 == 수정한 사람이 
		 //login하고 session으로 처리
		 //if(null != outVO && !param.getModId().equals(outVO.getRegId())) {
		 
		 if(null != outVO) {
			 int readCnt = boardDao.updateReadCnt(param);
			 LOG.debug("-readCnt-"+readCnt);
		 }
		 
		 
		 
		 
		//BoardDAO 처리 return받아 화면으로 전송
		 request.setAttribute("vo", outVO);
		 RequestDispatcher dispacher = request.getRequestDispatcher("/board/board_mod.jsp");
		 dispacher.forward(request, response);
		 
		//board_mod.jsp
		
	}
	
	
	
	protected void moveToReg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("========================================");
		LOG.debug("=moveToReg()=");
		LOG.debug("========================================");	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/board/board_reg.jsp");////contextPath생략
		dispatcher.forward(request, response);
	}
	
	protected void doRetrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("========================================");
		LOG.debug("=doRetrieve()=");
		LOG.debug("========================================");
		SearchVO  param=new SearchVO();
		//param read
		/*
		 * searchDiv="" searchWord="" pageSize=10 pageNum=1
		 */
		String searchDiv = StringUtil.nvl(request.getParameter("searchDiv"),"");
		String searchWord= StringUtil.nvl(request.getParameter("searchWord"),"");
		String pageSize  = StringUtil.nvl(request.getParameter("pageSize"),"10");
		String pageNum   = StringUtil.nvl(request.getParameter("pageNum"),"1");
		LOG.debug("------------------------------");
		LOG.debug("searchDiv:"+searchDiv);
		LOG.debug("searchWord:"+searchWord);
		LOG.debug("pageSize:"+pageSize);
		LOG.debug("pageNum:"+pageNum);
		LOG.debug("------------------------------");        
		//param to SeachVO
		param.setSearchDiv(searchDiv);
		param.setSearchWord(searchWord);
		param.setPageSize(Integer.parseInt(pageSize));
		param.setPageNum(Integer.parseInt(pageNum));
		LOG.debug("param:"+param.toString());
		//BoardDAO 호출: 목록
		List<BoardVO> list= this.boardDao.doRetrieve(param);
		
		//BoardDAO 호출 : 총글수
		int totalCnt = this.boardDao.totalCount(param);
		
		
		//BoardDAO 처리 return받아 화면으로 전송
		if(list.size()>0) {
			for(BoardVO vo :list) {
				LOG.debug("vo:"+vo);
			}
		}
		  
		//request에 list을 담아 전송
		request.setAttribute("list", list);
		
		//총글수
		request.setAttribute("totalCnt", totalCnt);
		
		//param   
		request.setAttribute("seachVO", param);
		
		//받을 url: /board/board_list.jsp
		RequestDispatcher  dispatcher = request.getRequestDispatcher("/board/board_list.jsp");//contextPath생략
		//data전송 
		dispatcher.forward(request, response);
		
		
	}
	
	protected void doSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("=====================================");
		LOG.debug("=doSave()=");
		LOG.debug("=====================================");	
		BoardVO paramVO =new BoardVO();
		//paramSet
		//seq,title,contents,reg_id,mod_id
		
		
		
		String title = StringUtil.nvl(request.getParameter("title"),"");
		String seq   = StringUtil.nvl(request.getParameter("seq"),"0");
		String regId = StringUtil.nvl(request.getParameter("reg_id"),"");
		String contents = StringUtil.nvl(request.getParameter("contents"),"");
		LOG.debug("=title="+title);
		
		String modId = regId;//최초 등록시 등록자id와 수정자id는 동일
		
		paramVO.setSeq(Integer.parseInt(seq));
		paramVO.setTitle(title);
		paramVO.setRegId(regId);
		paramVO.setModId(modId); 
		paramVO.setContents(contents);
		
		LOG.debug("=paramVO="+paramVO.toString());
		
		//DAO 호출
		int flag = this.boardDao.doSave(paramVO);
		
		LOG.debug("=flag="+flag);
		
		//VIEW전송
		//flag: 1성공, 0실패
		MessageVO msgVO=new MessageVO();
		msgVO.setMessageId(String.valueOf(flag));
		
		String message = "";
		if(1==flag) {
			message = "등록 성공!";
		}else {
			message = "등록 실패!";
		}
		
		msgVO.setMsgContents(message);
		
		Gson  gson=new Gson();
		String jsonString = gson.toJson(msgVO);
		
		LOG.debug("===========================");
		LOG.debug("=jsonString="+jsonString);
		LOG.debug("===========================");
		
		//VIEW전송
		//response encoding
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
		
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
