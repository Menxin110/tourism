package com.springboot.tourism.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @author oneda
 * @version 菜鸟
 */

@TableName("t_province")
@Data
public class Province {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String tags;
    @TableField("placecounts")
    private Integer placeCounts;

    @TableLogic
    private Integer isDeleted;
}
