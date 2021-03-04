package pers.hao.scts.tms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.common.util.Query;
import pers.hao.scts.tms.service.SchoolService;
import pers.hao.scts.tms.dao.SchoolDao;
import pers.hao.scts.tms.entity.SchoolEntity;


@Service("schoolService")
public class SchoolServiceImpl extends ServiceImpl<SchoolDao, SchoolEntity> implements SchoolService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SchoolEntity> page = this.page(
                new Query<SchoolEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

}