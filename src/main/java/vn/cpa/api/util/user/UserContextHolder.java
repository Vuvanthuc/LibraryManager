package vn.cpa.api.util.user;

import org.springframework.security.core.context.SecurityContextHolder;
import vn.cpa.api.entity.Account;
import vn.cpa.api.entity.SysUser;
import vn.cpa.api.service.authentication.BundleUserDetailsService;

public class UserContextHolder {

    private UserContextHolder() {
    }

    public static Account getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BundleUserDetailsService.BundleUserDetails userDetails = (BundleUserDetailsService.BundleUserDetails) principal;
        return userDetails.getAccount();
    }
}
