//**********************************************
//文件名：UDPServer
//运行空间：java/dc/sockettest
//功能：UDP服务端
//作者：丁源 18030100116
//生成日期：21:52 2021/3/16
//修改日志：（日期：修改信息）



//*********************************************
package dc.sockettest;

import java.net.*;
import java.io.*;

public class UDPServer{
	public static void main(String args[]){
		DatagramSocket aSocket = null;
		int serverPort = 6789;
		int id = 1;
		try{
			aSocket = new DatagramSocket(serverPort);
			byte[] buffer = new byte[1000];
			//byte[] buffer = new byte[1000];
			while(true){
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);
				UdpServerThread thread = new UdpServerThread(aSocket,id,request);
				thread.start();

				//InetAddress address=aSocket.getInetAddress();
				//System.out.println("Current client id:"+address.getHostAddress());

				id++;
			}
		} catch (SocketException e){
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null) aSocket.close();
		}
	}
}
