package pers.hao.scts.tms.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pers.hao.scts.tms.entity.ProfessionEntity;
import pers.hao.scts.tms.service.ProfessionService;
import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.common.util.Result;



/**
 * 专业表
 *
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-03 23:05:16
 */
@RestController
@RequestMapping("tms/profession")
public class ProfessionController {
    @Autowired
    private ProfessionService professionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("tms:profession:list")
    public Result<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = professionService.queryPage(params);

        return Result.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("tms:profession:info")
    public Result<?> info(@PathVariable("id") Integer id){
		ProfessionEntity profession = professionService.getById(id);

        return Result.success(profession);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("tms:profession:save")
    public Result<?> save(@RequestBody ProfessionEntity profession){
		professionService.save(profession);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("tms:profession:update")
    public Result<?> update(@RequestBody ProfessionEntity profession){
		professionService.updateById(profession);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("tms:profession:delete")
    public Result<?> delete(@RequestBody Integer[] ids){
		professionService.removeByIds(Arrays.asList(ids));

        return Result.success();
    }

}
