package com.guet.property.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MybatisPlus配置类
 *
 * @author dhxstart
 * @date 2021/12/11 21:12
 */
@Configuration
@EnableTransactionManagement
@MapperScan(value = "com.guet.property.mapper")
public class MybatisPlusConfig {

    /**
     * 新的分页插件，一级缓存和二级缓存遵循mybatis规则，
     * 需要设置MybatisConfiguration的 useDeprecatedExecutor = false，
     * 避免缓存出现问题
     *
     * @return MybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseGeneratedShortKey(false);
    }
}
