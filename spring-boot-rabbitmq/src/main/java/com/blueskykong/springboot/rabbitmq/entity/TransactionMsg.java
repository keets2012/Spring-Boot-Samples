package com.blueskykong.springboot.rabbitmq.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author keets
 */
@Data
@NoArgsConstructor
public class TransactionMsg {

    /**
     * 用于消息的追溯
     */
    private String groupId;

    /**
     * 子任务id
     */
    @NonNull
    private String subTaskId;

    /**
     * 源服务，即调用发起方
     */
    private String source;

    /**
     * 目标方
     */
    private String target;

    /**
     * 执行的方法，适配成枚举
     */
    private String method;

    /**
     * 参数，即要传递的内容，可以为null
     */
    private byte[] args;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 操作结果信息
     */
    private String message;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 是否消费，默认为否
     *
     */
    private int consumed = 0;



}
