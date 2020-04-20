package com.jimbolix.april.product.dao;

import com.jimbolix.april.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:48
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {

}
