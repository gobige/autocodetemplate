package com.example.autocodetemplate.domain.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="acxw_supplier")
public class SupplierMongo  {
    public SupplierMongo(){}

    public SupplierMongo(Integer supplierId, String nickName) {
        this.setSupplierId(supplierId);
        this.setNickName(nickName);
    }
    @Id
    public String id;
    /**
     * id
     */
    public Integer supplierId;
    /**
     * 头像
     */
    public String avatar;
    /**
     * 昵称
     */
    public String nickName;
    /**
     * 联系方式
     */
    public String mobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}


