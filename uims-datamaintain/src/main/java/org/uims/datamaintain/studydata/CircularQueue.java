package org.uims.datamaintain.studydata;

import java.util.Scanner;

/**
 *
 * 环形队列
 * @author kyrie
 * @since jdk1.8
 *
 */
public class CircularQueue {
    public static void main(String[] args) {
        System.out.println("测试环形队列");
        char key = ' ';
        CircularArray circularArray = new CircularArray(4);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    try{
                        circularArray.showQueue();
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    try{
                        circularArray.addQueue(value);
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g' :
                    try {
                        System.out.println(circularArray.getQueue());
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e' :
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出");
    }


}

class CircularArray{
    private int[] arr;
    //队列头
    private int front;
    //队列尾
    private int rear;
    //队列最大存储数
    private int maxSize;

    public CircularArray(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }
    /**
     * 判断队列是否满
     * @return
     */
    public boolean isFull(){
        return (rear+1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return rear == front;
    }

    /**
     * 给队列中添加数据
     * @param item
     */
    public void addQueue(int item){
        if (isFull()){
            throw new RuntimeException("队列空间已满");
        }
        arr[rear] = item;
        rear = (rear + 1) % maxSize;
    }

    /**
     * 获取队列中的值
     * @return
     */
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列中无数据");
        }
        int value = arr[front];
        front = ( front + 1 ) % maxSize;
        return value;
    }

    public void showQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列中无数据");
        }

        for (int i=front;i<front+getSize();i++){
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    public int getSize(){
        return ( rear + maxSize - front ) % maxSize;
    }

}
