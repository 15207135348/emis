package abc.eims.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author wangzhe
 * @date 2020/8/9 15:15
 */
@Repository
public interface UserInfoMapper {

    int changePwd(@Param("account") String account,
                  @Param("oldPwd") String oldPwd,
                  @Param("newPwd") String newPwd);

    int changeInfo(@Param("eId") Integer eId,
                   @Param("name") String name,
                   @Param("birthday") Date birthday,
                   @Param("sex") Integer sex,
                   @Param("phone") String phone,
                   @Param("email") String email);

}
