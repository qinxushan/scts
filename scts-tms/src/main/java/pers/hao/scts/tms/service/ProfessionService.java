package pers.hao.scts.tms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.tms.entity.ProfessionEntity;

import java.util.Map;

/**
 * 专业表
 *
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-01 11:12:30
 */
public interface ProfessionService extends IService<ProfessionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

