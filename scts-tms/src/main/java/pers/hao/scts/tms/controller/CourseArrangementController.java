package pers.hao.scts.tms.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pers.hao.scts.tms.entity.CourseArrangementEntity;
import pers.hao.scts.tms.service.CourseArrangementService;
import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.common.util.Result;



/**
 * 课程安排表
 *
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-03 23:05:16
 */
@RestController
@RequestMapping("tms/coursearrangement")
public class CourseArrangementController {
    @Autowired
    private CourseArrangementService courseArrangementService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = courseArrangementService.queryPage(params);

        return Result.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result<?> info(@PathVariable("id") Integer id){
		CourseArrangementEntity courseArrangement = courseArrangementService.getById(id);

        return Result.success(courseArrangement);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result<?> save(@RequestBody CourseArrangementEntity courseArrangement){
		courseArrangementService.save(courseArrangement);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result<?> update(@RequestBody CourseArrangementEntity courseArrangement){
		courseArrangementService.updateById(courseArrangement);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result<?> delete(@RequestBody Integer[] ids){
		courseArrangementService.removeByIds(Arrays.asList(ids));

        return Result.success();
    }

}
