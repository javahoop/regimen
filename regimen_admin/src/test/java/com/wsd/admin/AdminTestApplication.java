package com.wsd.admin;

import com.wsd.admin.dao.CommodityOptionDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName AdminApplication
 * @Description
 * @Author 吴松达
 * @Date 2023/2/28 14:30
 * @Version 1.0
 **/

@SpringBootTest
public class AdminTestApplication  {
    @Autowired
    private CommodityOptionDao commodityOptionDAO;
    @Test
    public  void getDao() {
      //System.out.println(commodityOptionDAO.selectByPrimaryKey(3L).toString());
    }
}
