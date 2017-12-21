package ${basePackage}.service.impl;

import ${basePackage}.data.dao.${modelNameUpperCamel}Mapper;
import ${basePackage}.data.bean.${modelNameUpperCamel};
import ${basePackage}.service.intf.${modelNameUpperCamel}Service;
import ${basePackage}.base.AbstractBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheConfig;

import javax.annotation.Resource;


/**
 * Created by ${author} on ${date}.
 */
@CacheConfig(cacheNames = "${modelNameUpperCamel}")
@Service
@Transactional
public class ${modelNameUpperCamel}ServiceImpl extends AbstractBaseService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

}
