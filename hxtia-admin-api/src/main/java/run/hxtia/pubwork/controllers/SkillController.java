package run.hxtia.pubwork.controllers;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import run.hxtia.pubwork.common.baseController.BaseController;
import run.hxtia.pubwork.common.utils.Streams;
import run.hxtia.pubwork.mapStruct.MapStructs;
import run.hxtia.pubwork.pojo.po.Skill;
import run.hxtia.pubwork.pojo.vo.request.save.SkillReqVo;
import run.hxtia.pubwork.pojo.vo.response.SkillVo;
import run.hxtia.pubwork.pojo.vo.result.DataJsonVo;
import run.hxtia.pubwork.services.SkillService;
import run.hxtia.pubwork.common.utils.JsonVos;

import java.util.List;
import java.util.function.Function;

@RestController
@Api(tags = "SkillController")
@Tag(name = "SkillController", description = "首页内容管理")
@RequestMapping("/skills")
public class SkillController extends BaseController<Skill, SkillReqVo> {

    @Autowired
    SkillService skillService;

    @ApiOperation("用MybatisPlus查询skill")
    @GetMapping("/list")
    public DataJsonVo<List<SkillVo>> list() {
        return JsonVos.ok(Streams.map(skillService.list(), MapStructs.INSTANCE::po2vo));
    }

    @ApiOperation("手写映射文件查询skills")
    @GetMapping("/testMapper")
    public List<Skill> testMapper() {
        return skillService.testMapper();
    }

    @Override
    protected IService<Skill> getService() {
        return skillService;
    }

    @Override
    protected Function<SkillReqVo, Skill> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}
