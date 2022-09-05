package run.hxtia.pubwork.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import run.hxtia.pubwork.mappers.SkillMapper;
import run.hxtia.pubwork.po.Skill;
import run.hxtia.pubwork.services.SkillService;

import java.util.List;

@Service
public class SkillServiceImpl
    extends ServiceImpl<SkillMapper, Skill> implements SkillService {

    @Override
    public List<Skill> testMapper() {
        return baseMapper.testMapper();
    }
}
