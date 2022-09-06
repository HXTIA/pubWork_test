package run.hxtia.pubwork.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import run.hxtia.pubwork.common.cache.Caches;
import run.hxtia.pubwork.common.utils.Constants;
import run.hxtia.pubwork.common.utils.JsonVos;
import run.hxtia.pubwork.mapStruct.MapStructs;
import run.hxtia.pubwork.mappers.UserMapper;
import run.hxtia.pubwork.pojo.po.User;
import run.hxtia.pubwork.pojo.vo.request.LoginReqVo;
import run.hxtia.pubwork.pojo.vo.result.CodeMsg;
import run.hxtia.pubwork.pojo.vo.result.LoginVo;
import run.hxtia.pubwork.services.UserService;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl
    extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public LoginVo login(LoginReqVo reqVo) {

        //TODO: 加强LambdaQueryWrapper
        // 通过邮箱查询user
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, reqVo.getEmail());
        User userPo = baseMapper.selectOne(wrapper);

        if (userPo == null) {
            return JsonVos.raise(CodeMsg.WRONG_USERNAME);
        }

        if (!userPo.getPassword().equals(reqVo.getPassword())) {
            return JsonVos.raise(CodeMsg.WRONG_PASSWORD);
        }

        if (userPo.getState() == Constants.UserStatus.UNABLE) {
            return JsonVos.raise(CodeMsg.USER_LOCKED);
        }

        // 更新登录时间
        userPo.setLoginTime(new Date());
        baseMapper.updateById(userPo);

        // 生成Token
        String token = UUID.randomUUID().toString();

        // 将对象其放入缓存中
        Caches.putToken(token, userPo);

        // 将 po -> vo
        LoginVo vo = MapStructs.INSTANCE.po2loginVo(userPo);
        vo.setToken(token);

        return vo;
    }
}
