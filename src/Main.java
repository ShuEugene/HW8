//  Домашние задания к Уроку №8 «Методы»

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//        System.out.println();
//        task1(2022);
        System.out.println();
        task2("2021, anDroid");
        System.out.println();

    }

//  ДЗ 8.1
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

//  ДЗ 8.2
    static void task2(String ps) {
        int clientOSIndex = 0;
        int deviceReleaseYear = 0;
        String[] parameters;
        if (strNotEmpty(ps)) {
            parameters = prepareParamsString(ps);
            clientOSIndex = getClientOS(parameters);
            deviceReleaseYear = getOSReleaseYear(parameters);
        }
        String clientOSName = (clientOSIndex == 0) ? "iOS" : "Android";
        String osVersion = (deviceReleaseYear < LocalDate.now().getYear()) ? "облегчённую " : "";
        System.out.printf("У Вас есть возможность установить новую %sверсию %s.", osVersion, clientOSName);
    }

    static boolean strNotEmpty(String str) {
        if (str.length() > 0) {
            return true;
        }
        throw new RuntimeException("Строка пуста.");
    }

    static String getParamsSeparator(String str) {
        if (str.contains(",")) {
            return ",";
        } else if (str.contains(";")) {
            return ";";
        } else if (str.contains(".")) {
            return dotSymbol;
        } else if (str.contains(" ")) {
            return " ";
        }
        throw new RuntimeException("Допустимый разделитель отсутствует.");
    }

    static final String dotSymbol = "%dot%";

    static String[] prepareParamsString(String str) {
        String paramsSeparator = getParamsSeparator(str);
        String[] parameters;
        switch (paramsSeparator) {
            case ",":
            case ";":
            case dotSymbol:
                if (str.contains(" ")) {
                    str = str.replace(" ", "");
                }
                if (str.contains(".")) {
                    str = str.replace(".", dotSymbol);
                }
                parameters = str.split(paramsSeparator);
                return parameters;
            case " ":
                parameters = str.split(paramsSeparator);
                return parameters;
            default:
                throw new RuntimeException("Недопустимые параметры.");
        }
    }

    static boolean isCorrectParamNumber(String[] paramString) {
        if (paramString.length == 2) {
            return true;
        }
        throw new RuntimeException("Недопустимое количество параметров.");
    }

    static int getClientOS(String[] paramsString) throws NumberFormatException {
        int clientOS = 0;
        if (isCorrectParamNumber(paramsString)) {
            for (String currentParameter :
                    paramsString) {
                try {
                    Integer.parseInt(currentParameter);
                } catch (NumberFormatException e) {
                    currentParameter = currentParameter.toLowerCase();
                    clientOS = currentParameter.equals("ios") ? 0 : 1;
                }
            }
        }
        return clientOS;
    }

    static int getOSReleaseYear(String[] paramsString) throws NumberFormatException {
        int releaseYear = 0;
        if (isCorrectParamNumber(paramsString)) {
            for (String currentParameter :
                    paramsString) {
                try {
                    releaseYear = Integer.parseInt(currentParameter);
                    if (releaseYear > 0) {
                        return releaseYear;
                    } else throw new RuntimeException("Год выпуска устройства не указан.");
                } catch (NumberFormatException e) {
                }
            }
        }
        return releaseYear;
    }
}