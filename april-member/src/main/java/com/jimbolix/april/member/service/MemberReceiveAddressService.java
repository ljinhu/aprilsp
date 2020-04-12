package com.jimbolix.april.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jimbolix.april.common.utils.PageUtils;
import com.jimbolix.april.member.entity.MemberReceiveAddressEntity;

import java.util.Map;

/**
 * 会员收货地址
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-08 09:47:05
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

