package com.guet.property.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.Owner;
import com.guet.property.mapper.OwnerMapper;
import com.guet.property.service.OwnerService;
import org.springframework.stereotype.Service;

/**
 * 此服务类用于实现针对于tb_owner表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class OwnerServiceImpl extends ServiceImpl<OwnerMapper, Owner> implements OwnerService {

}
