package com.jimbolix.april.authentication.feign;

import com.jimbolix.april.authentication.domain.SysMenuEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @ClassName ResourceProviderFallback
 * @Author liruihui
 * @date 2020.05.16 17:37
 */
public class ResourceProviderFallback implements ResourceProvider {
    public List<SysMenuEntity> allResources() {
        return new ArrayList<>();
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(String userAccount) {
        return new ArrayList<>();
    }
}
