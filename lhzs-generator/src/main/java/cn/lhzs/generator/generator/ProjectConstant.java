package cn.lhzs.generator.generator;

/**
 * 项目常量
 */
public interface ProjectConstant {
    String BASE_PACKAGE = "cn.lhzs";//项目基础包名称，根据自己公司的项目修改

    String MODEL_PACKAGE = BASE_PACKAGE + ".data.bean";//Model所在包
    String MAPPER_PACKAGE = BASE_PACKAGE + ".data.dao";//Mapper所在包
    String SERVICE_PACKAGE = BASE_PACKAGE + ".service.intf";//Service所在包
    String SERVICE_IMPL_PACKAGE = BASE_PACKAGE + ".service.impl";//ServiceImpl所在包
    String CONTROLLER_PACKAGE = BASE_PACKAGE + ".web.controller";//Controller所在包

    String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".data.base.Mapper";//Mapper插件基础接口的完全限定名
    String MAPPER_PLUGIN = BASE_PACKAGE + ".generator.generator.MapperPlugin";//Mapper插件
}
