package com.springboot.tourism.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.tourism.pojo.OptionProvince;
import com.springboot.tourism.pojo.Province;
import com.springboot.tourism.service.ProvinceService;
import com.springboot.tourism.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author oneda
 * @version 菜鸟
 */

@Slf4j
@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    /**
     * 获取所有省份的id和名
     *
     * @return
     */
    @GetMapping()
    public JsonData getAllProvince(){
        JsonData jsonData = new JsonData();
        List<Province> list = provinceService.list();

        List<OptionProvince> optionList = null;

        if (list != null){
            optionList = new ArrayList<>();
            for (Province province : list) {

                optionList.add(new OptionProvince(province.getId(), province.getName()));
            }
            jsonData.setContent(optionList);
            jsonData.setMsg("获取成功");
        }else {
            jsonData.setCode(0);
            jsonData.setMsg("获取失败");
        }

        return jsonData;
    }

    /**
     * 分页查询省份数据
     *
     * @param province
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public JsonData getPage(Province province,
                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "2") Integer pageSize){
        JsonData jsonData = new JsonData();

        System.out.println(province);
        IPage<Province> provinceIPage = provinceService.queryWrapperPage(province, pageNum, pageSize);
        jsonData.setMsg("查询分页数据成功");
        jsonData.setContent(provinceIPage);
        return jsonData;
    }

    /**
     * 保存省份信息
     *
     * @param province
     * @return
     */
    @PostMapping()
    public JsonData saveProvince(@RequestBody Province province){
        log.info("保存数据：" + province);
        JsonData jsonData = new JsonData();
        boolean save = provinceService.save(province);
        if (save){
            jsonData.setMsg("保存成功");
        } else {
            jsonData.setCode(0);
            jsonData.setMsg("保存失败");
        }

        return jsonData;
    }
    /**
     * 修改省份信息
     *
     */
    @PutMapping()
    public JsonData updateProvince(@RequestBody Province province){
        JsonData jsonData = new JsonData();

        boolean b = provinceService.updateById(province);
        if(b){

            jsonData.setMsg("修改省份信息成功");
        }else {
            jsonData.setCode(0);
            jsonData.setMsg("修改省份信息失败！");
        }
        return jsonData;
    }

    @DeleteMapping("/{id}")
    private JsonData deleteProvince(@PathVariable("id") Integer id){
        log.info("要删除的省份id：" + id);
        JsonData jsonData = new JsonData();

        boolean b = provinceService.removeById(id);
        if (b){
            jsonData.setMsg("删除成功");
        }else {
            jsonData.setCode(0);
            jsonData.setMsg("删除失败");
        }
        return jsonData;
    }
}
