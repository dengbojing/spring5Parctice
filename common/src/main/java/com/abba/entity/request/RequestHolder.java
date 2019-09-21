package com.abba.entity.request;

import com.abba.entity.Subject;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dengbojing
 */
public class RequestHolder {

    /**
     * 获取请求信息
     *
     * @return RequestInfo
     */
    public static RequestInfo info() {
        RequestInfo info = new RequestInfo();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null != requestAttributes) {
            val request = (HttpServletRequest) (requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST));
            if (null != request) {
                // 用户
                RequestUser user = new RequestUser();
                user.setUserId(request.getHeader("X-UserId"));
                user.setCompanyId(request.getHeader("X-OrganizationId"));
                user.setCompanyType(request.getHeader("X-OrganizationType"));
                info.setUser(user);
                // 其他
                info.setFrom(request.getHeader("X-PreCaller"));
                info.setHost(request.getRemoteHost());
                info.setPath(request.getServletPath());
                info.setMethod(request.getMethod());
            }
        }
        return info;
    }

    /**
     * 获取请求用户信息
     *
     * @return RequestUser
     */
    public static RequestUser user() {
        return info().getUser();
    }

    /**
     * 获取请求用户ID
     *
     * @return String
     */
    public static String userId() {
        return user().getUserId();
    }

    /**
     * 获取请求用户组织ID
     *
     * @return String
     */
    public static String companyId() {
        return user().getCompanyId();
    }

    /**
     * 获取请求用户组织类型
     *
     * @return String
     */
    public static Subject.CompanyType companyType() {
        String type = user().getCompanyType();
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        return Subject.CompanyType.valueOf(type);
    }

    /**
     * 获取请求用户部门ID
     *
     * @return String
     */
    public static String departmentId() {
        return user().getDepartmentId();
    }

    @Getter
    @Setter
    public static class RequestInfo {
        private RequestUser user = new RequestUser();
        private String from;
        private String path;
        private String method;
        private String host;
    }

    @Getter
    @Setter
    public static class RequestUser {
        private String userId;
        private String companyId;
        private String companyType;
        private String departmentId;
    }
}
