import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void getData(String str) {
        if(str.isEmpty()){
            System.out.println("String is empty");
        }else{
            int row = 2;
            CsvParserSettings settings = new CsvParserSettings();
            settings.selectIndexes( row - 1);
            CsvParser parser = new CsvParser(settings);

            Trie root = null;
            try {
                root = new Trie(parser.parseAll(new FileReader("airports.csv")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            long startTime = System.currentTimeMillis();
            List<String> lines = root.suggest(str);
            long endTime = System.currentTimeMillis();

            for(String line : lines){
                System.out.println(line);
            }
            System.out.println("\nNumber of lines: " + lines.size());
            System.out.println("Search time: " + (endTime-startTime) + "ms");
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите строку: ");
        String str = in.nextLine();
        in.close();

        getData(str);

    }
}
