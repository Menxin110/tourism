package com.springboot.tourism.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author oneda
 * @version 菜鸟
 */

@Getter
public enum Sex {

    MALE(0, "女"),
    FEMALE(1, "男");

    @EnumValue
    private Integer sex;
    private String sexName;

    Sex(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }

    @Override
    public String toString() {
        return "Sex{" +
                "sex=" + sex +
                ", sexName='" + sexName + '\'' +
                '}';
    }
}
