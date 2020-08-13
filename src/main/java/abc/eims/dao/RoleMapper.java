package abc.eims.dao;

import abc.eims.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/9 9:14
 */
@Repository
public interface RoleMapper {

    public List<Role> findAll();

    Role findById(@Param("rId") Integer rId);

    int insert(Role recode);

    int update(Role recode);

    void deleteById(@Param("rId") Integer rId);


}
