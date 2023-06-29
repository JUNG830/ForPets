package ForPets.Controller;

import ForPets.DTO.BoardDTO;
import ForPets.Entity.BoardEntity;
import ForPets.Service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
@Controller
@Slf4j
public class BoardController {
  public BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  private BoardService boardService;
//  @RequestMapping("/testBoardList")
//  public ModelAndView testBoardList() {
//    ModelAndView mav = new ModelAndView();
//    List<BoardEntity> boardList = new ArrayList<BoardEntity>();
//    //boardList = boardService.getBoardList(board);
//    log.warn(boardList.toString());
//    mav.addObject("BoardList", boardList);
//
//    return mav; // jsp 파일 이름
//  }

  /**
   * 게시판 목록
   *
   *
   * @return
   */
  @RequestMapping("/getBoardList")
  public ModelAndView getBoardList() {
    ModelMapper modelMapper = new ModelMapper();
    ModelAndView mav = new ModelAndView();
    //List<BoardEntity> boardList = boardService.getBoardList();
    //BoardDTO boardDTO = modelMapper.map(boardList, BoardDTO.class);
    //log.warn(boardList.toString());
    mav.addObject("boardList", boardService.getBoardList());
    mav.setViewName("getBoardList");
    return mav;

  }
}
