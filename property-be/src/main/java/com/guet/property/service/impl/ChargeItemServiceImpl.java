package com.guet.property.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.ChargeItem;
import com.guet.property.mapper.ChargeItemMapper;
import com.guet.property.service.ChargeItemService;
import org.springframework.stereotype.Service;

/**
 * 此服务类用于实现针对于tb_charge_item表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class ChargeItemServiceImpl extends ServiceImpl<ChargeItemMapper, ChargeItem> implements ChargeItemService {

}