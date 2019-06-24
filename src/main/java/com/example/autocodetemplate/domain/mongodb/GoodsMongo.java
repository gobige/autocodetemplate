package com.example.autocodetemplate.domain.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="acxw_goods")
public class GoodsMongo {
    public GoodsMongo() {}

    /**
     * 商品id
     */
    @Id
    public Integer goodsId;
    /**
     * 商品id
     */
    @DBRef
    public SupplierMongo supplier;

    /**
     * 商品名称
     */
    public String goodsName;
    /**
     * 分类id
     */
    public Integer catId;
    /**
     * 品牌id
     */
    public Integer brandId;
    /**
     * 起批量
     */
    public Integer moq;
    /**
     * 商品详情
     */
    public String goodsDesc;

    /**
     * 商品缩略图
     */
    public String goodsThumb;

    public SupplierMongo getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierMongo supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getMoq() {
        return moq;
    }

    public void setMoq(Integer moq) {
        this.moq = moq;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsThumb() {
        return goodsThumb;
    }

    public void setGoodsThumb(String goodsThumb) {
        this.goodsThumb = goodsThumb;
    }
}
