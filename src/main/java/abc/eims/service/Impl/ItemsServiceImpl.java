package abc.eims.service.Impl;

import abc.eims.dao.ItemsMapper;
import abc.eims.entity.Items;
import abc.eims.service.IItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/5 11:27
 */

@Service
public class ItemsServiceImpl implements IItemsService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<Items> findAll() {
        return itemsMapper.findAll();
    }

    @Override
    public Items findById(Integer id) {
        return itemsMapper.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        itemsMapper.deleteById(id);
    }

    @Override
    public void insertOrUpdate(Items items) {
        itemsMapper.update(items);
    }

    @Override
    public void insert(Items items) {
        itemsMapper.insert(items);
    }

}
