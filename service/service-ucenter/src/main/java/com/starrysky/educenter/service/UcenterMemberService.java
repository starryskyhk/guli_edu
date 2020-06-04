package com.starrysky.educenter.service;

import com.starrysky.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.starrysky.educenter.entity.vo.LoginVo;
import com.starrysky.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author starrysky
 * @since 2020-06-02
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    UcenterMember getByOpenid(String openid);
}
