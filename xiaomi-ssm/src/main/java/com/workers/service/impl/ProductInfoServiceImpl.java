package com.workers.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.workers.pojo.ProductInfo;
import com.workers.pojo.ProductInfoExample;
import com.workers.mapper.ProductInfoMapper;
import com.workers.pojo.vo.ProductInfoVo;
import com.workers.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    //在业务逻辑层中，一定会有数据访问层的对象
    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAll() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    /**
     *
     * @param pageNum 当前页
     * @param pageSize 每页显示几条记录
     * @return
     */
    //select * from product_info limit 起始记录数=(当前页-1)*每页条数，每页取几条
    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
        //分页插件使用PageHelper工具类完成分页
        PageHelper.startPage(pageNum,pageSize);

        //进行数据PageInfo的数据封装
        //进行有条件的查询操作，必须要创建ProductInfoExample对象
        ProductInfoExample example = new ProductInfoExample();

        //设置排序，降序（方便我们插入查看）
        //select * from product_info order by p_id desc
        example.setOrderByClause("p_id desc");
        //取集合：记得设置PageHelper.startPage(pageNum,pageSize);搞了半天...
        List<ProductInfo> list = productInfoMapper.selectByExample(example);

        //将查询到的集合封装到PageInfo对象
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int save(ProductInfo info) {
        return productInfoMapper.insert(info);
    }

    @Override
    public ProductInfo getById(int pid) {
        return productInfoMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int update(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKey(info);
    }

    @Override
    public int delete(int pid) {
        return productInfoMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int deleteBatch(String[] pids) {
        return productInfoMapper.deleteBatch(pids);
    }

    @Override
    public List<ProductInfo> selectCondition(ProductInfoVo vo) {
        return productInfoMapper.selectCondition(vo);
    }

    @Override
    public PageInfo splitPageVo(ProductInfoVo vo, int pageSize) {
        //取出集合之前，先要设置PageHelper.startPage()属性
        PageHelper.startPage(vo.getPage(),pageSize);
        List<ProductInfo> list = productInfoMapper.selectCondition(vo);
        return new PageInfo<>(list);
    }


}
