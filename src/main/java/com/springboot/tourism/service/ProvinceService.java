package com.springboot.tourism.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.tourism.pojo.Province;

/**
 * @author oneda
 * @version 菜鸟
 */
public interface ProvinceService extends IService<Province> {

    public IPage<Province> queryWrapperPage(Province province, Integer pageNum, Integer pageSize);
}
