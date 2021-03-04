package pers.hao.scts.tms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.tms.entity.CollegeEntity;

import java.util.Map;

/**
 * 学院表
 *
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-01 11:12:31
 */
public interface CollegeService extends IService<CollegeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

