package pers.hao.scts.tms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.common.util.Query;

import pers.hao.scts.tms.dao.ClazzDao;
import pers.hao.scts.tms.entity.ClazzEntity;
import pers.hao.scts.tms.service.ClazzService;


@Service("clazzService")
public class ClazzServiceImpl extends ServiceImpl<ClazzDao, ClazzEntity> implements ClazzService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ClazzEntity> page = this.page(
                new Query<ClazzEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

}