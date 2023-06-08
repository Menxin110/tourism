package com.springboot.tourism;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.springboot.tourism.pojo.Province;
import com.springboot.tourism.pojo.User;
import com.springboot.tourism.service.ProvinceService;
import com.springboot.tourism.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author oneda
 * @version 菜鸟
 */

@SpringBootTest
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveTest(){
        User user = new User();
        user.setUsername("king2");
        user.setPassword("king2");
        user.setEmail("king2@qq.com");

        boolean save = userService.save(user);
        System.out.println("save：" + save);
    }

    @Autowired
    private ProvinceService provinceService;

    @Test
    public void getTest(){

        System.out.println(provinceService.getById(3));
    }

    @Test
    public void saveProvinceTest(){

        Province province = new Province();
        province.setName("山西省");
        province.setTags("一方水土养一方人，晋城");
        province.setPlaceCounts(0);

        System.out.println(provinceService.save(province));
    }

    @Test
    public void getAllTest(){

        List<Province> list = provinceService.list();
        for (Province province : list) {
            System.out.println(province);
        }
    }

    @Test
    public void deleteTest(){

        // UPDATE t_province SET is_deleted=1 WHERE id=? AND is_deleted=0
        System.out.println(provinceService.removeById(8));
    }

    @Test
    public void wrapperTest(){

        // SELECT id,name,tags,placecounts,is_deleted FROM t_province WHERE is_deleted=0 AND (name LIKE ? AND placecounts BETWEEN ? AND ? AND tags IS NOT NULL)
        QueryWrapper<Province> provinceQueryWrapper = new QueryWrapper<>();
        // 采用链式，拼接查询的所有条件
        provinceQueryWrapper.like("name", "山")
                .between("placecounts", 0, 5)
                .isNotNull("tags");

        List<Province> list = provinceService.list(provinceQueryWrapper);
        for (Province province : list) {
            System.out.println(province);
        }
    }

    @Test
    public void wrapperTest2(){

        //SELECT id,name,tags,placecounts,is_deleted FROM t_province WHERE is_deleted=0 ORDER BY placecounts DESC,id ASC
        QueryWrapper<Province> provinceQueryWrapper = new QueryWrapper<>();
        provinceQueryWrapper.orderByDesc("placecounts")
                .orderByAsc("id");

        List<Province> list = provinceService.list(provinceQueryWrapper);
        for (Province province : list) {
            System.out.println(province);
        }
    }

    @Test
    public void wrapperTest3(){

        // UPDATE t_user SET password=? WHERE (id > ? OR username LIKE ?)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("id", 5)
                .or()
                .like("username", "qaz");

        // 要更新的数据
        User user = new User();
        user.setPassword("123456789");

        boolean update = userService.update(user, queryWrapper);
        System.out.println(update);
    }

    @Test
    public void wrapperTest4(){

        // SELECT name,tags,placecounts FROM t_province WHERE is_deleted=0
        QueryWrapper<Province>  queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "tags", "placecounts");

        List<Province> list = provinceService.list(queryWrapper);
        for (Province province : list) {
            System.out.println(list);
        }
    }
}
