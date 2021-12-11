public class CircularQueue {
    
    private int[] data;
    private int front, rear, count;

    // constructor with the array size as a parameter
    public CircularQueue(int k){

        this.front = -1;
        this.rear = -1;
        this.data = new int[k];
        this.count = 0;
    }

    
    // returns the array data
    public int[] getdata(){
        return data;
    }


    // adds the specified element to the rear of the queue, expanding
    // the capacity of the queue array if necessary
    public void enqueue(int element){

        // below we check if the queue is full, and if so we need to expand it
        if(size() == data.length - 1){
            expandCapacity();
        }else if (isEmpty()){
            front++;
        }
        rear = (rear + 1) % data.length;
        data[rear] = element;
        count++;
    }


    //returns the number of elements currently in the queue 
    public int size(){
        int numItems = (data.length - front + rear + 1) % data.length;
        return numItems;
    }


    // checks if the queue is empty
    public boolean isEmpty(){
        boolean flag = (front == -1);
        return flag;
    }


    //removes the element at the front of the queue and returns a reference to it
    public int dequeue(){
        
        if(isEmpty()){
            System.out.println("The queue is empty!");
        }
        int temp = data[front];
        data[front] = 0;
        if(front == rear){
            front = -1;
            rear = -1;
        }else{
            front = (front + 1) % data.length;
        }
        count--;
        return temp; 
    }


    // creates a new array to store the contents of the queue with twice the capacity of the old one
    public void expandCapacity(){

        int[] doubleQueue = new int[data.length * 2];

        // transfer values to new array
        for(int j = 0; j < count; j++){
            doubleQueue[j] = data[front];
            front = (front + 1) % data.length;
        }

        front = 0; 
        rear = size() - 1;
        data = doubleQueue;
    }


    // returns a string representation of the table of the queue
    public String queuetoString()
    {
        String s = "";
        int i;
        for(i = 0; i < data.length; i++){
            if(data[i] == 0){
                s += "-\n";
            }else{
                s += Integer.toString(data[i]) + "\n";
            }
        }
        return s;
    }


    public static void main(String[] args){

        CircularQueue q = new CircularQueue(5);
        
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);
        q.enqueue(60);
    
        System.out.println("This is my queue:");
        System.out.println(q.queuetoString());
        System.out.println("The size of the queue is:" + q.size());
        System.out.println("the rear is:" + q.rear);
        System.out.println("the front is:" + q.front);

        q.dequeue();
        q.dequeue();
        q.enqueue(70);
        q.enqueue(80);
        q.enqueue(90);
        q.enqueue(100);
        q.enqueue(110);
        q.dequeue();
        q.dequeue();
        q.enqueue(120);

        System.out.println("This is my queue:");
        System.out.println(q.queuetoString());
        System.out.println("The size of the queue is:" + q.size());
        System.out.println("the rear is:" + q.rear);
        System.out.println("the front is:" + q.front);
    }
}





