/**
 * 
 */
package com.danny.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.danny.domain.proto.FruitProto;
import com.danny.domain.proto.FruitProto.Apple;
import com.danny.domain.proto.FruitProto.Fruit;

/**
 * @author 676002187@qq.com
 * @date 2018-05-18
 */
public class ProtoClient {
	private final static String HOST = "127.0.0.1";
	private final static int PORT = 5555;
	/**
	 * @param args
	 * @date 2018-05-18
	 */
	public static void main(String[] args) {
		try (Socket socket = new Socket(HOST, PORT);) {
			Fruit.Builder fruitBuilder = FruitProto.Fruit.newBuilder();
			Apple.Builder appleBuilder = FruitProto.Apple.newBuilder();
			appleBuilder.setName("apple").setColor("green").setWeight(150);
			fruitBuilder.addApple(appleBuilder).setCategory("shanxi apple");
			Fruit mobilePhone = fruitBuilder.build();
			byte[] messageBody = mobilePhone.toByteArray();
			int headerLen = 1;
			byte[] message = new byte[headerLen + messageBody.length];
			message[0] = (byte) messageBody.length;
			System.arraycopy(messageBody, 0, message, 1, messageBody.length);
			System.out.println("messageLen >> " + messageBody.length);
			socket.getOutputStream().write(message);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
