import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        int a;
        int b;
        int c;
        char operator;

        Scanner reed = new Scanner(System.in);
        System.out.println("Введите два числа: ");
        a = reed.nextInt();
        b = reed.nextInt();

        System.out.println("\nВведите оператор (+, -, *, /): ");
        operator = reed.next().charAt(0);

        switch(operator) {
            case '+': c = a + b;
                break;
            case '-': c = a - b;
                break;
            case '*': c = a * b;
                break;
            case '/': c = a / b;
                break;
            default:  System.out.printf("throws Exception");
                return;
        }
        System.out.print("\nРезультат:\n");
        System.out.printf(a + " " + operator + " " + b + " = " + c);

    }
}