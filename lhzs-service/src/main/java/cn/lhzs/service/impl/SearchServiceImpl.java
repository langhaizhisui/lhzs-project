package cn.lhzs.service.impl;

import cn.lhzs.common.vo.ProductSearchCondition;
import cn.lhzs.common.vo.ShopSearchCondition;
import cn.lhzs.data.bean.Product;
import cn.lhzs.service.intf.SearchService;
import cn.lhzs.common.util.JestUtil;
import cn.lhzs.common.util.StringUtil;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZHX on 2017/11/23.
 */
@Service
public class SearchServiceImpl implements SearchService {

    Logger logger = Logger.getLogger(SearchServiceImpl.class);

    private static JestClient jestClient = JestUtil.getJestClient();

    @Override
    public <T> void builderSearchIndex(List<T> list, String index, String type) {
        try {
            DeleteIndex deleteIndex = new DeleteIndex(index);
            jestClient.equals(deleteIndex);
            CreateIndex createIndex = new CreateIndex(index);
            jestClient.execute(createIndex);
            Bulk bulk = new Bulk(index, type);
            list.forEach(item -> {
                bulk.addIndex(new Index.Builder(item).build());
            });
            jestClient.execute(bulk);
        } catch (Exception e) {
            logger.error("SearchServiceImpl builderSearchIndex() exception:" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> searchProductList(ProductSearchCondition condition) {
        try {
            int from = (condition.getPage() - 1) * condition.getSize();
            String param = "{\"query\":{\"filtered\":{\"query\":{\"multi_match\":{\"query\":\"" + condition.getName() + "\",\"type\":\"best_fields\",\"minimum_should_match\":\"80%\",\"fields\":[\"name\"]}}}},\"highlight\":{\"pre_tags\":[\"<font color='red'>\"],\"post_tags\":[\"</font>\"],\"fields\":{\"name\":{}}},\"sort\":[],\"from\":" + from + ",\"size\":" + condition.getSize() + "}";
            JestResult result = getJestResult(param, "products", "prod");
            return result.getSourceAsObjectList(Product.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> searchShopList(ShopSearchCondition condition) {
        try {
            int from = (condition.getPage() - 1) * condition.getSize();
            String param = "{\"query\":{\"filtered\":{\"query\":{\"multi_match\":{\"query\":\"" + condition.getShopName() + "\",\"type\":\"best_fields\",\"minimum_should_match\":\"80%\",\"fields\":[\"webShop\"]}}}},\"highlight\":{\"pre_tags\":[\"<font color='red'>\"],\"post_tags\":[\"</font>\"],\"fields\":{\"name\":{}}},\"sort\":[],\"from\":" + from + ",\"size\":" + condition.getSize() + "}";
            JestResult result = getJestResult(param, "shops", "shop");
            return result.getSourceAsObjectList(Product.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private JestResult getJestResult(String query, String index, String type) throws Exception {
        Search search = new Search(query);
        if (StringUtil.isNotEmpty(index)) {
            search.addIndex(index);
        }
        if (StringUtil.isNotEmpty(type)) {
            search.addType(type);
        }
        return jestClient.execute(search);
    }
}