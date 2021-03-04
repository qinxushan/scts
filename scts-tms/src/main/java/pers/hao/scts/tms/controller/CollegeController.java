package pers.hao.scts.tms.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pers.hao.scts.tms.entity.CollegeEntity;
import pers.hao.scts.tms.service.CollegeService;
import pers.hao.scts.common.util.PageUtils;
import pers.hao.scts.common.util.Result;



/**
 * 学院表
 *
 * @author hujiahao
 * @email wt4437892@163.com
 * @date 2021-03-03 23:05:16
 */
@RestController
@RequestMapping("tms/college")
public class CollegeController {
    @Autowired
    private CollegeService collegeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = collegeService.queryPage(params);

        return Result.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result<?> info(@PathVariable("id") Integer id){
		CollegeEntity college = collegeService.getById(id);

        return Result.success(college);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result<?> save(@RequestBody CollegeEntity college){
		collegeService.save(college);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result<?> update(@RequestBody CollegeEntity college){
		collegeService.updateById(college);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result<?> delete(@RequestBody Integer[] ids){
		collegeService.removeByIds(Arrays.asList(ids));

        return Result.success();
    }

}
