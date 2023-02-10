import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket {

    private String[] products;
    private int[] prises;
    private int[] counts;
    private int ollSum;

    public Basket(String[] products, int[] prises) {
        this.products = products;
        this.prises = prises;
        this.counts = new int[products.length];
    }

    private Basket(String[] products, int[] prises, int[] counts, int ollSum) {
        this.products = products;
        this.prises = prises;
        this.counts = counts;
        this.ollSum = ollSum;
    }
//    int ollSum = 0;

    public void addToCart(int productNum, int amount) {
        counts[productNum - 1] += amount;
        int sum = amount * prises[productNum - 1];
        ollSum += sum;
//        System.out.println(Arrays.toString(counts));
    }

    public void printCart() {
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                System.out.println(products[i] + " " + counts[i] + " шт " +
                        prises[i] + " руб/шт " + (counts[i] * prises[i]) + " в сумме");
            }
        }
        System.out.println("Итого: " + ollSum);
    }

    public void saveTxt(File textFile) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (String product : products) {
                out.print(product + " ");
            }
            out.println();
            for (int prise : prises) {
                out.print(prise + " ");
            }
            out.println();
            for (int count : counts) {
                out.print(count + " ");
            }
            out.println();
            out.print(ollSum);
        }
    }

    static Basket loadFromTxtFile(File textFile) throws IOException {
        try (FileInputStream input = new FileInputStream(textFile)) {
            Scanner scanner = new Scanner(textFile);
            String[] product = scanner.nextLine().split(" ");
            String[] prisesAbc = scanner.nextLine().split(" ");
            int[] prises = Arrays.stream(prisesAbc).mapToInt(Integer::parseInt).toArray();
            String[] countsAbc = scanner.nextLine().split(" ");
            int[] counts = Arrays.stream(countsAbc).mapToInt(Integer::parseInt).toArray();
            String ollSumAbc = scanner.nextLine();
            int ollSum = Integer.parseInt(ollSumAbc);

            return new Basket(product, prises, counts, ollSum);
        }
    }

    public String toString() {
        return Arrays.toString(products) + Arrays.toString(prises) + Arrays.toString(counts);
    }
}


