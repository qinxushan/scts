package pers.hao.scts.tms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.tms.entity.ClazzEntity;

import java.util.Map;

/**
 * 班级表
 *
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-03 23:05:16
 */
public interface ClazzService extends IService<ClazzEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

