/**
 * Created by 程浩 on 2020/4/10
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.sun.xml.internal.bind.api.impl.NameConverter;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import javax.xml.crypto.Data;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Listener {
    public String listening(String topic, String nameSrvAddr) throws MQClientException {
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
                        JSONObject jsonObject = JSONArray.parseObject(caseData, JSONObject.class, Feature.OrderedField);//将字符串转化成json对象
                        JSONObject jsonObject2 = JSONObject.parseObject(jsonObject.get(caseName).toString(), JSONObject.class, Feature.OrderedField);
                        LinkedHashMap<String, String> jsonMap = JSON.parseObject(jsonObject2.toString(), new TypeReference<LinkedHashMap<String, String>>() {
                        }, Feature.OrderedField);
                        for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
                            System.out.println(entry.getKey());
                            JSONObject jsonObject3 = JSONObject.parseObject(entry.getValue(), JSONObject.class, Feature.OrderedField);
                            LinkedHashMap<String, String> jsonMap2 = JSON.parseObject(jsonObject3.toString(), new TypeReference<LinkedHashMap<String, String>>() {
                            }, Feature.OrderedField);
                            for (Map.Entry<String, String> entry2 : jsonMap2.entrySet()) {
                                System.out.println(entry2.getKey() + "" + entry2.getValue());
                            }
                        }
                        Run runner = new Run();
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                标记消息处理状态为成功
            }
        });
        // 启动消费者
        consumer.start();
        return "";
    }

    public static void main(String[] args) {
        String topic = "CASE";
        String nameSrvAddr = "127.0.0.1:9876";
        Listener listener = new Listener();
        try {
            listener.listening(topic, nameSrvAddr);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
