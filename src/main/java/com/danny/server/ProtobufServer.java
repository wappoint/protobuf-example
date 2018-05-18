/**
 * 
 */
package com.danny.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.danny.domain.proto.FruitProto;
import com.danny.domain.proto.FruitProto.Fruit;

/**
 * @author 676002187@qq.com
 * @date 2018-05-18
 */
public class ProtobufServer {

	private static final int PORT = 5555;
	/**
	 * @param args
	 * @date 2018-05-18
	 */
	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(PORT);
				Socket socket = serverSocket.accept();) {
			byte[] message = new byte[256];
			socket.getInputStream().read(message);
			int messageBodyLen = message[0];
			System.out.println("messageBodyLen >> " + messageBodyLen);
			byte[] messageBody = new byte[messageBodyLen];
			System.arraycopy(message, 1, messageBody, 0, messageBodyLen);
			Fruit fruit = FruitProto.Fruit.parseFrom(messageBody);
			System.out.println("received message : ");
			System.out.println(fruit.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
