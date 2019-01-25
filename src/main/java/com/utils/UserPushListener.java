package com.utils;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component("userPushListener")
public class UserPushListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			String jsonStr = textMessage.getText();
			if (jsonStr != null) {
				com.pojo.UserAq info = JSON.parseObject(jsonStr, com.pojo.UserAq.class);
				System.out.println("==============================接受到的用户信息 开始====================================");
                System.out.println(info.toString());
                System.out.println("==============================接受到的用户信息 结束====================================");
				WebsocketController.broadcast("user", jsonStr);
			}
		} catch (Exception e) {
		}
	}

}
