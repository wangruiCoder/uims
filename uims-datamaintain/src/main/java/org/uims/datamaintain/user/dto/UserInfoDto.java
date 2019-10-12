package org.uims.datamaintain.user.dto;

import java.util.Date;

/**
 * 用户信息实体类
 * @author kyrie
 * @since 1.0
 */
public class UserInfoDto {
    private Integer userId;
    private String mobileNum;
    private String mail;

    private Date createTime;
    private Date updateTime;

    private String fullName;
    private Integer sex;
    private Integer age;
    private String idNum;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "userId=" + userId +
                ", mobileNum='" + mobileNum + '\'' +
                ", mail='" + mail + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", fullName='" + fullName + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", idNum='" + idNum + '\'' +
                '}';
    }
}
