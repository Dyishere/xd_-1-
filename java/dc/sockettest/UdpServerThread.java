//**********************************************
//文件名：ServerTask
//运行空间：java/dc/sockettest
//功能：UDPServer通信线程
//作者：丁源 18030100116
//生成日期：21:52 2021/3/16
//修改日志：（日期：修改信息）



//*********************************************
package dc.sockettest;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServerThread extends Thread{

    private DatagramSocket socket = null;
    private DatagramPacket request = null;
    private int ID = 0;

    public UdpServerThread(DatagramSocket socket,int id,DatagramPacket request){
        this.socket = socket;
        this.ID = id;
        this.request = request;
    }

    public void run(){
        System.out.println("Thread "+this.ID+" is running.");
        try{

            DatagramPacket reply = new DatagramPacket(request.getData(),
                    request.getLength(), request.getAddress(), request.getPort());
            socket.send(reply);
            System.out.println("reply to "+request.getAddress().toString()+" with:"+request.getData().toString());

        }catch(Exception e){
            System.out.println("Error exception thread:"+ID);
            System.out.println("massage:"+e);
        }
//        finally {
//            if(socket!=null)
//                socket.close();
//        }

    }
}