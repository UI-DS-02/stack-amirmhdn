import java.util.EmptyStackException;
import java.util.Scanner;

public class caculator {
    public static void main(String[] args) throws Exception {

        caculator caculator = new caculator();
        System.out.println("Hello enter numbers :");
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        while (!caculator.isMatched(string))
        {

            System.out.println("Hello enter numbers :");
            string = scanner.nextLine();
        }
        String u = toPostfix(string);
        System.out.println(calc(u));

    }
    private static int calc(String string)
    {
        Stack<Integer> integerStack = new Stack<>();

        for(int i = 0 ; string.length()>i ; i++)
        {
            char c = string.charAt(i);
            int x = 0 ;
            int y =0 ;
            int z = 0 ;
            if(Character.isDigit(c))
            {
                int t = Character.getNumericValue(c);
                integerStack.push(t);

            } else if (c=='+') {
                x= integerStack.pop();
                y=integerStack.pop();
                z=x+y;
                integerStack.push(z);

            }
            else if(c=='-') {
                x = integerStack.pop();
                y = integerStack.pop();
                z = x - y;
                integerStack.push(z);

            }
            else if(c == '*'){
                x = integerStack.pop();
                y = integerStack.pop();
                z = x*y;
                integerStack.push(z);
            }
            else if(c == '/'){
                x = integerStack.pop();
                y = integerStack.pop();
                z = x/y;
                integerStack.push(z);
            }


        }
        int a =integerStack.pop() ;

        return a;
    }
    private static String toPostfix(String infix)
//converts an infix expression to postfix
    {
        Stack<Character> operators = new Stack<>();
        char symbol;
        String postfix = "";
        for(int i=0;i<infix.length();++i)
//while there is input to be read
        {
            symbol = infix.charAt(i);
//if it's an operand, add it to the string
            if (Character.isDigit(symbol))
                postfix = postfix + symbol;
            else if (symbol=='(')
//push (
            {
                operators.push(symbol);
            }
            else if (symbol==')')
//push everything back to (
            {
                while (operators.peek() != '(')
                {
                    postfix = postfix + operators.pop();
                }
                operators.pop();        //remove '('
            }
            else
//print operators occurring before it that have greater precedence
            {
                while (!operators.isEmpty() && !(operators.peek()=='(') && prec(symbol) <= prec(operators.peek()))
                    postfix = postfix + operators.pop();
                operators.push(symbol);
            }
        }
        while (!operators.isEmpty())
            postfix = postfix + operators.pop();
        return postfix;
    }
    static int prec(char x)
    {
        if (x == '+' || x == '-')
            return 1;
        if (x == '*' || x == '/' || x == '%')
            return 2;
        return 0;
    }



    public  Boolean isMatched (String e) throws Exception
    {
        final String opening = "({[";
        final String closing = ")}]";
        Stack<Character> buffer = new Stack<>();
        char r = 0;
        for (char i: e.toCharArray()) {
            if(opening.indexOf(i)!=-1)
            {
                buffer.push(i);
            } else if (closing.indexOf(i)!=-1) {
                if(buffer.isEmpty())
                {
                    try {
                        throw new Exception(i + "is error");
                    }
                    catch (Exception e1)
                    {
                        System.out.println(e1.getMessage());
                        return false;
                    }
                }
                if (closing.indexOf(i)!=opening.indexOf(buffer.pop())){
                    try {
                        throw new Exception(i + " is error");
                    }
                    catch (Exception e1)
                    {
                        System.out.println(e1.getMessage());
                        return false;
                    }
                }
            }
            r=i;
        }
        if(buffer.isEmpty())
        {
            String[] split = e.split("");
            int j ;
            for(int i = 0 ; split.length>i ; i++)
            {
                if(split[i].equals("+")||split[i].equals("-")||split[i].equals("*")||split[i].equals("/")||split[i].equals("^"))
                {
                    j=i ;
                    if(j+1>=split.length)
                    {
                        try{
                            throw new Exception(split[j]+"is error");
                        }
                        catch (Exception t )
                        {
                            System.out.println(t.getMessage());
                            return false;
                        }
                    }
                    char[] p = split[j+1].toCharArray();
                    if(!Character.isDigit(p[0]))
                    {
                        if(p[0]!='(') {
                            try {
                                throw new Exception(split[j + 1] + "is error with" + split[i]);
                            } catch (Exception t) {
                                System.out.println(t.getMessage());
                                return false;
                            }
                        }
                    }
                }
                if(split[i].equals(")")&& split.length>i+1)
                {
                    char[] q = split[i+1].toCharArray();
                    if(Character.isDigit(q[0]))
                    {
                        try {
                            throw new Exception("we must have operator between "+split[i]+" and"+split[i+1]);
                        } catch (Exception t) {
                            System.out.println(t.getMessage());
                            return false;
                        }
                    }
                }

            }
            return true;

        }
        else {
            try {
                throw new Exception(r + "error");
            }
            catch (Exception e1){
                System.out.println(e1.getMessage());
                return false;
            }
        }



    }
}
class Stack<E>{
    int Capacity=10000 ;
    E[] element ;
    int size =0;
    Stack()
    {
        element= (E[]) new Object[Capacity];

    }
    Stack(int capacity)
    {
        this.Capacity=capacity;
        element= (E[]) new Object[Capacity];
    }
    void push(E element)
    {
        this.element[size] = element;
        size++;
    }
    E peek()
    {

            if (size == 0)
                throw new EmptyStackException();



        return this.element[size-1];
    }
    E pop() throws IllegalStateException
    {
        if(size==0)
        {
            System.out.println("we dont have element");
            throw new  IllegalStateException("Stack is full");

        }
        else {
            E e = element[size - 1];
            element[size-1]=null;
            size--;
            return e;
        }

    }
    E top () throws IllegalStateException
    {
        if(size!=0)
        {
            E e = element[size-1];
            return e;
        }
        throw new  IllegalStateException("Stack is full");
    }
    boolean isEmpty()
    {
        if(size==0)
        {
            return true;
        }
        return false;
    }
    int size()
    {
        return size;
    }
    int getCapacity()
    {
        return Capacity;
    }

}

