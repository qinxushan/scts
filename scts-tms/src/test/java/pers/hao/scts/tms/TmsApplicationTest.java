package pers.hao.scts.tms;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.hao.scts.tms.entity.CourseArrangementEntity;
import pers.hao.scts.tms.service.CourseArrangementService;
import pers.hao.scts.tms.vo.CourseArrangementVO;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TmsApplicationTest {

    @Resource
    CourseArrangementService courseArrangementService;

    @Test
    public void contextTest() {
        CourseArrangementEntity courseArrangementEntity = new CourseArrangementEntity();
        courseArrangementEntity.setClassId(1);
        courseArrangementEntity.setTeacherId(1);
        courseArrangementEntity.setBeginSection(3);
        courseArrangementEntity.setEndSection(4);
        courseArrangementEntity.setStartWeek(1);
        courseArrangementEntity.setEndWeek(17);
        courseArrangementEntity.setCourseId(2);
        courseArrangementEntity.setClassRoom("木铎楼504");
        courseArrangementEntity.setSingleOrDouble(2);
        courseArrangementEntity.setDayOrder(4);
        courseArrangementEntity.setLatitude(new BigDecimal(111));
        courseArrangementEntity.setLongitude(new BigDecimal(111));
        courseArrangementService.saveCourseArrangement(courseArrangementEntity);

    }

}
