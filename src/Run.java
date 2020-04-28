import org.apache.rocketmq.client.exception.MQClientException;

/**
 * Created by 程浩 on 2020/4/19
 */
public class Run {
    public static void main(String[] args) {
        String topic = "CASE3";
        String nameSrvAddr = "127.0.0.1:9876";
        Listener listener = new Listener();
        try {
            listener.listening(topic, nameSrvAddr);
        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }

//    获取队列消息并解析





}
