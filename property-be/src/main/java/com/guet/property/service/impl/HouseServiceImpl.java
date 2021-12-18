package com.guet.property.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.House;
import com.guet.property.mapper.HouseMapper;
import com.guet.property.service.HouseService;
import org.springframework.stereotype.Service;

/**
 * 此服务类用于实现针对于tb_house表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {

}
