package com.springboot.tourism.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author oneda
 * @version 菜鸟
 */

@Getter
public enum Status {

    Disabled(0, "禁用"),
    Normal(1, "正常");

    @EnumValue
    private Integer code;
    private String msg;

    Status(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
