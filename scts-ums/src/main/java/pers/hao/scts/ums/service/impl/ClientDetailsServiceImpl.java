package pers.hao.scts.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.hao.scts.entity.ums.ClientDetails;
import pers.hao.scts.ums.mapper.ClientDetailsMapper;
import pers.hao.scts.ums.service.IClientDetailsService;

/**
 * <p>
 * 客户端详细信息表 服务实现类
 * </p>
 *
 * @author hujiahao
 * @since 2021-02-06
 */
@Service
public class ClientDetailsServiceImpl extends ServiceImpl<ClientDetailsMapper, ClientDetails> implements IClientDetailsService {

}
