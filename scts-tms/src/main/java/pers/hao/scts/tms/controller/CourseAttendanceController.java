package pers.hao.scts.tms.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pers.hao.scts.tms.entity.CourseAttendanceEntity;
import pers.hao.scts.tms.service.CourseAttendanceService;
import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.common.util.Result;

import javax.annotation.Resource;


/**
 * 考勤表
 *
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-03 23:05:16
 */
@RestController
@RequestMapping("tms/courseattendance")
public class CourseAttendanceController {

    @Resource
    private CourseAttendanceService courseAttendanceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("tms:courseattendance:list")
    public Result<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = courseAttendanceService.queryPage(params);

        return Result.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("tms:courseattendance:info")
    public Result<?> info(@PathVariable("id") Integer id){
		CourseAttendanceEntity courseAttendance = courseAttendanceService.getById(id);

        return Result.success(courseAttendance);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result<?> save(@RequestBody CourseAttendanceEntity courseAttendance){
		courseAttendanceService.save(courseAttendance);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("tms:courseattendance:update")
    public Result<?> update(@RequestBody CourseAttendanceEntity courseAttendance){
		courseAttendanceService.updateById(courseAttendance);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("tms:courseattendance:delete")
    public Result<?> delete(@RequestBody Integer[] ids){
		courseAttendanceService.removeByIds(Arrays.asList(ids));

        return Result.success();
    }

}
