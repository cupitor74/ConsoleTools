import java.util.Stack;

public class ArithmeticExpressionEvaluator {

    /**
     * Evaluates a mathematical expression given as a string and returns the result.
     * Uses basic arithmetic operations (+, -, *, /), parentheses, and decimal numbers
     * @param text the mathematical expression to evaluate
     * @return the result of the expression
     */
    public static double evaluateExpression(String text) {
        /* to remove all  whitespace characters from entered text */
        String expression = text.replaceAll("\\s+", "");

        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        int length = expression.length();

        for (int i = 0; i < length; i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                String number = "";

                while (i < length && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    number += expression.charAt(i);
                    i++;
                }
                i--;

                values.push(Double.parseDouble(number));

            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    values.push(applyOperator( values.pop(), values.pop(), operators.pop()));
                }
                operators.pop();

            } else if (isMathOperator(c)) {
                while (!operators.isEmpty() && priorityOperation(operators.peek()) >= priorityOperation(c)) {

                    values.push(applyOperator( values.pop(), values.pop(), operators.pop()));
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperator(values.pop(), values.pop(), operators.pop() ));
        }

        return values.pop();
    }

    /**
     * Check if c is Math operator
     * @param c char to check
     * @return true value if char is Math operator
     */
    private static boolean isMathOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * Returns the priority operation where 2 is higher for "*" or "/" and 1 if "+" or "-"
     * @param op char to check
     * @return the priority level as int
     */
    private static int priorityOperation(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    /**
     * Applies the arithmetic operation specified by the operator to the two operands.
     * @param b the second operand
     * @param a the first operand
     * @param op the arithmetic operator to apply (+, -, *, /)
     * @return the result of the arithmetic operation
     */
    private static double applyOperator(  double b, double a, char op ) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Error: Division by zero!");
                return a / b;
            default: throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }
}
