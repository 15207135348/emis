package abc.eims.dao;

import abc.eims.entity.Items;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/5 11:18
 */
@Repository
public interface ItemsMapper {

    List<Items> findAll();

    Items findById(Integer id);

    int insert(Items recode);

    int update(Items recode);

    void deleteById(Integer id);

}
