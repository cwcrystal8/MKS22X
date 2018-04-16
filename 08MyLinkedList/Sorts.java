import java.util.*;
import java.util.Arrays;

public class Sorts{
  public static void radixsort(MyLinkedListImproved<Integer> data){
    if(data.size() > 1){
      Integer max = data.max();

      MyLinkedListImproved<Integer> negatives = new MyLinkedListImproved<>(), positives = new MyLinkedListImproved<>();
      for(Integer x: data){
        if(x < 0){
          negatives.add(-1 * x);
        }
        else{
          positives.add(x);
        }
      }


      for(int i = 0; i < (int)Math.log10(max) + 3; i++){
        @SuppressWarnings("unchecked") MyLinkedListImproved<Integer>[] bucket = new MyLinkedListImproved[10];
        for(int x = 0; x < 10; x++){
          bucket[x] = new MyLinkedListImproved<Integer>();
        }
        for(int j = 0; j < positives.size(); j++){
          int n = positives.get(j), digit = (n / (int)Math.pow(10,i)) % 10;
          bucket[digit].add(n);
        }
        positives.clear();
        for(int k = 0; k < 10; k++){
          positives.extend(bucket[k]);
        }
      }

      if(negatives.size() > 0){
        max = negatives.max();
        for(int i = 0; i < (int)Math.log10(max) + 3; i++){
          @SuppressWarnings("unchecked") MyLinkedListImproved<Integer>[] bucket = new MyLinkedListImproved[10];
          for(int x = 0; x < 10; x++){
            bucket[x] = new MyLinkedListImproved<Integer>();
          }
          for(int j = 0; j < negatives.size(); j++){
            int n = negatives.get(j), digit = (n / (int)Math.pow(10,i)) % 10;
            bucket[digit].add(n);
          }
          negatives.clear();
          for(int k = 0; k < 10; k++){
            negatives.extend(bucket[k]);
          }
        }
      }

      data.clear();

      for(Integer x: negatives){
        data.add(0, -1 * x);
      }
      data.extend(positives);
    }
  }

  public static void main(String[] args) {
    //-----------SORTING POSITIVES-----------
    System.out.println("TESTING ON POSITIVE INTEGERS ONLY:");
    MyLinkedListImproved<Integer> data = new MyLinkedListImproved<>();
    int[] correctData = new int[1000];

    //Create MyLinkedListImproved and array with random integers
    for(int i = 0; i < 1000; i++){
      int temp = (int)(Math.random() * 1000);
      data.add(temp);
      correctData[i] = temp;
    }

    //Sorts data and times the sort
    long end,start = System.currentTimeMillis();
    radixsort(data);
    end = System.currentTimeMillis();

    //Sorts the array
    Arrays.sort(correctData);
    System.out.println("Sort completed in " + (end - start) + " seconds");

    //Checks if data is properly sorted
    boolean hasError = false;
    int index = 0;
    for(Integer x: data){
      if(!(x.equals(correctData[index]))){
        System.out.println("THERE IS AN ERROR");
        System.out.println("Index of error: " + index);
        hasError = true;
      }
      index++;
    }

    if(!(hasError)){
      System.out.println("Your LinkedList with all positive integers is properly sorted.");
    }
    else{
      System.out.println(data);
    }
    System.out.println("\n");


    //-----------SORTING NEGATIVES-----------
    System.out.println("TESTING ON NEGATIVE INTEGERS ONLY:");
    data.clear();
    correctData = new int[1000];

    //Create MyLinkedListImproved and array with random integers
    for(int i = 0; i < 1000; i++){
      int temp = (int)(Math.random() * 1000);
      temp *= -1;
      data.add(temp);
      correctData[i] = temp;
    }

    //Sorts data and times the sort
    start = System.currentTimeMillis();
    radixsort(data);
    end = System.currentTimeMillis();

    //Sorts the array
    Arrays.sort(correctData);
    System.out.println("Sort completed in " + (end - start) + " seconds");

    //Checks if data is properly sorted
    hasError = false;
    index = 0;
    for(Integer x: data){
      if(!(x.equals(correctData[index]))){
        System.out.println("THERE IS AN ERROR");
        System.out.println("Index of error: " + index);
        hasError = true;
      }
      index++;
    }

    if(!(hasError)){
      System.out.println("Your LinkedList with all negative numbers is properly sorted.");
    }
    else{
      System.out.println(data);
    }
    System.out.println("\n");


    //-----------SORTING POSITIVES AND NEGATIVES-----------
    System.out.println("TESTING ON POSITIVE AND NEGATIVE INTEGERS:");
    data.clear();
    correctData = new int[1000];

    //Create MyLinkedListImproved and array with random integers
    for(int i = 0; i < 1000; i++){
      int temp = (int)(Math.random() * 1000);
      if((int)(Math.random() * 1000) % 2 == 0){
        temp *= -1;
      }
      data.add(temp);
      correctData[i] = temp;
    }

    //Sorts data and times the sort
    start = System.currentTimeMillis();
    radixsort(data);
    end = System.currentTimeMillis();

    //Sorts the array
    Arrays.sort(correctData);
    System.out.println("Sort completed in " + (end - start) + " seconds");

    //Checks if data is properly sorted
    hasError = false;
    index = 0;
    for(Integer x: data){
      if(!(x.equals(correctData[index]))){
        System.out.println("THERE IS AN ERROR");
        System.out.println("Index of error: " + index);
        hasError = true;
      }
      index++;
    }

    if(!(hasError)){
      System.out.println("Your LinkedList with positive and negative integers is properly sorted.");
    }
    else{
      System.out.println(data);
    }
    System.out.println("\n");


    //-----------SORTING EMPTY LISTS-----------
    System.out.println("SORTING ON EMPTY LISTS");
    data.clear();
    correctData = new int[0];

    start = System.currentTimeMillis();
    radixsort(data);
    end = System.currentTimeMillis();

    //Sorts the array
    Arrays.sort(correctData);
    System.out.println("Sort completed in " + (end - start) + " seconds");

    //Checks if data is properly sorted
    hasError = false;
    index = 0;
    for(Integer x: data){
      if(!(x.equals(correctData[index]))){
        System.out.println("THERE IS AN ERROR");
        System.out.println("Index of error: " + index);
        hasError = true;
      }
      index++;
    }

    if(!(hasError)){
      System.out.println("Your empty LinkedList is properly sorted.");
    }
    else{
      System.out.println(data);
    }
    System.out.println("\n");


    //-----------SORTING POSITIVE ONE-ELEMENT LISTS-----------
    System.out.println("SORTING POSITIVE ONE-ELEMENT LISTS");
    data.clear();
    correctData = new int[1];

    int temp = (int)(Math.random() * 1000);
    data.add(temp);
    correctData[0] = temp;

    start = System.currentTimeMillis();
    radixsort(data);
    end = System.currentTimeMillis();

    //Sorts the array
    Arrays.sort(correctData);
    System.out.println("Sort completed in " + (end - start) + " seconds");

    //Checks if data is properly sorted
    hasError = false;
    index = 0;
    for(Integer x: data){
      if(!(x.equals(correctData[index]))){
        System.out.println("THERE IS AN ERROR");
        System.out.println("Index of error: " + index);
        hasError = true;
      }
      index++;
    }

    if(!(hasError)){
      System.out.println("Your LinkedList with one positive element is properly sorted.");
    }
    else{
      System.out.println(data);
    }
    System.out.println("\n");


    //-----------SORTING NEGATIVE ONE-ELEMENT LISTS-----------
    System.out.println("SORTING NEGATIVE ONE-ELEMENT LISTS");
    data.clear();
    correctData = new int[1];

    temp = (int)(Math.random() * 1000) * -1;
    data.add(temp);
    correctData[0] = temp;

    start = System.currentTimeMillis();
    radixsort(data);
    end = System.currentTimeMillis();

    //Sorts the array
    Arrays.sort(correctData);
    System.out.println("Sort completed in " + (end - start) + " seconds");

    //Checks if data is properly sorted
    hasError = false;
    index = 0;
    for(Integer x: data){
      if(!(x.equals(correctData[index]))){
        System.out.println("THERE IS AN ERROR");
        System.out.println("Index of error: " + index);
        hasError = true;
      }
      index++;
    }

    if(!(hasError)){
      System.out.println("Your LinkedList with one negative element is properly sorted.");
    }
    else{
      System.out.println(data);
    }
  }
} 
