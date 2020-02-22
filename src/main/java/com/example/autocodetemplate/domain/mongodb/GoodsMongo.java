package com.example.autocodetemplate.domain.mongodb;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@ToString
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


}
