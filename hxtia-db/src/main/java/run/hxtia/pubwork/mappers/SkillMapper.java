package run.hxtia.pubwork.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import run.hxtia.pubwork.pojo.po.Skill;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 持久层：Skill表
 */
@Repository
public interface SkillMapper extends BaseMapper<Skill> {
    List<Skill> testMapper();
}
