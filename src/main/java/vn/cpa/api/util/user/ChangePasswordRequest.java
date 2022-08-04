package vn.cpa.api.util.user;

import vn.cpa.api.entity.Account;
import vn.cpa.api.entity.SysUser;

public class ChangePasswordRequest {
    private Account account;
    private String password;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
