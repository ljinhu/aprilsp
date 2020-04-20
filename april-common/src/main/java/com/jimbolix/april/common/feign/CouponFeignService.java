package com.jimbolix.april.common.feign;


import com.jimbolix.april.common.constant.MicroServiceItems;
import com.jimbolix.april.common.to.SkuReductionTo;
import com.jimbolix.april.common.to.SpuBoundTo;
import com.jimbolix.april.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@FeignClient(value = MicroServiceItems.coupon)
public interface CouponFeignService {

    @RequestMapping("/coupon/coupon/member/list")
    R membercoupons();

    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);


    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}
