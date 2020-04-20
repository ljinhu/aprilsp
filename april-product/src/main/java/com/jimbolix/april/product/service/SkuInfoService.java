package com.jimbolix.april.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jimbolix.april.common.utils.PageUtils;
import com.jimbolix.april.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuInfo(SkuInfoEntity skuInfoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);


}

