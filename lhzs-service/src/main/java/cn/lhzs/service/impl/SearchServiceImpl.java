package cn.lhzs.service.impl;

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
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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
    public List<Product> searchProductList(String param) {
        try {
            JestResult result = getJestResult("name", param, "products", "prod");
            return result.getSourceAsObjectList(Product.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> searchShopList(String param) {
        try {
            JestResult result = getJestResult("webShop", param, "shops", "shop");
            return result.getSourceAsObjectList(Product.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private JestResult getJestResult(String field, String param, String index, String type) throws Exception {
        QueryBuilder queryBuilder = QueryBuilders.fieldQuery(field, param);
        Search search = new Search(Search.createQueryWithBuilder(queryBuilder.toString()));
        if (StringUtil.isNotEmpty(index)) {
            search.addIndex(index);
        }
        if (StringUtil.isNotEmpty(type)) {
            search.addType(type);
        }
        return jestClient.execute(search);
    }
}