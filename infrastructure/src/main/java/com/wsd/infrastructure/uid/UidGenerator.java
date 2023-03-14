package com.wsd.infrastructure.uid;

import com.simonalong.butterfly.sequence.ButterflyIdGenerator;
import com.simonalong.butterfly.worker.db.DbButterflyConfig;

/**
 * @author 吴松达
 * @ClassName UidGenrator.java
 * @Description 全局唯一id获取   配置
 * @createTime 2021年11月10日 09:27:00
 */
//@Component
public class UidGenerator {
    private DbButterflyConfig config =null;
    private ButterflyIdGenerator generator=null;
    private static  final String NAMESPACES = "chat";

    /**
     * 该方法 特意私有化，防止在外部被实例化，只能是单例
     */
    private UidGenerator() {
        config = new DbButterflyConfig();
        config.setUrl("jdbc:mysql://139.9.225.98:3307/uid_generator?useUnicode=true&characterEncoding=utf-8&useSSL=true&allowMultiQueries=true&serverTimezone=Asia/Shanghai");
        config.setUserName("root");
        config.setPassword("wsd120418");
        generator = ButterflyIdGenerator.getInstance(config);
        // 设置起始时间，如果不设置，则默认从2020年2月22日开始 项目启动后不可变更
        generator.setStartTime(2021, 11, 10, 0, 0, 0);
        // 添加业务空间，如果业务空间不存在，则会注册
        generator.addNamespaces(NAMESPACES);
    }

    /**
     * 获取唯一Id（在多进程中只要数据库 与 namespaces 一致，数据全局唯一）
     * @return
     */
    public long getId(){
        return generator.getUUid(NAMESPACES);
    }

}
