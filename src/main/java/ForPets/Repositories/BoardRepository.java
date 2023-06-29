package ForPets.Repositories;

import ForPets.Entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, BigDecimal> {

}
