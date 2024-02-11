import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Main {
    static int numOfPersons;
    static double check = 0;
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Product> products = new ArrayList<>();
    static Calculate calc = new Calculate();

    public static void main(String[] args) {
        String s;
        System.out.println("На скольких человек необходимо разделить счёт:");
        s = scanner.next();

        while (true) {
            if (s.equalsIgnoreCase("завершить")) {
                System.out.println("Программа завершена");
                return;
            }
            if (s.matches("\\d+")) {
                numOfPersons = Integer.parseInt(s);
            } else {
                System.out.println("Это некорректное значение для подсчёта");
                System.out.println("Введите количество человек:");
                s = scanner.next();
            }
            if (numOfPersons == 1) {
                System.out.println("Количество человек равно 1.\nЭто некорректное значение для подсчёта");
                System.out.println("Введите количество человек:");
                s = scanner.next();
            } else if (numOfPersons < 1) {
                System.out.println("Количество человек меньше 1.\nЭто некорректное значение для подсчёта");
                System.out.println("Введите количество человек:");
                s = scanner.next();;
            } else break;

        }

        while (true) {
            System.out.println("Введите блюдо и цену.\nЕсли добавили все позиции введите 'Завершить'");
            s = scanner.next();
            if (!s.equalsIgnoreCase("завершить")) {
                s += scanner.nextLine();
                check += calc.calculate(s, products);
            } else {
                System.out.println("Добавленные товары:");
                for (Product product : products) {
                    product.print();
                }
                if (check / numOfPersons > 1)
                    System.out.println(String.format("Итого: %.2f рубля с каждого", check / numOfPersons));
                else
                    System.out.println(String.format("Итого: %.2f рубль с каждого", check / numOfPersons));
                return;
            }
        }
    }

    public static class Calculate {
            public static double calculate(String s, ArrayList products) {
                Pattern pattern = Pattern.compile(" [0-9]*\\.?[0-9]+$");
                Matcher matcher = pattern.matcher(s);
                if (matcher.find())
                    if (matcher.start() > 1) {
                        double check = 0;
                        check += Double.parseDouble(s.substring(matcher.start(), matcher.end()));
                        Product product = new Product(s.substring(0, matcher.start() - 1), Double.parseDouble(s.substring(matcher.start(), matcher.end())));
                        product.printAdd();
                        products.add(product);
                        return check;
                    }
                System.out.println("Некоректный ввод!\nВвод осуществляется в формате:Название блюда РУБЛИ.КОПЕЙКИ");
                // System.out.println(s.substring(matcher.start(), matcher.end()));
                return 0;
            }
    }

    public static class Product {
        private String name;
        private double price;
        Product(String name, double price) {
            this.name = name;
            this.price = price;
        }
        public void printAdd() {
            System.out.println(String.format("Блюдо: %s по цене %.2f рублей добавленно", name, price));
        }

        public void print() {
            System.out.println(String.format("Блюдо: %s по цене %.2f рублей", name, price));
        }
    }
}
