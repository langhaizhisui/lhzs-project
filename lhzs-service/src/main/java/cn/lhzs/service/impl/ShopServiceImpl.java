package cn.lhzs.service.impl;

import cn.lhzs.base.AbstractBaseService;
import cn.lhzs.common.constant.ShopEnum;
import cn.lhzs.common.util.StringUtil;
import cn.lhzs.common.vo.ShopSearchCondition;
import cn.lhzs.data.bean.Shop;
import cn.lhzs.data.dao.ShopMapper;
import cn.lhzs.service.intf.ShopService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

import static com.github.pagehelper.PageHelper.startPage;
import static java.util.stream.Collectors.toList;

/**
 * Created by ZHX on 2017/4/27.
 */
@Service
public class ShopServiceImpl extends AbstractBaseService<Shop> implements ShopService {

    Logger logger = Logger.getLogger(ShopServiceImpl.class);

    @Resource
    public ShopMapper shopMapper;

    @Override
    public List<Shop> getShopList(Shop shop) {
        return shopMapper.select(shop);
    }

    @Override
    public List<Shop> searchShop(ShopSearchCondition shopSearchCondition) {
        startPage(shopSearchCondition.getPage(), shopSearchCondition.getSize());
        List<Shop> shopList = findByCondition(getShopSearchExample(shopSearchCondition));
        shopList.forEach(shop -> {
            if(StringUtil.isNotEmpty(shop.getSite())){
                shop.setSite(ShopEnum.get(shop.getSite()).getName());
            }
            if(StringUtil.isNotEmpty(shop.getType())){
                shop.setType(ShopEnum.get(shop.getType()).getName());
            }
        });
        return shopList;
    }

    private Example getShopSearchExample(ShopSearchCondition shopSearchCondition) {
        Example example = new Example(Shop.class);
        Example.Criteria criteria = example.createCriteria();
        if (shopSearchCondition.getId() != null) {
            criteria.andEqualTo("id", shopSearchCondition.getId());
        }
        if (StringUtil.isNotEmpty(shopSearchCondition.getShopName())) {
            criteria.andEqualTo("webShop", shopSearchCondition.getShopName());
        }
        if (StringUtil.isNotEmpty(shopSearchCondition.getSite())) {
            criteria.andEqualTo("site", shopSearchCondition.getSite());
        }
        if (StringUtil.isNotEmpty(shopSearchCondition.getType())) {
            criteria.andEqualTo("type", shopSearchCondition.getType());
        }
        if (shopSearchCondition.getCreateTimeStart() != null && shopSearchCondition.getCreateTimeEnd() != null) {
            criteria.andGreaterThanOrEqualTo("createTime", shopSearchCondition.getCreateTimeStart())
                    .andLessThanOrEqualTo("createTime", shopSearchCondition.getCreateTimeEnd());
        }
        return example;
    }

    @Override
    public List<Shop> getShops(Shop shop) {
        return findByCondition(getShopsExample(shop));
    }

    private Example getShopsExample(Shop shop) {
        Example example = new Example(Shop.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(shop.getType())) {
            criteria.andEqualTo("type", shop.getType());
        }
        if (StringUtil.isNotEmpty(shop.getSite())) {
            criteria.andEqualTo("site", shop.getSite());
        }
        return example;
    }

    @Override
    public void deleteTable() {
        shopMapper.deleteTable();
    }

    @Override
    public List<Shop> getAllShop() {
        return shopMapper.selectAll();
    }

    @Override
    public Shop getShopByShopId(Long shopId) {
        return findById(shopId);
    }

    @Override
    public void addBatchShop(List<Shop> shops) {
//        shopMapper.batchInsert(shops);
    }

    @Override
    public void addShop(Shop shop) {
        save(shop);
    }

    @Override
    public void deleteShopByShopId(Long shopId) {
        deleteById(shopId);
    }

    @Override
    public void updateShop(Shop shop) {
        save(shop);
    }

}
