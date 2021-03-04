package pers.hao.scts;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.hao.scts.ums.service.ISysUserService;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UmsApplicationTest {


    @Resource
    ISysUserService sysUserService;

    @Test
    public void contextLoads() {
        log.info(sysUserService.getUserRealName(1));
    }
}
