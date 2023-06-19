package com.springboot.tourism.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.tourism.pojo.Place;
import com.springboot.tourism.service.PlaceService;
import com.springboot.tourism.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author oneda
 * @version 菜鸟
 */

@RequestMapping("/place")
@RestController
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Value("${upload.dir}")
    private String realPath;

    /**
     * 修改景点信息
     */
    @PutMapping()
    public JsonData updatePlace(@RequestPart("pic") MultipartFile pic,
                                @RequestBody Place place) throws IOException {
        JsonData jsonData = new JsonData();
        if (!pic.isEmpty()){
            // 上传文件名
            String originalFilename = pic.getOriginalFilename();
            originalFilename = UUID.randomUUID().toString() + originalFilename;
            pic.transferTo(new File(realPath + originalFilename));
            place.setPicPath(realPath + pic.getOriginalFilename());
        }

        boolean b = placeService.updateById(place);
        if (b){
            jsonData.setMsg("修改成功");
        }else {
            jsonData.setCode(0);
            jsonData.setMsg("修改失败");
        }
        return jsonData;
    }

    /**
     * 根据 id 查询景点信息
     */
    @GetMapping("/{id}")
    public JsonData getPlaceById(@PathVariable("id") Integer id){
        JsonData jsonData = new JsonData();
        Place place = placeService.getById(id);

        if (place != null){
            jsonData.setMsg("查询成功");
            jsonData.setContent(place);
        }else {
            jsonData.setCode(0);
            jsonData.setMsg("查询失败");
        }

        return jsonData;
    }

    /**
     * 删除景点信息
     */
    @DeleteMapping("/{id}")
    public JsonData deletePlace(@PathVariable("id") Integer id){
        JsonData jsonData = new JsonData();
        boolean b = placeService.deletePlaceAndUpdateProvince(id);

        if (b){
            jsonData.setMsg("删除成功");
        }else {
            jsonData.setCode(0);
            jsonData.setMsg("删除失败");
        }
        return jsonData;
    }

    /**
     * 保存景点信息
     *
     * @param pic
     * @return
     */
    @PostMapping()
    public JsonData savePlace(@RequestPart("pic") MultipartFile pic,
                              @RequestBody Place place) throws IOException {
        JsonData jsonData = new JsonData();

        if (!pic.isEmpty()){
            // 上传文件名
            String originalFilename = pic.getOriginalFilename();
            originalFilename = UUID.randomUUID().toString() + originalFilename;
            pic.transferTo(new File(realPath + originalFilename));
            place.setPicPath(realPath + pic.getOriginalFilename());

            // 保存景点信息
            boolean save = placeService.savePlaceAndUpdateProvince(place);
            if(save){
                jsonData.setMsg("保存成功");
            }else {
                jsonData.setMsg("保存失败");
            }
        }else {
            jsonData.setMsg("文件为空！");
        }
        return jsonData;
    }

    @GetMapping("/page")
    public JsonData placePageByProvince(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "2") Integer pageSize,
                                        @RequestParam(value = "provinceId", defaultValue = "1") String provinceId){
        JsonData jsonData = new JsonData();

        IPage<Place> placeList = placeService.getPlacePageByProvince(pageNum, pageSize, provinceId);
        jsonData.setMsg("查询景点分页数据成功");
        jsonData.setContent(placeList);

        return jsonData;
    }
}
