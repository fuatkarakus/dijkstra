package com.koucs.util;

import com.koucs.domain.City;
import lombok.Getter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class Utils {

    private static Utils instance;

    private final Map<String, City> cities;
    private final List<String> route;
    private final Map<City, List<City>> neighbourhood;
    private final Sheet sheet;

    private Utils() throws IOException {
        this.cities = getAllCities();
        this.route = getCityRoute();
        this.neighbourhood = getCitiesNeighbourhood();
        this.sheet = getExcelSheet();
    }

    public static synchronized Utils getInstance() {
        try {
            if (instance == null) {
                instance = new Utils();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static <T> List<Pair<T, T>> getCombinationsGivenCities(List<T> tList) {

        List<Pair<T, T>> pair = new ArrayList<>();

        for (int i = 0; i < tList.size(); i++) {

            for (int j = i + 1; j < tList.size(); j++) {

                pair.add(Pair.create(tList.get(i), tList.get(j)));

            }
        }
        return pair;
    }

    public static Map<String, City> getAllCities() throws IOException {

        Map<String, City> cities;

        String filePath = getFilePath("konum.csv");

        Stream<String> lines = Files.lines(Paths.get(filePath));

        cities =
                lines.map(line -> line.split(";"))
                        .collect(Collectors.toMap(
                                line -> line[2],
                                line -> new City(Double.valueOf(line[0]),
                                        Double.valueOf(line[1]),
                                        line[2],
                                        line[3])
                        ));

        lines.close();

        return cities;

    }

    public static List<String> getCityRoute() throws IOException {

        List<String> route;

        String filePath = getFilePath("rota.csv");

        Stream<String> lines = Files.lines(Paths.get(filePath));

        route = lines.collect(Collectors.toList());

        route.remove("41");

        if (route.size() > 9)
            route = route.subList(0, 9);

        lines.close();

        return route;
    }

    public static Map<String, String> getCityNumbers() throws IOException {

        Map<String, String> numbers;

        String filePath = getFilePath("plaka.csv");

        Stream<String> lines = Files.lines(Paths.get(filePath));

        numbers = lines.map(line -> line.split(";"))
                .collect(Collectors.toMap(
                        line -> line[0],
                        line -> line[1]
                ));

        lines.close();

        return numbers;
    }

    public static Map<City, List<City>> getCitiesNeighbourhood() throws IOException {

        Map<City, List<City>> neighbourhood;

        Map<String, City> cityMap = getAllCities();

        String filePath = getFilePath("komsu.csv");

        Stream<String> lines = Files.lines(Paths.get(filePath));

        neighbourhood = lines.map(line -> line.split(";"))
                .collect(Collectors.toMap(
                        line -> cityMap.get(line[0]),
                        line -> Arrays.stream(line).skip(1) // skip first element
                                .map(cityMap::get)
                                .collect(Collectors.toList())
                ));

        lines.close();

        return neighbourhood;
    }

    public static Sheet getExcelSheet() {
        String filePath = getFilePath("ilmesafe.xls");
        Sheet sheet1 = null;

        try (InputStream inp = new FileInputStream(new File(filePath))) {

            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));

            sheet1 = wb.getSheetAt(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sheet1;
    }

    public static <T> List<List<T>> listPermutations(List<T> list) {

        if (list.isEmpty()) {
            List<List<T>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }

        List<List<T>> returnMe = new ArrayList<>();

        T firstElement = list.remove(0);

        List<List<T>> recursiveReturn = listPermutations(list);

        for (List<T> li : recursiveReturn) {

            for (int index = 0; index <= li.size(); index++) {
                List<T> temp = new ArrayList<>(li);
                temp.add(index, firstElement);
                returnMe.add(temp);
            }

        }

        return returnMe;
    }

    public static List<Pair<Integer, Integer>> getRoutePairs(List<String> list) {

        List<Pair<Integer, Integer>> pairs = new ArrayList<>();

        for (int i = 0; i<list.size()-1; i++) {
            pairs.add(Pair.create(Integer.valueOf(list.get(i)), Integer.valueOf(list.get(i+1))));
        }

        return pairs;

    }

    public static String getFilePath(String fileName) {
        return Objects.requireNonNull(Utils.class
                .getClassLoader()
                .getResource(fileName))
                .getPath();
    }
}
