import java.util.LinkedList;

public class Calculator{
  public static double eval(String exp){
    LinkedList<String> stack = new LinkedList<>();
    String temp = "";
    for(int i = 0; i < exp.length(); i++){
      if(exp.charAt(i) == ' '){
        if(isOperator(temp)){
          stack.add(operate(temp, pop(stack), pop(stack)));
        }
        else{
          stack.add(temp);
        }
        temp = "";
      }
      else{
        temp += exp.charAt(i);
      }
    }
    double ans = Double.parseDouble(operate(temp, pop(stack), pop(stack)));
    return ans;
  }

  public static String operate(String operator, String num1, String num2){
    double numb1 = Double.parseDouble(num1), numb2 = Double.parseDouble(num2);
    if(operator.equals("+")){
      return "" + (numb2 + numb1);
    }
    else if(operator.equals("-")){
      return "" + (numb2 - numb1);
    }
    else if(operator.equals("*")){
      return "" + (numb2 * numb1);
    }
    else if(operator.equals("/")){
      return "" + (numb2 / numb1);
    }
    else{
      return "" + (numb2 % numb1);
    }
  }

  public static String pop(LinkedList<String> data){
    String ans = data.get(data.size() - 1);
    data.remove(data.size() - 1);
    return ans;
  }

  public static boolean isOperator(String s){
    return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%");
  }

  public static void main(String[] args) {
    System.out.println(eval("6 6 +"));
    System.out.println(eval("11 3 - 4 + 2.5 *"));
    System.out.println(eval("8 2 + 99 9 - * 2 + 9 -"));
  }
}
