import java.util.Comparator;

public class MinPriorityQueue<T> {
    private T[] heap;
    private int size;
    private Comparator<T> cmp;

    public int getSize() {
        return size;
    }

    MinPriorityQueue(int capacity, Comparator<T> cmp){
        if(capacity < 1) throw new IllegalArgumentException();
        this.heap = (T[]) new Object[capacity + 1];
        this.size = 0;
        this.cmp = cmp;
    }
    public int size(){
        return size;
    }
    T Min(){
        System.out.println("Max value of the heap is : " + heap[1].toString());
        return heap[1];
    }
    T getMin(){

        if(size == 0) throw new IllegalArgumentException();
        T ob = heap[1];
        if(size>1) heap[1] = heap[size];
        heap[size--] = null;
        sink(1);
        // System.out.println("Removing song : " + ob);
        return ob;
    }
    void removeLast(int c){
        remove(heap[c]);
    }

    private void resize(){
        //Casting into data type T[] from object array
        T[] temp = (T[]) new Object[heap.length * 2];
        for(int i = 0; i < heap.length; i++){
            temp[i] = heap [i];
        }
        //change of pointer to new array
        heap = temp;
    }
    private void swim(int i){
        while(i>1){
            int p = i/2;
            int result = cmp.compare(heap[i], heap[p]);
            if (result <= 0)return;
            swap(i, p);
            i = p;
        }
    }
    void insert(T ob){
        if(ob == null)
            throw new IllegalArgumentException();
        if(size == heap.length - 1)
            throw new IllegalArgumentException();
        heap[++size] = ob;
        swim(size);
        //System.out.println("Added "+ ob.toString()+ " we thought you might like.");
        if(heap.length == getSize()){
            //creates a duplicate heap with double size.
            resize();
        }
    }


    T remove(T ob){
        if(ob == null)throw new IllegalArgumentException();
        if(size == heap.length - 1) throw new IllegalArgumentException();
        heap[--size] = ob;
        sink(size);
        return ob;
    }

    private void sink(int i){
        int l = 2*i;
        int r = l + 1;
        int max = l;
        while(l<=size){
            if(r <=size){
                max = cmp.compare(heap[l],heap[r]) < 0 ? r:l;
            }
            if(cmp.compare(heap[i], heap[max])>= 0)return;
            swap(i, max);
            i = max;
            l = 2*i;
            r = l+1;
            max = l;
        }
    }

    void printHeap() {
        for (int i=1; i<=size; i++){
            System.out.print(heap[i]+ " ");
        }
        System.out.println();
    }
    private void swap(int i,int j){
        T t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }
    T getLast(){
        return heap[size-1];
    }
    public boolean isEmpty(){
        return size == 0;
    }

}
