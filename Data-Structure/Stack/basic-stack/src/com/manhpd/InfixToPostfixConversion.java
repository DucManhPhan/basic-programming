package com.manhpd;

/**
 * Conversion algorithm: Infix to Postfix
 *
 * 1. Scan the infix expression from left to right.
 * 2. If the scanned character is an operand, output it.
 * 3. Else,
 *       1 If the precedence of the scanned operator is greater than the precedence of the operator in the stack(or the stack is empty           or the stack contains a ‘(‘ ), push it.
 *       2 Else, Pop all the operators from the stack which are greater than or equal to in precedence than that of the scanned operator. After doing that Push the scanned operator to the stack. (If you encounter parenthesis while popping then stop there and push the scanned operator in the stack.)
 * 4. If the scanned character is an ‘(‘, push it to the stack.
 * 5. If the scanned character is an ‘)’, pop the stack and and output it until a ‘(‘ is encountered, and discard both the parenthesis.
 * 6. Repeat steps 2-6 until infix expression is scanned.
 * 7. Print the output
 * 8. Pop and output from the stack until it is not empty.
 */
public class InfixToPostfixConversion {

    public static void main(String[] args) {
//        String s = "a+b*(c^d-e)^(f+g*h)-i";   // abcd^e-fgh*+^*+i-
        String s = "x ^ y / (5 * z) + 10";      // xy^5z*/10+

        InfixToPostfixConversion solution = new InfixToPostfixConversion();
        System.out.println(solution.infixToPostfix(s));
    }

    public String infixToPostfix(String infix) {


        return "";
    }

}
