package com.guet.property.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.Repair;
import com.guet.property.mapper.RepairMapper;
import com.guet.property.service.RepairService;
import org.springframework.stereotype.Service;

/**
 * 此服务类用于实现针对于tb_repair表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements RepairService {

}
