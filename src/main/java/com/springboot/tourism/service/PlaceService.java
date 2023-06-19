package com.springboot.tourism.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.tourism.pojo.Place;

import java.util.List;

/**
 * @author oneda
 * @version 菜鸟
 */

public interface PlaceService extends IService<Place> {

    // 根据身份信息拿到景点分页数据
    IPage<Place> getPlacePageByProvince(Integer pageNum, Integer pageSize, String provinceId);

    /**
     * 删除景点信息，对应省份修改
     *
     * @param id
     * @return
     */
    boolean deletePlaceAndUpdateProvince(Integer id);

    /**
     * 保存景点信息，对应省份修改
     *
     * @param place
     * @return
     */
    boolean savePlaceAndUpdateProvince(Place place);
}
