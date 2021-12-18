package com.guet.property.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.Notice;
import com.guet.property.mapper.NoticeMapper;
import com.guet.property.service.NoticeService;
import org.springframework.stereotype.Service;

/**
 * 此服务类用于实现针对于tb_notice表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
