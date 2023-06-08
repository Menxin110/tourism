package com.springboot.tourism.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author oneda
 * @version 菜鸟
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 为mybatis-plus添加插件
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 添加分页插件
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        // 跳出页码范围时，回到首页
        paginationInnerInterceptor.setOverflow(true);
        // 在此也可设置每页最多多少条记录（优先级没有Page高）
//        paginationInnerInterceptor.setMaxLimit(3L);
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);

        // 添加乐观锁插件
        OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor = new OptimisticLockerInnerInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(optimisticLockerInnerInterceptor);
        return mybatisPlusInterceptor;
    }
}
