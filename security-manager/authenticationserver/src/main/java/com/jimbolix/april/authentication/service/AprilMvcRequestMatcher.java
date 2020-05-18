package com.jimbolix.april.authentication.service;

import com.google.common.base.Objects;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * @Description
 * @ClassName AprilMvcRequestMatcher
 * @Author liruihui
 * @date 2020.05.17 09:38
 */
public class AprilMvcRequestMatcher extends MvcRequestMatcher {

    /**
     * 对应于pattern
     */
    private String pattern;
    private String method;

    public AprilMvcRequestMatcher(HandlerMappingIntrospector introspector,String method, String pattern) {
        super(introspector, pattern);
        this.setMethod(HttpMethod.resolve(method));
        this.pattern = pattern;
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AprilMvcRequestMatcher that = (AprilMvcRequestMatcher) o;
        return Objects.equal(pattern, that.pattern) &&
                Objects.equal(method, that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pattern, method);
    }
}
