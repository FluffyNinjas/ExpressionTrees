import java.util.Scanner;
import java.util.Stack;

import static java.lang.Character.isDigit;

class Node {
    public Object element;
    public Node leftChild;
    public Node rightChild;

    public Node (Object o) {
        this (o, null, null);
    }
    public Node (Object o, Node l, Node r) {
        element = o;
        leftChild = l;
        rightChild = r;
    }
    public String toString() {
        return element.toString();
    }
}
public class Main extends Converter{
    public Main(String infix) {
        super(infix);
    }

    public static boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
    public static Node convert(String postfix){
        Stack<Node> list = new Stack<>();
        Node x = new Node(5);
        Node y = new Node(6);
        Node z = new Node(7);
        String r = "";
        for(int i = 0;i<postfix.length();i++){
            if(postfix.charAt(i) == ' '){
                continue;
            }
            else if(!isOperator(postfix.charAt(i))){
                r = "";
                for(int d = i;d<postfix.length();d++){
                    if(isDigit(postfix.charAt(d))==true){
                        r+=postfix.charAt(d);
                    }
                    else if(postfix.charAt(d)== ' '){
                        break;
                    }
                    i+=1;
                }
                x = new Node(r);
                list.push(x);
            }
            else{
                y = list.pop();
                z = list.pop();
                x = new Node(postfix.charAt(i),z,y);
                list.push(x);
            }
        }
        return list.pop();
    }
    public static void prefix(Node c){
        if(c == null){
            return;
        }
        System.out.print(c.element);
        prefix(c.leftChild);
        prefix(c.rightChild);
    }
    public static void inorder(Node c){
        if(c == null){
            return;
        }
        System.out.print("(");
        inorder(c.leftChild);
        System.out.print(c.element);
        inorder(c.rightChild);
        System.out.print(")");

    }
    public static void postfix(Node c){
        if(c == null){
            return;
        }
        postfix(c.leftChild);
        postfix(c.rightChild);
        System.out.print(c.element);
    }
    public static void main(String[] args) {
        Scanner jt = new Scanner(System.in);
        System.out.println("Type your expression: ");
        String s = jt.nextLine();
        String x = toPostFix(s);
        Node e = convert(x);
        System.out.println(x);
        inorder(e);
        System.out.println("");
        postfix(e);
        System.out.println("");
        prefix(e);
    }
}
