package com.jimbolix.april.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jimbolix.april.common.utils.PageUtils;
import com.jimbolix.april.member.entity.MemberCollectSpuEntity;

import java.util.Map;

/**
 * 会员收藏的商品
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-08 09:47:05
 */
public interface MemberCollectSpuService extends IService<MemberCollectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

