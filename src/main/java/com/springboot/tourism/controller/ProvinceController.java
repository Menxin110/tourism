package com.springboot.tourism.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.tourism.pojo.Province;
import com.springboot.tourism.service.ProvinceService;
import com.springboot.tourism.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author oneda
 * @version 菜鸟
 */

@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/page")
    public JsonData getPage(@RequestBody Province province,
                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "2") Integer pageSize){
        JsonData jsonData = new JsonData();

        System.out.println(province);
        IPage<Province> provinceIPage = provinceService.queryWrapperPage(province, pageNum, pageSize);
        jsonData.setMsg("查询分页数据成功");
        jsonData.setContent(provinceIPage);
        return jsonData;
    }
}
