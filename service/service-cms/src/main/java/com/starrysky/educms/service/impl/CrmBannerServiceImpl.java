package com.starrysky.educms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starrysky.educms.entity.CrmBanner;
import com.starrysky.educms.mapper.CrmBannerMapper;
import com.starrysky.educms.service.CrmBannerService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author starrysky
 * @since 2020-06-01
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    @Cacheable(value = "banner",key ="'selectIndexList'" )
    public List<CrmBanner> selectAllBanner() {
        List<CrmBanner> list = baseMapper.selectList(null);
        return list;
    }

    @Override
    @CacheEvict(value = "banner", allEntries=true)
    public void saveBanner(CrmBanner banner) {
        baseMapper.insert(banner);
    }

    @Override
    @CacheEvict(value = "banner", allEntries=true)
    public void updateBanner(CrmBanner banner) {
        baseMapper.updateById(banner);
    }
    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void removeBanner(String id) {
        baseMapper.deleteById(id);
    }
}
