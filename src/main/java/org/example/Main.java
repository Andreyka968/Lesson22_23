package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    static Logger logger;
    private static final String FILENAME = "resources/computer1.csv";
    private static Computer sybernova;

    static String[] readFileUsingFileReader(String filename) {
        var newLine = System.lineSeparator();
        var sb = new StringBuffer();
        FileReader reader;

        try {
            reader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            reader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int c;
        try {
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString().split(System.lineSeparator());
    }

    private static String[] readFileToArray(String filename) throws FileNotFoundException {
        List<String> data = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(FILENAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                data.add(line);
            }
        }
        return data.toArray(String[]::new);
    }

    static String[] readFileUsingScanner(String filename) {
        ArrayList<String> data = new ArrayList<String>();
        var file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        boolean finished = false;
        do {
            try {
                data.add(scanner.nextLine());
            } catch (NoSuchElementException e) {
                finished = true;
            }
        }
        while (!finished);
        scanner.close();
        return Arrays.copyOf(data.toArray() , data.size() , String[].class);
    }

    private static List<Computer> createdObject(String[] data) {
        List<Computer> myList = new ArrayList<>();
        for (int c = 2; c < data.length; c++) {
            var peaces = data[c].split(";");
            Computer sybernova = null;
            switch (peaces[2]) {
                case "CyberNova_1":
                    sybernova = new CyberNova
                            (Integer.parseInt(peaces[0]) , peaces[5] , peaces[2] , peaces[1] ,
                                    peaces[4] , peaces[3] ,
                                    Integer.parseInt(peaces[6]));
                    logger.log(Level.INFO , "Created syberNova of Class ACyberNova: " + sybernova);
                    break;
                case "NexusWave_2":
                    sybernova = new NexusWave(Integer.parseInt(peaces[0]) , peaces[5] , peaces[2] , peaces[1] ,
                            peaces[4] , peaces[3] ,
                            Integer.parseInt(peaces[6]));
                    logger.log(Level.INFO , "Created syberNova of Class NexusWave: " + sybernova);
                    break;
                case "QuantumPulse_3":
                    sybernova = new QuantumPulse(Integer.parseInt(peaces[0]) , peaces[5] , peaces[2] , peaces[1] ,
                            peaces[4] , peaces[3] ,
                            Integer.parseInt(peaces[6]));
                    logger.log(Level.INFO , "Created syberNova of Class QuantumPulse: " + sybernova);
                    break;
                case "StellarByte_4":
                    sybernova = new StellarByte(Integer.parseInt(peaces[0]) , peaces[5] , peaces[2] , peaces[1] ,
                            peaces[4] , peaces[3] ,
                            Integer.parseInt(peaces[6]));
                    logger.log(Level.INFO , "Created syberNova of Class StellarByte: " + sybernova);
                    break;
                case "VirtuosoSystem_6":
                    sybernova = new VirtuosoSystem(Integer.parseInt(peaces[0]) , peaces[5] , peaces[2] , peaces[1] ,
                            peaces[4] , peaces[3] ,
                            Integer.parseInt(peaces[6]));
                    logger.log(Level.INFO , "Created syberNova of Class Alabay: " + sybernova);
                    break;
                case "TechMaverick_5":
                    sybernova = new TechMaverick(Integer.parseInt(peaces[0]) , peaces[5] , peaces[2] , peaces[1] ,
                            peaces[4] , peaces[3] ,
                            Integer.parseInt(peaces[6]));
                    logger.log(Level.INFO , "Created syberNova of Class VirtuosoSystem: " + sybernova);
                    break;
            }
            myList.add(sybernova);
        }
        return myList;
    }
//----------------------

    public static void main(String[] args) throws FileNotFoundException {

        String configFilePath = System.getenv("CONFIG_FILE_PATH");
        if (configFilePath != null && !configFilePath.isEmpty()) {
            System.setProperty("java.util.logging.config.file", configFilePath);
        } else {
            System.setProperty("java.util.logging.config.file", "resources/logging.properties");
        }
        logger = Logger.getLogger(Main.class.getName());

        ArrayList<Long> executionTimes = new ArrayList<>();
        long t = 0;
        List<Computer> myList = new LinkedList<>();
        long time1 = System.nanoTime();
        long time2 = 0;

        time2 = System.nanoTime();
        t = (time2 - time1) / 1000000;

        System.out.println("Process lasted " + t + "mc");
        time1 = time2;

        String[] data = readFileUsingFileReader(FILENAME);

        time2 = System.nanoTime();
        t = (time2 - time1) / 1000000;
        executionTimes.add(t);
        System.out.println("Process readFileUsingFileReader lasted " + t + "mc");
        time1 = time2;

        myList = createdObject(data);

        time2 = System.nanoTime();
        t = (time2 - time1) / 1000000;
        executionTimes.add(t);
        System.out.println("Process createdObject(data) lasted " + (time2 - time1) / 1000000 + "mc");
        time1 = time2;

        //Сортировка с условием SortSkip и SortOut.
        Stream<Computer> stream = myList.stream();
        stream
                .sorted((c1 , c2) -> c1.getId() - c2.getId())
                .skip(17)
                .limit(21)
                .forEach(System.out::println);

//      Фильтрация в количестве FilteRoot.
        Stream<Computer> stream1 = myList.stream();
        stream1
                .filter(cam -> cam.getHascam() >= 1)
                .limit(11)
                .forEach(System.out::println);

        //Создание обьекта Map из стрима.
        Stream<Computer> stream2 = myList.stream();
        Map<Integer, String> compMap = stream2.collect(Collectors.toMap(Computer::getId ,
                Computer::getComputerName));
        compMap.forEach((key , value) -> System.out.println
                ("ID: " + key + ", Computer Name: " + value));

        time2 = System.nanoTime();
        t = (time2 - time1) / 1000000;
        executionTimes.add(t);
        System.out.println("Three acts with the streams lasted " + (time2 - time1) / 1000000 + "mc");
        time1 = time2;

        Collections.sort(executionTimes);

        // Вывод временных данных в консоль
        System.out.println("Времена выполнения в миллисекундах (возрастающий порядок):");
        for (long time : executionTimes) {
            System.out.println(time);
        }

        // Создаем два объекта LocalDateTime

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try {
            // Заданные даты
            Date date1 = dateFormat.parse("12.11.2023");
            Date date2 = dateFormat.parse("4.1.2024");

            // Разница в миллисекундах
            long differenceInMillis = Math.abs(date2.getTime() - date1.getTime());
            int differenceInDays = (int) (differenceInMillis / 1000 / 3600 / 24);

            // Вывод результата
            System.out.println("date1 " + date1);
            System.out.println("date2 " + date2);
            System.out.println("Разница в миллисекундах: " + differenceInMillis + " Это " + differenceInDays + " дня");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
