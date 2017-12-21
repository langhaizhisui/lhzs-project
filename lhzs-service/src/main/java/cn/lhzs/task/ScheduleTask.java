package cn.lhzs.task;

import cn.lhzs.common.support.thread.JestThreadPool;
import cn.lhzs.service.intf.ProductService;
import cn.lhzs.service.intf.SearchService;
import cn.lhzs.service.intf.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleTask {

    @Autowired
    private JestThreadPool jestThreadPool;

    @Autowired
    private SearchService searchService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShopService shopService;

    @Scheduled(cron = "0 0 2 * * ? ")
    public void timerDeleteExpireProducts() {
        productService.timerDeleteTask(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    @Scheduled(cron = "0 0 2 * * ? ")
    public void timerUpdateJestIndex() {
        jestThreadPool.addTask(() -> searchService.builderSearchIndex(productService.getAllProduct(), "products", "prod"));
        jestThreadPool.addTask(() -> searchService.builderSearchIndex(shopService.getAllShop(), "shops", "shop"));
    }
}