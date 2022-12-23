import java.util.ArrayList;
import java.util.List;

public class Converter extends ArrayStack {
    private String infix;

    public static List<String> parse(char[] input) {
        List<String> parsed = new ArrayList<String>();
        for (int i = 0; i < input.length; ++i) {
            char c = input[i];
            if (Character.isDigit(c)) {
                String number = input[i] + "";
                for (int j = i + 1; j < input.length; ++j) {
                    if (Character.isDigit(input[j])) {
                        number += input[j];
                        i = j;
                    } else {
                        break;
                    }
                }
                parsed.add(number);
            } else if (c == '*' || c == '/' ||
                    c == '+' || c == '^' ||
                    c == '-' || c == '(' || c == ')') {
                parsed.add(c + "");
            }
        }
        return parsed;
    }

    public Converter(String infix) {
        this.infix = infix;
    }

    public static String toPostFix(String infix) {
        char[] tokenized = new char[infix.length()];
        Stack<Object> myStack = new ArrayStack<>();
        String equation = "";
        for (int i = 0; i < infix.length(); i++) {
            tokenized[i] = infix.charAt(i);
        }
        List<String> thing = parse(tokenized);
        for (String s : thing) {
            if (Character.isDigit((s.charAt(0)))) {
                equation += s + " ";
            } else if ((s.charAt(0)) == '(') {
                myStack.push("(");
            } else if ((s.charAt(0)) == ')') {
                while (myStack.top() != "(") {
                    equation += myStack.pop() + " ";
                }
                myStack.pop();
            } else if ((s.charAt(0)) == '^') {
                myStack.push(s);
            } else if ((s.charAt(0)) == '*' || (s.charAt(0)) == '/') {
                while (myStack.top() != null && (myStack.top().equals("^") || myStack.top().equals("*") || myStack.top().equals("/"))) {
                    equation += myStack.pop() + " ";
                }

                myStack.push(s);
            } else if ((s.charAt(0)) == '+' || (s.charAt(0)) == '-') {
                while (myStack.top() != null && (myStack.top().equals("^") || myStack.top().equals("*") || myStack.top().equals("/") || myStack.top().equals("+") || myStack.top().equals("-"))) {
                    equation += myStack.pop() + " ";
                }

                myStack.push(s);
            }
        }
        while (!myStack.isEmpty()) {
            equation += myStack.pop() + " ";
        }
        equation = equation.substring(0, equation.length() - 1);
        return equation;
    }
}
