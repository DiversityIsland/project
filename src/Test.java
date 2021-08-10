import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.printf("Введите, пожалуйста, выражение одного из этих видов: a + b, a - b, a * b, a / b,\nгде a и b - числа от 1 до 10 включительно в римской или арабской системе счисления.\n");
        String str = in.nextLine();
        String[] s = str.split(" ");
        if (s.length != 3)
            throw new Exception("Запись выражения может быть только таких видов: a + b, a - b, a * b, a / b. Между операндами и оператором необходим один пробел.");
        if (((!cont(s[0])) && (!cont(s[2]))) && ((!numb(s[0])) && (!numb(s[2]))))
            throw new Exception ("Операнды введены в неверном формате!");
        if (((cont(s[0])) && (!cont(s[2]))) || ((!cont(s[0])) && (cont(s[2]))))
            throw new Exception("Оба числа должны быть одной системы счисления!");
        int a = 0, b = 0, c = 0;
        if (cont(s[0]))
        {
            a = Roman.valueOf(s[0]).getNum();
            b = Roman.valueOf(s[2]).getNum();
        }
        else {
            a = Integer.parseInt(s[0]);
            b = Integer.parseInt(s[2]);
        }
        if ((a < 1) || (a > 10) || (b < 1) || (b > 10))
            throw new Exception("Числа на входе должны быть от 1 до 10 включительно!");
        switch (s[1]) {
            case "+":
                c = a + b;
                break;
            case "-":
                c = a - b;
                break;
            case "*":
                c = a * b;
                break;
            case "/":
                c = a / b;
                break;
            default:
                break;
        }
        if ((c < 1) && (cont(s[0])))
            throw new Exception("В римской системе счисления нет нуля и отрицательных чисел!");
        String q;
        if (c == 100)
            q = "C";
        else
            q = convert(c);
        if (cont(s[0]))
            System.out.printf("%s %s %s = %s%n", s[0], s[1], s[2], q);
        else
            System.out.printf("%d %s %d = %d%n", a, s[1], b, c);
    }

    public static String romanDigit(int n, String one, String five, String ten) {

        if (n >= 1) {
            switch (n) {
                case 1:
                    return one;
                case 2:
                    return one + one;
                case 3:
                    return one + one + one;
                case 4:
                    return one + five;
                case 5:
                    return five;
                case 6:
                    return five + one;
                case 7:
                    return five + one + one;
                case 8:
                    return five + one + one + one;
                case 9:
                    return one + ten;
                default:
                    return "";
            }
        }
        return "";
    }

    public static String convert(int number) {
        String romanOnes = romanDigit(number % 10, "I", "V", "X");
        number /= 10;
        String romanTens = romanDigit(number % 10, "X", "L", "C");
        number /= 10;
        String result = romanTens + romanOnes;
        return result;
    }

    public static boolean cont(String test) {

        for (Roman c : Roman.values()) {
            if (c.name().equals(test))
                return true;
        }

        return false;
    }

    public static boolean numb(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }

    enum Roman {
        I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10);
        private int num;

        Roman(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }
}