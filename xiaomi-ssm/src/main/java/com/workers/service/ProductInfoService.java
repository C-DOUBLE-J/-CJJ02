package com.workers.service;

import com.github.pagehelper.PageInfo;
import com.workers.pojo.ProductInfo;
import com.workers.pojo.vo.ProductInfoVo;


import java.util.List;

public interface ProductInfoService {
    //显示全部商品，（不分页的情况 测试）
    List<ProductInfo> getAll();


    //分页显示商品
    PageInfo splitPage(int pageNum,int pageSize);

    //增加商品
    int save(ProductInfo info);

    //通过主键id查询商品
    ProductInfo getById(int pid);

    //更新商品
    int update(ProductInfo info);

    //单个商品删除
    int delete(int pid);

    //批量删除商品
    int deleteBatch(String []ids);

    //多条件查询商品
    List<ProductInfo> selectCondition(ProductInfoVo vo);

    //多条件查询分页
    public PageInfo splitPageVo(ProductInfoVo vo,int pageSize);
}
