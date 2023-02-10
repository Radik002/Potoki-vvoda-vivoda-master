import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File("C://Users//1//Documents//IDEA//Potoki_vvoda_vivoda//basket.txt");
        try {
            if (file.exists()) {
                System.out.println("Корзина восстановлена:");
                Basket basket = Basket.loadFromTxtFile(file);
                basket.printCart();
            } else {
                System.out.println("Файл корзины отсутствует");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        String[] products = {
                "Молоко",
                "Яблоки",
                "Сыр",
                "Картофель",
                "Хлеб",
        };

        int[] prises = {
                80,
                50,
                200,
                35,
                45
        };

        Basket basket = new Basket(products, prises);

        System.out.println("Список возможных товаров для покупки: ");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prises[i] + " руб/шт");
        }

        int productNum;
        int count;

        while (true) {
            System.out.println("Выберите товар и количество или нажмите end");
            String input = scanner.nextLine();//1 2
            if (input.equals("end")) {
                break;
            }
            String[] parts = input.split(" ");

            productNum = Integer.parseInt(parts[0]);
            count = Integer.parseInt(parts[1]);

            basket.addToCart(productNum, count);
        }
        basket.printCart();
        basket.saveTxt(file);
    }
}
