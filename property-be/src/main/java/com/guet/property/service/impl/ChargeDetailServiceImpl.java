package com.guet.property.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.ChargeDetail;
import com.guet.property.mapper.ChargeDetailMapper;
import com.guet.property.service.ChargeDetailService;
import org.springframework.stereotype.Service;

/**
 * 此服务类用于实现针对于tb_charge_detail表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class ChargeDetailServiceImpl extends ServiceImpl<ChargeDetailMapper, ChargeDetail> implements ChargeDetailService {

}
