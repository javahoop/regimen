package com.wsd.customer.service;

import com.wsd.customer.model.dto.EstimateSubmitDTO;
import com.wsd.customer.model.dto.GetEstimateDTO;
import com.wsd.customer.model.vo.EstimateVO;

import java.util.List;

/**
 * @ClassName EstimateService
 * @Description
 * @Author 吴松达
 * @Date 2023/3/14 14:05
 * @Version 1.0
 **/
public interface EstimateService {

    EstimateVO submit(EstimateSubmitDTO param);

    List<EstimateVO> select(GetEstimateDTO param);
}
