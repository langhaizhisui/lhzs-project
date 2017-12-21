package cn.lhzs.common.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IBA-EDV on 2017/8/9.
 */
public enum SaleOrderExcelEnum {
    AGENT_SHOP_EXCEL((byte) 1,
            "fileName",
            new String[]{"shopName", "id", "bossSkuId", "brandName", "firstCata", "barcode", "skuCode", "prodName", "saleTime", "saleTime", "qty", "salePirce", "totalPrice","refundQty"},
            new String[]{"店铺名称", "订单号", "商品Id", "品牌", "类型", "商品编码", "商品条形码", "商品名称", "下单时间", "支付时间", "数量", "单价", "金额(人民币)","退货数量"}),
    COMMON_SHOP_EXCEL((byte) 2,
            "fileName",
            new String[]{"shopName", "shopGuideName", "id", "bossSkuId", "brandName", "firstCata", "barcode", "skuCode", "prodName", "saleTime", "saleTime", "qty", "salePirce", "totalPrice", "copeAmt","refundQty"},
            new String[]{"店铺名称", "导购员", "订单号", "商品Id", "品牌", "类型", "商品编码", "商品条形码", "商品名称", "下单时间", "支付时间", "数量", "单价", "销售价(人民币)", "成交价","退货数量"}),
    GUIDE_SALE_DETAIL((byte) 3,
            "收银员销售报表",
            new String[]{"userName", "prodSaleNum", "salePriceTotal", "preferentialPriceTotal", "transactionPriceTotal", "supplyPriceTotal", "profit", "wechatPayTotal", "alipayPayTotal", "crashPayTotal", "bankCardPayTotal"},
            new String[]{"收银员", "商品销售量", "销售价总额", "优惠总额", "成交价总额", "供货价总额", "利润", "微信", "支付宝", "现金", "银行卡"}),
    PROD_SALE_DETAILS_EXCEL((byte) 4,
            "商品销售报表",
            new String[]{"bossSkuId","barcode","prodName", "prodSaleNumTotal", "prodSalePriceTotal", "prodPreferentialPriceTotal", "prodTransactionPriceTotal", "prodO2oSupplyPriceTotal","prodProfitTotal"},
            new String[]{"商品SkuId", "商品条形码", "商品名称", "销售数量", "销售价总额", "优惠价总额", "成交价总额", "供货价总额", "利润"}),
    SALE_DAY_REPORT_EXCEL((byte) 5,
            "销售日报表",
            new String[]{"createTime", "saleProdQty", "salePrice", "preferential", "copeAmt", "supplyPrice", "profit", "wechatPay", "alipayPay", "cashPayment", "bankCard", "amountRefunded"},
            new String[]{"日期", "商品销售量", "销售价总额", "优惠总额", "成交价总额", "供货价总额", "利润", "微信", "支付宝", "现金", "银行卡", "退单金额"});

    private static final Map<Byte, SaleOrderExcelEnum> map = new HashMap<>();

    static {
        Arrays.stream(SaleOrderExcelEnum.values()).forEach(e -> map.put(e.getRoleId(),e));
    }

    private Byte roleId;
    private String fileName;
    private String[] fields;
    private String[] names;

    SaleOrderExcelEnum(Byte roleId, String fileName, String[] fields, String[] names) {
        this.roleId = roleId;
        this.fileName = fileName;
        this.fields = fields;
        this.names = names;
    }

    public static SaleOrderExcelEnum get(Byte b) {
        return map.get(b);
    }

    public Byte getRoleId() {
        return roleId;
    }

    public void setRoleId(Byte roleId) {
        this.roleId = roleId;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

//    public static Result generatorSaleOrderExcel(Byte type, List data, HttpServletResponse response) {
//        try {
//            ExcelHelper eh1 = XSSFExcelHelper.getInstance("");
//            response.setContentType("application/vnd.ms-excel;charset=utf-8");
//            response.setHeader("Content-Disposition", "attachment;filename=" + new String(SaleOrderExcelEnum.get(type).getFileName().getBytes("gb2312"), "ISO8859-1") + ".xlsx");
//            ServletOutputStream out = response.getOutputStream();
//            eh1.writeExcel(Prod4OrderVo.class, data, SaleOrderExcelEnum.get(type).getFields(), SaleOrderExcelEnum.get(type).getNames(), out);
//            return genSuccessResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return genBadReqResult(HttpCode.SALE_ORDER_DOWNLOAD_ERROR, "Excel下载失败");
//        }
//    }
}
