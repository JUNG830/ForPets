package ForPets.Mapper;

import ForPets.DTO.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberDTO findById(String id);
}
