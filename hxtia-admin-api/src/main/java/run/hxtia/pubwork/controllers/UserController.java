package run.hxtia.pubwork.controllers;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import run.hxtia.pubwork.common.baseController.BaseController;
import run.hxtia.pubwork.common.cache.Caches;
import run.hxtia.pubwork.common.shiro.TokenFilter;
import run.hxtia.pubwork.common.utils.JsonVos;
import run.hxtia.pubwork.common.utils.Streams;
import run.hxtia.pubwork.mapStruct.MapStructs;
import run.hxtia.pubwork.pojo.po.User;
import run.hxtia.pubwork.pojo.vo.request.LoginReqVo;
import run.hxtia.pubwork.pojo.vo.request.save.UserReqVo;
import run.hxtia.pubwork.pojo.vo.response.UserVo;
import run.hxtia.pubwork.pojo.vo.result.DataJsonVo;
import run.hxtia.pubwork.pojo.vo.result.JsonVo;
import run.hxtia.pubwork.pojo.vo.result.LoginVo;
import run.hxtia.pubwork.services.UserService;

import java.util.List;
import java.util.function.Function;

@RestController
@Api(tags = "UserController")
@Tag(name = "UserController", description = "用户模块")
@RequestMapping("/users")
public class UserController extends BaseController<User, UserReqVo> {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public DataJsonVo<LoginVo> login(@RequestBody LoginReqVo reqVo) {
        return JsonVos.ok(userService.login(reqVo));
    }

    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public JsonVo logout(@RequestHeader(TokenFilter.HEADER_TOKEN) String token) {
        // 清空缓存中的token就可以了
        Caches.remove(token);
        return JsonVos.ok();
    }

    @GetMapping("/list")
    @ApiOperation("查询所有的用户")
    public DataJsonVo<List<UserVo>> list() {
        return JsonVos.ok(Streams.map(userService.list(), MapStructs.INSTANCE::po2vo));
    }

    @Override
    protected IService<User> getService() {
        return userService;
    }

    @Override
    protected Function<UserReqVo, User> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}
