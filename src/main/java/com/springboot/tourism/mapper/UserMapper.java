package com.springboot.tourism.mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.tourism.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author oneda
 * @version 菜鸟
 */

@Repository
public interface UserMapper extends BaseMapper<User> {

    int insertSelective(User user);
}
