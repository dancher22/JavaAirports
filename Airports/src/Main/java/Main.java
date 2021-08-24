package Main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        List<String[]> list = new ArrayList<>();
        File csvFile;
        try {
        csvFile = new File("airports.csv");
        String Path = csvFile.getPath();
        String line;
        String cvsSplitBy = ",";
        BufferedReader br = new BufferedReader(new FileReader(Path));
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(cvsSplitBy);
                list.add(columns);
            }
        } catch (FileNotFoundException e) {
            out.println("Файл не найден. Для корректной работы программы добавьте файл airports.csv в одну папку с файлом Airports.jar.");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Settings settings = new Settings();
        ClassLoader classLoader = Settings.class.getClassLoader();
        try (InputStream io = classLoader.getResourceAsStream("app.properties")){
            settings.LoadPrs(io);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        byte colNumb;
        if (args.length != 0) {
            String programValue = settings.GetValue(args[0]);
            try {
                 colNumb = Byte.parseByte(programValue);
            } catch (NumberFormatException e) {
                colNumb = 1;
                out.println("Номер колонки введён некорректно! По умолчанию, для поиска, выбрана колонка 2.");
            }
        } else {
            out.println("По умолчанию, для поиска, выбрана колонка 2.");
            colNumb = 1;
        }

        list.sort(Comparator.comparing(columns -> columns[1]));

        int countLines = 0;

        out.print("Введите строку: ");
        Scanner scanner = new Scanner(in);
        String enteredValue = scanner.nextLine();

        long m = currentTimeMillis();

        if (!enteredValue.isEmpty()) {
            for (String[] columns : list) {
                if (columns[colNumb].startsWith(enteredValue)) {
                    out.println(new StringBuilder().append(columns[1]).append("; ").append(columns[2]).append("; ")
                            .append(columns[3]).append("; ").append(columns[4]).append("; ").append(columns[5]).
                            append("; ").append(columns[6]).append("; ").append(columns[7]).append("; ").
                            append(columns[8]).append("; ").append(columns[9]).append("; ").append(columns[10]).
                            append("; ").append(columns[11]).append("; ").append(columns[12]).append("; ").append(columns[13]));
                    countLines++;
                }
            }
        } else {
            out.println("Введана пустая строка.");
        }
        out.println("Количество найденных строк: " + countLines + ".");
        out.println("Время, затраченное на поиск: " + (currentTimeMillis() - m) + " мс.");
    }
}