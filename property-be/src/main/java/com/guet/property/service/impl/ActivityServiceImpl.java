package com.guet.property.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.Activity;
import com.guet.property.mapper.ActivityMapper;
import com.guet.property.service.ActivityService;
import org.springframework.stereotype.Service;

/**
 * 此服务类用于实现针对于tb_activity表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

}
