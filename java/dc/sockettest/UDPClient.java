//**********************************************
//文件名：UDPClient
//运行空间：java/dc/sockettest
//功能：UDP客户端
//作者：丁源 18030100116
//生成日期：21:52 2021/3/16
//修改日志：（日期：修改信息）



//*********************************************
package dc.sockettest;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class UDPClient{
public static void main(String args[]){
// args give message contents and server hostname
    DatagramSocket aSocket = null;
	try {
		aSocket = new DatagramSocket();

		byte[] m = "Hello world!".getBytes(StandardCharsets.UTF_8);

		InetAddress aHost = InetAddress.getByName("127.0.0.1");
		int serverPort = 6789;

		//packet包对象，分别封装了 包数据、服务器地址：（本机）127.0.0.1、端口号
		DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
		aSocket.send(request);

		byte[] buffer = new byte[1000];
		DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
		aSocket.receive(reply);
		System.out.println("Reply: " + new String(reply.getData()));
	} catch (SocketException e){
		System.out.println("Socket: " + e.getMessage());
    } catch (IOException e){
		System.out.println("IO: " + e.getMessage());
    } finally { 
	    if(aSocket != null) aSocket.close();
	}
}
}