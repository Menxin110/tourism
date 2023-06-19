package com.springboot.tourism.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author oneda
 * @version 菜鸟
 */

@TableName("t_place")
@Data
public class Place {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    private String name;
    @TableField("picpath")
    private String picPath;
    @TableField("hottime")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date hotTime;
    @TableField("hotticket")
    private Double hotTicket;
    @TableField("dimticket")
    private Double dimTicket;
    // 景点描述
    @TableField("placedes")
    private String placeDes;
    // 省份id
    @TableField("provinceid")
    private String provinceId;

    // 乐观锁
    @Version
    private Integer version;
}
