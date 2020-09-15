/**
 * Created by 程浩 on 2020/4/10
 */

import bsh.EvalError;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Listener {
    public void listening(String topic, String nameSrvAddr) throws MQClientException {
        // 创建消费者对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerGroupName");
        // 设置服务器地址
        consumer.setNamesrvAddr(nameSrvAddr);
        // 订阅指定主题
        consumer.subscribe(topic, "*");
        // 注册消息监听事件
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                if (list != null) {
                    for (MessageExt ext : list) {
                        String caseData = new String(ext.getBody(), StandardCharsets.UTF_8);
                        String caseName = ext.getKeys();
                        System.out.println("********************************************************************************");
                        System.out.println(new Date());
                        System.out.println(caseName);
                        System.out.println("********************************************************************************");
                        try {
                            Run.run(caseData, caseName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (EvalError evalError) {
                            evalError.printStackTrace();
                        }
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                标记消息处理状态为成功
            }
        });
        // 启动消费者
        consumer.start();


    }

}
