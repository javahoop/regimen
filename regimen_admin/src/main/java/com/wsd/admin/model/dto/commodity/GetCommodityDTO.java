package com.wsd.admin.model.dto.commodity;

import com.wsd.infrastructure.constants.PageParameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName GetCommodityDTO
 * @Description
 * @Author 吴松达
 * @Date 2023/3/2 09:17
 * @Version 1.0
 **/
@Data
@Schema(description = "查询商品入参")
public class GetCommodityDTO extends PageParameter{

        /**
         * 商品名称
         */
        @Schema(description = "商品名称",required = true, example = "冰糖雪梨")
        private String commodityName;
}
