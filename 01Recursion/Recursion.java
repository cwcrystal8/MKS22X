public class Recursion{
  public int fact(int n){
    if (n < 0){
      throw new IllegalArgumentException();
    }
    if (n == 0){
      return 1;
    }
    else{
      return n * fact(n-1);
    }
  }

  public int fib(int n){
    return fibHelper(n, 0, 1);
  }

  public int fibHelper(int n, int first, int second){
    if (n < 0){
      throw new IllegalArgumentException();
    }
    else if (n == 0){
      return first;
    }
    else {
      return fibHelper(n - 1, first + second, first);
    }
  }

  public double sqrt(double n){
    return sqrtHelper(n, n / 2);
  }

  public double sqrtHelper(double n, double guess){
    if (n < 0){
      throw new IllegalArgumentException();
    }
    else if(n == 0){
      return 0;
    }
    else if(((n - (guess * guess)) / n) < 0.001 && (((n - (guess * guess)) / n) >= 0) ||
    ((n - (guess * guess)) / n) > -0.001 && (((n - (guess * guess)) / n) < 0)) {
      return guess;
    }
    else{
      return sqrtHelper(n, (n / guess + guess) / 2);
    }
  }

  public static void main(String[] args){
    Recursion a = new Recursion();
    System.out.println(a.fact(0));
    System.out.println(a.fact(1));
    System.out.println(a.fact(2));
    System.out.println(a.fact(3));
    System.out.println(a.fact(4));
    System.out.println(a.fib(0));
    System.out.println(a.fib(1));
    System.out.println(a.fib(2));
    System.out.println(a.fib(3));
    System.out.println(a.fib(4));
    System.out.println(a.fib(5));
    System.out.println(a.fib(6));
    System.out.println(a.fib(7));
    System.out.println(a.fib(8));
    System.out.println(a.fib(9));
    System.out.println(a.sqrt(100.0));
    System.out.println(a.sqrt(1.0));
    System.out.println(a.sqrt(2.0));
    System.out.println(a.sqrt(4.0));
    System.out.println(a.sqrt(3.0));
    System.out.println(a.sqrt(64.0));
  }
}
