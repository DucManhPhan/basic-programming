import com.manhpd.array.ArrayStack;
import com.manhpd.array.Element;
import com.manhpd.linked_list.Node;
import com.manhpd.linked_list.llStack;

public class Application {

    public static void main(String[] args) {
        usingStackByLinkedList();
    }

    private static void usingStackByArray() throws Exception {
        ArrayStack stack = new ArrayStack();
        stack.push(new Element(10));
        stack.push(new Element(20));
        stack.push(new Element(30));
        stack.push(new Element(40));

        System.out.println("The top element of stack is: " + stack.peek().getValue());

        try {
            stack.pop();
            stack.pop();
            stack.pop();
            stack.pop();
            stack.pop();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void usingStackByLinkedList() {
        llStack stack = new llStack();

        stack.push(new Node(10));
        stack.push(new Node(20));
        stack.push(new Node(30));
        stack.push(new Node(40));

        System.out.println("The top position of Stack is: " + stack.peek().getValue());

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
    }

}
