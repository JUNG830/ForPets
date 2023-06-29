package ForPets.Repositories;

import ForPets.Entity.BaseTimeEntity;
import ForPets.Entity.BoardEntity;
import ForPets.Enum.UsingRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class BoardRepositoryTest {

  @Autowired
  BoardRepository boardRepository;
  BaseTimeEntity baseTimeEntity;

  @Test
  @DisplayName("게시글 테스트")
  public void BoardTest() {
    // 임시로 게시물 10개를 만들자
    for (int i = 1; i < 11; i++) {
      BoardEntity board = new BoardEntity();
      board.setTitle("제목   " + i);
      board.setWriter("작성자 " + i);
      board.setContent("글내용  " + i);
      board.setUsingRole(UsingRole.USING);
      board.setCnt(0L);
      boardRepository.save(board);
      System.out.println(i);
    }

  }
}