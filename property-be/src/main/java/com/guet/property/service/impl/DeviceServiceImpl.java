package com.guet.property.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.Device;
import com.guet.property.mapper.DeviceMapper;
import com.guet.property.service.DeviceService;
import org.springframework.stereotype.Service;

/**
 * 此服务类用于实现针对于tb_device表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

}
