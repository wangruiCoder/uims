package org.uims.datamaintain.user.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类，与数据库字段一一对应
 * @author kyrie
 */
public class AccountDto implements Serializable {

    private Integer accountId;
    private Integer accountFlag;
    private Integer userInfoId;

    private String accountName;
    private String password;
    private String passSalt;

    private Date createTime;
    private Date passEditTime;
    private Date lastLoginTime;

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getPassSalt() {
        return passSalt;
    }

    public void setPassSalt(String passSalt) {
        this.passSalt = passSalt;
    }

    public Date getPassEditTime() {
        return passEditTime;
    }

    public void setPassEditTime(Date passEditTime) {
        this.passEditTime = passEditTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAccountFlag() {
        return accountFlag;
    }

    public void setAccountFlag(Integer accountFlag) {
        this.accountFlag = accountFlag;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "accountId=" + accountId +
                ", accountFlag=" + accountFlag +
                ", userInfoId=" + userInfoId +
                ", accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                ", passSalt='" + passSalt + '\'' +
                ", createTime=" + createTime +
                ", passEditTime=" + passEditTime +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
