//**********************************************
//文件名：ServerTask
//运行空间：java/dc/sockettest
//功能：EchoServer通信线程
//作者：丁源 18030100116
//生成日期：21:52 2021/3/16
//修改日志：（日期：修改信息）



//*********************************************

package dc.sockettest;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerTask implements Runnable {
    private Socket clientSocket;

    public ServerTask(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        InputStream inStream = null;
        OutputStream outStream = null;
        BufferedReader in = null;
        PrintWriter out = null;

        String line = null;

        try {
            inStream = clientSocket.getInputStream();
            outStream = clientSocket.getOutputStream();
            in = new BufferedReader(new InputStreamReader(inStream));
            out = new PrintWriter(outStream);

            while(true) {
                line = in.readLine();
                System.out.println("Get massage from " + clientSocket.getInetAddress().toString() + " :" + line);

                out.println(line);
                out.flush();
                if (line.equals("bye"))
                    break;
            }

            }catch (IOException e) {
            System.out.println("Error:"+e);
        }
        System.out.println("A task has been done!");
    }
}
