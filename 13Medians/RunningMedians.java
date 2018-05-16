public class RunningMedian{
  private MyHeap<Double> minHeap;
  private MyHeap<Double> maxHeap;

  @SuppressWarnings("unchecked")
  public RunningMedian(double[] a){
    minHeap = new MyHeap<>();
    maxHeap = new MyHeap<>(false);
    for(int i = 0; i < a.length; i++){
      add(a[i]);
    }
  }

  public void add(Double a){
    if(minHeap.size() == 0 && maxHeap.size() == 0){
      minHeap.add(a);
    }
    else{
      if(a < minHeap.peek()){
        minHeap.add(a);
        if(minHeap.size() - maxHeap.size() == 2){
          maxHeap.add(minHeap.remove());
        }
      }
      else{
        maxHeap.add(a);
        if(maxHeap.size() - minHeap.size() == 2){
          minHeap.add(maxHeap.remove());
        }
      }
    }
  //  System.out.println("minHeap: " + minHeap);
  //  System.out.println("maxHeap: " + maxHeap);
  }

  public Double getMedian(){
    Double ans = 0.0;
    int numCounted = 0;
    if(minHeap.size() >= maxHeap.size()){
      ans+= minHeap.peek();
      numCounted++;
    }
    if(maxHeap.size() >= minHeap.size()){
      ans+= maxHeap.peek();
      numCounted++;
    }
    return ans / numCounted;
  }

  public static void main(String[] args){
    double[] data = {1.0,3.0,5.0,4.0,2.0};
    RunningMedian a = new RunningMedian(data);
    System.out.println(a.getMedian()); //3
//    System.out.println(a.minHeap);
//    System.out.println(a.maxHeap);
    a.add(6.0);
    System.out.println(a.getMedian()); //3.5
    a.add(1.0);
    a.add(1.0);
    System.out.println(a.getMedian());//2.5
  }
}
