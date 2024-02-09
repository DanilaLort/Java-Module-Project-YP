import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Main {
    static int numOfPersons;
    static double check = 0;
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        String s;
        System.out.println("Введите количество человек:");
        numOfPersons = scanner.nextInt();

        while (true) {
            if (numOfPersons > 1) {
                System.out.println("Введите блюдо и цену.\nЕсли добавили все позиции введите 'Завершить'");
                s = scanner.next();
                // System.out.println(s);
                if (s.equalsIgnoreCase("завершить")) {
                    System.out.println("Добавленные товары:");
                    for (Product product : products) {
                        product.print();
                    }
                    if (check / numOfPersons > 1)
                        System.out.println(String.format("Итого: %.2f рубля с каждого", check / numOfPersons));
                    else
                        System.out.println(String.format("Итого: %.2f рубль с каждого", check / numOfPersons));
                    break;
                } else {
                    s += scanner.nextLine();
                    calculate(s);
                }
            } else {
                System.out.println("Количество человек меньше 1. Это некорректное значение для подсчёта");
                System.out.println("Введите количество человек:");
                numOfPersons = scanner.nextInt();
            }
        }
    }

    private static void calculate(String s) {
        Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+$");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            check += Double.parseDouble(s.substring(matcher.start(), matcher.end()));
            Product product = new Product(s.substring(0, matcher.start() - 1), Double.parseDouble(s.substring(matcher.start(), matcher.end())));
            product.printAdd();
            products.add(product);
        } else {
            System.out.println("Некоректный ввод!\nЦена указывается в формате: РУБЛИ.КОПЕЙКИ");
        }
        // System.out.println(s.substring(matcher.start(), matcher.end()));

    }


}
