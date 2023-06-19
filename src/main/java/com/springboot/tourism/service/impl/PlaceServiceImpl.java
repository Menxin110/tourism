package com.springboot.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.tourism.mapper.PlaceMapper;
import com.springboot.tourism.mapper.ProvinceMapper;
import com.springboot.tourism.pojo.Place;
import com.springboot.tourism.pojo.Province;
import com.springboot.tourism.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author oneda
 * @version 菜鸟
 */

@Transactional
@Service
public class PlaceServiceImpl extends ServiceImpl<PlaceMapper, Place> implements PlaceService {
    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public IPage<Place> getPlacePageByProvince(Integer pageNum, Integer pageSize, String provinceId) {

        // 构造分页参数
        Page<Place> placePage = new Page<>(pageNum, pageSize);
        // 构造查询条件
        LambdaQueryWrapper<Place> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ge(provinceId != null, Place::getProvinceId, provinceId);

        return page(placePage, lambdaQueryWrapper);
    }

    @Override
    public boolean deletePlaceAndUpdateProvince(Integer id) {
        Place place = getById(id);

        Province province = provinceMapper.selectById(place.getProvinceId());
        province.setPlaceCounts(province.getPlaceCounts() - 1);

        // 执行删除和修改
        boolean b = removeById(id);
        int i = provinceMapper.updateById(province);

        return b && i != 0;
    }

    @Override
    public boolean savePlaceAndUpdateProvince(Place place) {

        Province province = provinceMapper.selectById(place.getProvinceId());
        province.setPlaceCounts(province.getPlaceCounts() + 1);

        // 执行保存和修改
        boolean save = save(place);
        int i = provinceMapper.updateById(province);

        return save && i != 0;
    }
}
