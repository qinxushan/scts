package pers.hao.scts.tms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.common.util.Query;
import pers.hao.scts.tms.service.CollegeService;
import pers.hao.scts.tms.dao.CollegeDao;
import pers.hao.scts.tms.entity.CollegeEntity;


@Service("collegeService")
public class CollegeServiceImpl extends ServiceImpl<CollegeDao, CollegeEntity> implements CollegeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollegeEntity> page = this.page(
                new Query<CollegeEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

}