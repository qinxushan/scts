package pers.hao.scts.tms.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pers.hao.scts.tms.entity.SchoolEntity;
import pers.hao.scts.tms.service.SchoolService;
import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.common.util.Result;



/**
 * 学校表
 *
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-03 23:05:16
 */
@RestController
@RequestMapping("tms/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("tms:school:list")
    public Result<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = schoolService.queryPage(params);

        return Result.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("tms:school:info")
    public Result<?> info(@PathVariable("id") Integer id){
		SchoolEntity school = schoolService.getById(id);

        return Result.success(school);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("tms:school:save")
    public Result<?> save(@RequestBody SchoolEntity school){
		schoolService.save(school);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("tms:school:update")
    public Result<?> update(@RequestBody SchoolEntity school){
		schoolService.updateById(school);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("tms:school:delete")
    public Result<?> delete(@RequestBody Integer[] ids){
		schoolService.removeByIds(Arrays.asList(ids));

        return Result.success();
    }

}
