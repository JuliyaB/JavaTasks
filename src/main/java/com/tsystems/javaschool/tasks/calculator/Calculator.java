package com.tsystems.javaschool.tasks.calculator;

import java.util.*;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("Answer: " + evaluate("(1+38)*4-5"));
        System.out.println("Answer: " + evaluate("7*6/2+8"));
        System.out.println("Answer: " + evaluate("-12)1//("));
    }

    static public String evaluate(String statement) {
        String current = "";
        int parenthesis = 0;
        try {
            Stack<Character> stack = new Stack<>();
            int priority;
            for (int i = 0; i < statement.length(); i++) {
                priority = getP(statement.charAt(i));

                if (priority == 0) {
                    current += statement.charAt(i);
                }
                if (priority == 1) {
                    stack.push(statement.charAt(i));
                    parenthesis++;
                }
                if (priority > 1) {
                    current += ' ';
                    while (!stack.empty()) {
                        if (getP(stack.peek()) >= priority) {
                            current += stack.pop();
                        } else {
                            break;
                        }
                    }
                    stack.push(statement.charAt(i));
                }
                if (priority == -1) {
                    current += ' ';
                    parenthesis--;
                    while (getP(stack.peek()) != 1) {
                        current += stack.pop();
                    }
                    stack.pop();
                }
            }
            while (!stack.empty()) {
                current += stack.pop();
            }
            if (parenthesis != 0) {
                return null;
            }

            Stack<Double> stack2 = new Stack<>();
            for (int i = 0; i < current.length(); i++) {
                String operand = "";
                if (current.charAt(i) == ' ') {
                    continue;
                }
                if (getP(current.charAt(i)) == 0) {
                    while (getP(current.charAt(i)) == 0 && current.charAt(i) != ' ') {
                        operand += current.charAt(i);
                        i++;
                    }
                    stack2.push(Double.parseDouble(operand));
                    i--;
                }
                if (getP(current.charAt(i)) > 1) {
                    double a = stack2.pop(), b = stack2.pop();

                    if (current.charAt(i) == '+') {
                        stack2.push(b + a);
                    }
                    if (current.charAt(i) == '-') {
                        stack2.push(b - a);
                    }
                    if (current.charAt(i) == '*') {
                        stack2.push(b * a);
                    }
                    if (current.charAt(i) == '/') {
                        stack2.push(b / a);
                    }
                }
            }
            double res = stack2.pop();
            if (res == Double.NEGATIVE_INFINITY || res == Double.POSITIVE_INFINITY)
                return null;
            if (res % 1 == 0.0) {
                return Integer.toString((int) res);
            } else {
                return Double.toString(res);
            }
        } catch (Exception e) {
            return null;
        }
    }

    private static int getP(char token) {
        if (token == '*' || token == '/') {
            return 3;
        } else {
            if (token == '+' || token == '-') {
                return 2;
            } else {
                if (token == '(') {
                    return 1;
                } else {
                    if (token == ')') {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        }
    }
}
