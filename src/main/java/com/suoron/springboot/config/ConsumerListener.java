package com.suoron.springboot.config;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerListener {
    @Value("${apache.rocketmq.consumer.consumerGroup}")
    private String consumerGroup;
    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        try {
            //广播模式消费
            consumer.setMessageModel(MessageModel.BROADCASTING);
            consumer.subscribe("TopicTest1", "*");

            // 如果是第一次启动，从队列头部开始消费
            // 如果不是第一次启动，从上次消费的位置继续消费(本机写入一个临时文件)
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            //lamda表达式
            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
                try {
                    for (MessageExt messageExt : list) {
                        String messageBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                        System.out.println("[Consumer] msgID(" + messageExt.getMsgId() + ") msgBody : " + messageBody);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //延迟消费
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                //消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
            System.out.println("[Consumer 已启动]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consumer;
    }
}

