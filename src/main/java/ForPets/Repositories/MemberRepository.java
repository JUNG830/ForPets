package ForPets.Repositories;

import ForPets.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, BigDecimal> {
  List<MemberEntity> findByIdAndPassword(String id, String pwd);

  Optional<MemberEntity> findById(String id);

}
