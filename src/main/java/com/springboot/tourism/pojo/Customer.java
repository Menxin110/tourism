package com.springboot.tourism.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.springboot.tourism.enums.Sex;
import com.springboot.tourism.enums.Status;
import lombok.Data;

/**
 * @author oneda
 * @version 菜鸟
 */

@TableName("t_customer")
@Data
public class Customer {

    @TableId(value = "id")
    private Long id;
    private String name;
    private String phone;
    // 性别 0：女，1：男
    private Sex sex;
    private String idNumber;
    private String avatar;
    // 状态 0:禁用，1:正常
    private Status status;
}
