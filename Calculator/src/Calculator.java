import java.util.*;

public class Calculator {

    // TreeMap используется для храниения римских цифр
    private static final TreeMap<Integer, String> map = new TreeMap<>();

    // Статический блок в котором находятся римские цифры и их ценность
    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Input:"); // Здесь команда которая создаёт запрос в консоли
            String[] userInput = scanner.nextLine().toUpperCase().split(" ");

            // проверяем количество элементов введенной строки
            if (userInput.length != 3) {
                throw new IllegalArgumentException();
            }

            String firstOperand = userInput[0];
            String operator = userInput[1];
            String secondOperand = userInput[2];

            // проверяем, совпадают ли системы счисления обоих операндов
            if (isRomanNumber(firstOperand) != isRomanNumber(secondOperand)) {
                throw new IllegalArgumentException();
            }

            int number1 = isRomanNumber(firstOperand) ? romanToInt(firstOperand) : Integer.parseInt(firstOperand);
            int number2 = isRomanNumber(secondOperand) ? romanToInt(secondOperand) : Integer.parseInt(secondOperand);

            int result = calculate(number1, number2, operator);

            System.out.println("Output:");
            if (isRomanNumber(firstOperand)) {
                if (result <= 0) {
                    throw new IllegalArgumentException(); // Римские числа не могут быть отрицательными или нулем
                }
                System.out.println(intToRoman(result));
            } else {
                System.out.println(result);
            }

        } catch (IllegalArgumentException e) {
            System.out.println("throws Exception");
        }
    }
// Код самого калькулятора с обработкой событий и исключений
    private static int calculate(int number1, int number2, String operator) {
        switch (operator) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                if (number2 == 0) {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }
                return number1 / number2;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
    // Здесь проходит работа с римскими числами
    private static String intToRoman(int number) {
        int l = map.floorKey(number);
        if (number == l) {
            return map.get(number);
        }
        return map.get(l) + intToRoman(number - l);
    }

    private static int romanToInt(String roman) {
        // Начальная проверка на отсутствие неверных римских цифр
        if (!isRomanNumber(roman)) {
            throw new IllegalArgumentException("Invalid Roman numeral");
        }

        int result = 0;
        for (int i = 0; i < roman.length() - 1; i++) {
            if (getValue(roman.charAt(i)) < getValue(roman.charAt(i + 1))) {
                result -= getValue(roman.charAt(i));
            } else {
                result += getValue(roman.charAt(i));
            }
        }
        result += getValue(roman.charAt(roman.length() - 1));
        return result;
    }

    private static int getValue(char r) {
        switch (r) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: throw new IllegalArgumentException("Invalid Roman numeral");
        }
    }

    private static boolean isRomanNumber(String value) {
        return value.matches("^(M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3}))$");
    }
}
// Калькулятор соответствует всем требованиям и выводит значения и ошибки так как указанно в задании