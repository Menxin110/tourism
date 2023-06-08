package com.springboot.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.tourism.mapper.ProvinceMapper;
import com.springboot.tourism.pojo.Province;
import com.springboot.tourism.service.ProvinceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author oneda
 * @version 菜鸟
 */

@Transactional
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements ProvinceService {


    @Override
    public IPage<Province> queryWrapperPage(Province province, Integer pageNum, Integer pageSize) {
        // 构造分页参数
        Page<Province> provincePage = new Page<>(pageNum, pageSize);
        // 构造查询条件
        LambdaQueryWrapper<Province> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(province.getName()), Province::getName, province.getName())
                .ge(province.getPlaceCounts() != null, Province::getPlaceCounts, province.getPlaceCounts());

        return page(provincePage, lambdaQueryWrapper);
    }
}
