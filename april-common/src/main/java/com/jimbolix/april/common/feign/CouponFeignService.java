package com.jimbolix.april.common.feign;


import com.jimbolix.april.common.constant.MicroServiceItems;
import com.jimbolix.april.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@FeignClient(value = MicroServiceItems.coupon)
public interface CouponFeignService {

    @RequestMapping("/coupon/coupon/member/list")
    R membercoupons();
}
