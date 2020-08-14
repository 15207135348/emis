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

    /**
     * 修改密码
     *
     * @param eId    员工编号
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return 是否修改成功
     */
    int changePwd(@Param("eId") Integer eId,
                  @Param("oldPwd") String oldPwd,
                  @Param("newPwd") String newPwd);

    /**
     * 修改个人信息
     *
     * @param eId      员工Id
     * @param name     姓名
     * @param birthday 生日
     * @param sex      性别
     * @param phone    电话
     * @param email    e-mail
     * @return 是否修改成功
     */
    int changeInfo(@Param("eId") Integer eId,
                   @Param("name") String name,
                   @Param("birthday") Date birthday,
                   @Param("sex") Integer sex,
                   @Param("phone") String phone,
                   @Param("email") String email);

}
