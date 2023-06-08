package com.springboot.tourism;

import com.springboot.tourism.mapper.ProvinceMapper;
import com.springboot.tourism.mapper.UserMapper;
import com.springboot.tourism.pojo.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author oneda
 * @version 菜鸟
 */

@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){

        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }

        System.out.println(userMapper.selectById(1));
    }

    /**
     * 在插入数据的时候，默认调用了雪花算法生成id值
     *
     */
    @Test
    public void insertTest(){

        User user = new User();
        user.setUsername("king5");
        user.setPassword("king5");
        user.setEmail("king5@qq.com");
        int insert = userMapper.insert(user);
        System.out.println("insert：" + insert);
    }

    @Test
    public void deleteByIdTest(){

        int i = userMapper.deleteById(8);
        System.out.println("i" + i);
    }


    @Autowired
    private ProvinceMapper provinceMapper;
    @Test
    public void selectByIdProvinceTest(){

        System.out.println(provinceMapper.selectById(3));
    }
}
