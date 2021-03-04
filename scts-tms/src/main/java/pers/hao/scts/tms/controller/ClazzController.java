package pers.hao.scts.tms.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pers.hao.scts.tms.entity.ClazzEntity;
import pers.hao.scts.tms.service.ClazzService;
import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.common.util.Result;



/**
 * 班级表
 *
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-03 23:05:16
 */
@RestController
@RequestMapping("tms/clazz")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("tms:clazz:list")
    public Result<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = clazzService.queryPage(params);

        return Result.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("tms:clazz:info")
    public Result<?> info(@PathVariable("id") Integer id){
		ClazzEntity clazz = clazzService.getById(id);

        return Result.success(clazz);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("tms:clazz:save")
    public Result<?> save(@RequestBody ClazzEntity clazz){
		clazzService.save(clazz);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("tms:clazz:update")
    public Result<?> update(@RequestBody ClazzEntity clazz){
		clazzService.updateById(clazz);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("tms:clazz:delete")
    public Result<?> delete(@RequestBody Integer[] ids){
		clazzService.removeByIds(Arrays.asList(ids));

        return Result.success();
    }

}
