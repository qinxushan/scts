package pers.hao.scts.tms.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pers.hao.scts.tms.entity.CourseEntity;
import pers.hao.scts.tms.service.CourseService;
import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.common.util.Result;



/**
 * 课程表
 *
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-03 23:05:16
 */
@RestController
@RequestMapping("tms/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("tms:course:list")
    public Result<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = courseService.queryPage(params);

        return Result.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("tms:course:info")
    public Result<?> info(@PathVariable("id") Integer id){
		CourseEntity course = courseService.getById(id);

        return Result.success(course);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("tms:course:save")
    public Result<?> save(@RequestBody CourseEntity course){
		courseService.save(course);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("tms:course:update")
    public Result<?> update(@RequestBody CourseEntity course){
		courseService.updateById(course);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("tms:course:delete")
    public Result<?> delete(@RequestBody Integer[] ids){
		courseService.removeByIds(Arrays.asList(ids));

        return Result.success();
    }

}
