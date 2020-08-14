package abc.eims.dao;

import abc.eims.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wangzhe
 * @date 2020/8/9 9:14
 */
@Repository
public interface RoleMapper {

    /**
     * 通知rId查找对应权限
     *
     * @param rId 权限Id
     * @return 对应的权限
     */
    Role findById(@Param("rId") Integer rId);

    /**
     * 新增权限
     *
     * @param recode 权限
     */
    void insert(Role recode);

    /**
     * 更新权限信息
     *
     * @param recode 权限
     */
    void update(Role recode);

    /**
     * 删除权限信息
     *
     * @param rId 权限Id
     */
    void deleteById(@Param("rId") Integer rId);


}
