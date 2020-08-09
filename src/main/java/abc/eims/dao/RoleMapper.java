package abc.eims.dao;

import abc.eims.entity.Items;
import abc.eims.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/9 9:14
 */
@Repository
public interface RoleMapper {

    public List<Role> findAll();

    Role findById(Integer id);

    int insert(Role recode);

    int update(Role recode);

    void deleteById(Integer id);


}
