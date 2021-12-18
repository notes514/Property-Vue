package com.guet.property.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.Letter;
import com.guet.property.mapper.LetterMapper;
import com.guet.property.service.LetterService;
import org.springframework.stereotype.Service;

/**
 * 此服务类用于实现针对于tb_letter表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class LetterServiceImpl extends ServiceImpl<LetterMapper, Letter> implements LetterService {

}
