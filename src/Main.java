//  Домашние задания к Уроку №8 «Методы»
public class Main {

    static void task1(int arg) {
        System.out.printf("%d - %s год.", arg, yearIsLeap(arg));
    }

    static String yearIsLeap(int year) {
        if (year > 0) {
            if (((year % 4) == 0 && (year % 100) != 0) || (year % 400) == 0) {
                return "високосный";
            } else return "не високосный";
        }
        throw new RuntimeException("Год введён некорректно.");
    }

    public static void main(String[] args) {
        System.out.println();
        task1(2022);
        System.out.println();

    }
}