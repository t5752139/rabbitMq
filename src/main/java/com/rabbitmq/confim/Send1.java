package com.rabbitmq.confim;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.ConnertionUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * confim模式   最大的好处在于异步   :异步模式  只管往里面发
 *  如果队列已经声明了事务模式,就不能再改成confim模式
 */
public class Send1 {
    private static final String QUEUE = "tets_name_confim_01";

    /**
     * 异步模式
     * @param args
     * @throws IOException
     * @throws TimeoutException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取工厂连接
        Connection connection = ConnertionUtils.getConnection();
        //从连接里面获取一个通道
        Channel channel = connection.createChannel();
        //创建声明队列
        channel.queueDeclare(QUEUE, false, false, false, null);

        //开启confim模式 将通道设置为confim模式
        channel.confirmSelect();
        //集合  定义未确认的消息
        final SortedSet<Long> confimset = Collections.synchronizedSortedSet(new TreeSet<Long>());
        //监听
        channel.addConfirmListener(new ConfirmListener() {

            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
            if(multiple){
                System.out.println(11);
                confimset.headSet(deliveryTag+1).clear();
            }else {
                System.out.println(222);
                confimset.remove(deliveryTag);
            }
            }

            //没有问题的ack

            /**
             * 没回调成功就会调用此方法
             *
             * @param deliveryTag
             * @param multiple
             * @throws IOException
             */
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println(333);
                    confimset.headSet(deliveryTag + 1).clear();
                }else {
                    System.out.println(4444);
                    confimset.remove(deliveryTag);
                }
            }
        });
        String msg = "rabbit mq";
        while (true){
            long nextPublishSeqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("",QUEUE,null,msg.getBytes());
            confimset.add(nextPublishSeqNo);


        }







    }


}
