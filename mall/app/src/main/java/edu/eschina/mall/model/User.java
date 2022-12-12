package edu.eschina.mall.model;



public class User extends Common{
    private String mobile;
    private String nickName;
    private String headPic;

    public User() {

    }

    public User(long id,String createTime, String updateTime, String version, boolean valid, String mobile, String nickName, String headPic) {
        super(createTime, updateTime, version, valid);
        this.mobile = mobile;
        this.nickName = nickName;
        this.headPic = headPic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

}
