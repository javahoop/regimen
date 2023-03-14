package com.wsd.infrastructure.mybatis.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字段映射枚举
 * @author wsd
 * @date 2021/12/26
 */
@Getter
@AllArgsConstructor
public enum ColumnOverrideEnum {
    // 配置is_delete映射到deleted
    IS_DELETED("is_deleted", "deleted"),
    // 配置is_verified字段到verified的映射
    IS_VERIFIED("is_verified", "verified"),
    // 配置is_disabled字段到disabled的映射
    IS_DISABLED("is_disabled", "disabled"),
    // 配置is_manager字段到manager的映射
    IS_MANAGER("is_manager", "manager"),
    // 配置is_invalid字段到invalid的映射
    IS_INVALID("is_invalid", "invalid"),
    // 配置is_refund_drug字段到refundDrug的映射
    IS_REFUND_DRUG("is_refund_drug", "refundDrug"),
    // 配置is_long_prescription字段到longPrescription的映射
    IS_LONG_PRESCRIPTION("is_long_prescription", "longPrescription"),
    // 配置is_main_drug字段到mainDrug的映射
    IS_MAIN_DRUG("is_main_drug", "mainDrug"),
    // 配置is_allergy字段到allergy的映射
    IS_ALLERGY("is_allergy", "allergy"),
    // 配置is_default_address字段到defaultAddress的映射
    IS_DEFAULT_ADDRESS("is_default_address", "defaultAddress"),
    // 配置is_first字段到first的映射
    IS_FIRST("is_first", "first"),
    // 配置is_allergy_history字段到allergyHistory的映射
    IS_ALLERGY_HISTORY("is_allergy_history", "allergyHistory"),
    // 配置is_disease_history字段到diseaseHistory的映射
    IS_DISEASE_HISTORY("is_disease_history", "diseaseHistory"),
    // 配置is_heredity字段到heredity的映射
    IS_HEREDITY("is_heredity", "heredity"),
    // 配置is_charged字段到charged的映射
    IS_CHARGED("is_charged", "charged"),
    // 配置is_refund字段到refund的映射
    IS_REFUND("is_refund", "refund"),
    // 配置is_upload字段到upload的映射
    IS_UPLOAD("is_upload", "upload"),
    // 配置is_prescription_drug字段到prescriptionDrug的映射
    IS_PRESCRIPTION_DRUG("is_prescription_drug", "prescriptionDrug"),
    // 配置is_read字段到read的映射
    IS_READ("is_read", "read"),
    // 配置is_clear字段到clear的映射
    IS_CLEAR("is_clear", "clear"),
    // 配置is_flagship字段到flagship的映射
    IS_FLAGSHIP("is_flagship", "flagship"),
    // 配置is_goods_show字段到goodsShow的映射
    IS_GOODS_SHOW("is_goods_show", "goodsShow"),
    // 配置is_mall_show字段到mallShow的映射
    IS_MALL_SHOW("is_mall_show", "mallShow"),
    // 配置is_official_version字段到officialVersion的映射
    IS_OFFICIAL_VERSION("is_official_version", "officialVersion"),
    // 配置is_distracted字段到distracted的映射
    IS_DISTRACTED("is_distracted", "distracted"),
    // 配置is_show_record字段到showRecord的映射
    IS_SHOW_RECORD("is_show_record", "showRecord"),
    // 配置is_agent_payment字段到agentPayment的映射
    IS_AGENT_PAYMENT("is_agent_payment", "agentPayment"),
    // 配置is_pharmacist字段到pharmacist的映射
    IS_PHARMACIST("is_pharmacist", "pharmacist"),
    // 配置is_audit字段到audit的映射
    IS_AUDIT("is_audit", "audit"),
    // 配置is_withdraw字段到withdraw的映射
    IS_WITHDRAW("is_withdraw", "withdraw"),
    // 配置is_inside字段到inside的映射
    IS_INSIDE("is_inside", "inside"),
    // 配置is_force_upgrade字段到forceUpgrade的映射
    IS_FORCE_UPGRADE("is_force_upgrade", "forceUpgrade"),
    // 配置is_unbind字段到unbind的映射
    IS_UNBIND("is_unbind", "unbind"),
    // 配置is_process字段到process的映射
    IS_PROCESS("is_process", "process"),
    // 配置is_default_card字段到defaultCard的映射
    IS_DEFAULT_CARD("is_default_card", "defaultCard"),
    // 配置is_receive字段到receive的映射
    IS_RECEIVE("is_receive", "receive"),
    // 配置is_chat_subject字段到chatSubject的映射
    IS_CHAT_SUBJECT("is_chat_subject", "chatSubject"),
    // 配置is_system字段到system的映射
    IS_SYSTEM("is_system", "system"),
    // 配置is_secondary_sales字段到secondarySales的映射
    IS_SECONDARY_SALES("is_secondary_sales", "secondarySales"),
    // 配置is_evaluate字段到evaluate的映射
    IS_EVALUATE("is_evaluate", "evaluate"),
    // 配置is_anonymous字段到anonymous的映射
    IS_ANONYMOUS("is_anonymous", "anonymous"),
    // 配置is_receive_quick_diagnosis字段到receiveQuickDiagnosis的映射
    IS_RECEIVE_QUICK_DIAGNOSIS("is_receive_quick_diagnosis", "receiveQuickDiagnosis"),
    // 配置is_receive_graphic_diagnosis字段到receiveGraphicDiagnosis的映射
    IS_RECEIVE_GRAPHIC_DIAGNOSIS("is_receive_graphic_diagnosis", "receiveGraphicDiagnosis"),
    // 配置is_receive_free_diagnosis字段到receiveFreeDiagnosis的映射
    IS_RECEIVE_FREE_DIAGNOSIS("is_receive_free_diagnosis", "receiveFreeDiagnosis"),
    // 配置is_visit字段到visit的映射
    IS_VISIT("is_visit", "visit");

    /**
     * 原始字段名
     */
    private String from;
    /**
     * 映射字段名
     */
    private String to;
}
