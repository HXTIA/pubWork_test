package run.hxtia.pubwork.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import run.hxtia.pubwork.common.utils.Streams;
import run.hxtia.pubwork.mapStruct.MapStructs;
import run.hxtia.pubwork.pojo.po.Skill;
import run.hxtia.pubwork.pojo.vo.response.SkillVo;
import run.hxtia.pubwork.pojo.vo.result.DataJsonVo;
import run.hxtia.pubwork.services.SkillService;
import run.hxtia.pubwork.common.utils.JsonVos;

import java.util.List;

@RestController
@Api(tags = "SkillController")
@Tag(name = "SkillController", description = "首页内容管理")
@RequestMapping("/skills")
public class SkillController {

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

}
