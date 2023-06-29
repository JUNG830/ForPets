package ForPets.Repositories;

import ForPets.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, BigDecimal> {
  List<MemberEntity> findByIdAndPassword(String id, String pwd);
}
