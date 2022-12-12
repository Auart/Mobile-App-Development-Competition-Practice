package edu.eschina.mall.model;

/**
 * 所有模型的父类
 */
public class Common extends BaseId {
    /**
     * 创建时间
     */

    private String createTime;


    /**
     * 更新时间
     */


    private String updateTime;


    /**
     * 版本
     */

    private String version;

    /**
     * 权限
     */
    private  boolean valid;

    public Common() {
    }

    public Common(String createTime, String updateTime, String version, boolean valid) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.version = version;
        this.valid = valid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
