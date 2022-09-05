package run.hxtia.pubwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.hxtia.pubwork.po.Skill;
import run.hxtia.pubwork.services.SkillService;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    SkillService skillService;

    @GetMapping("/list")
    List<Skill> list() {
        return skillService.list();
    }

    @GetMapping("/testMapper")
    List<Skill> testMapper() {
        return skillService.testMapper();
    }

}
