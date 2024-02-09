public class Product {
    private String name;
    private double price;
    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public void printAdd() {
        System.out.println("Блюдо: " + name + " по цене " + price + " рублей" + " добавленно");
    }

    public void print() {
        System.out.println("Блюдо: " + name + " по цене " + price + " рублей");
    }
}
