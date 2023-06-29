package ForPets.Service;

import ForPets.Entity.BoardEntity;
import ForPets.Repositories.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
//final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@RequiredArgsConstructor
public class BoardService {
  private final BoardRepository boardRepository;

  /**
   * 글목록 조회
   *
   * @return
   */
    public List<BoardEntity> getBoardList() {
      List<BoardEntity> result = boardRepository.findAll();
      log.warn("게시판서비스" + result.toString());
      return result;
  }


}
