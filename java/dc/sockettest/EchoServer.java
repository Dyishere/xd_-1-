//**********************************************
//文件名：EchoServer
//运行空间：java/dc/sockettest
//功能：Echo服务器端
//作者：丁源 18030100116
//生成日期：21:52 2021/3/16
//修改日志：（日期：修改信息）



//*********************************************
package dc.sockettest;

import java.io.*;
import java.net.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EchoServer {
	 public static void main(String[] args) throws Exception {
		Socket clientSocket = null;
		ServerSocket listenSocket = new ServerSocket(8189); 
				
		System.out.println("Server listening at 8189");
//		clientSocket = listenSocket.accept();
//		System.out.println("Accepted connection from client");

		ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

		try {
			while (true) {
				clientSocket = listenSocket.accept();
				System.out.println("Accepted connection from client");
				ServerTask task = new ServerTask(clientSocket);
				pool.execute(task);

				System.out.println("////////////////////");
            	System.out.println("The number of threads in the ThreadPool:"+pool.getPoolSize());
            	System.out.println("The number of tasks in the Queue:" + pool.getQueue().size());
            	System.out.println("The number of tasks completed:"+pool.getCompletedTaskCount());
            	System.out.println("////////////////////");
			}
		}catch (SocketException e){
			System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){
			System.out.println("Socket: " + e.getMessage());
		}
//		InputStream inStream = clientSocket.getInputStream();
//		OutputStream outStream = clientSocket.getOutputStream();
//		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
//		PrintWriter out = new PrintWriter(outStream);
//
//		String line = null;
//		while((line=in.readLine())!=null) {
//			System.out.println("Message from client:" + line);
//			out.println(line);
//			out.flush();
//		}
		clientSocket.close();
		listenSocket.close();
		System.out.println("Good Bye!");
	 }
}