package com.starrysky.educms.service;

import com.starrysky.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author starrysky
 * @since 2020-06-01
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectAllBanner();
    //新增Banner
    void saveBanner(CrmBanner banner);
    //修改Banner
    void updateBanner(CrmBanner banner);

    void removeBanner(String id);
}
