package com.koucs.domain;

import com.koucs.util.Utils;
import lombok.Data;
import org.apache.poi.ss.usermodel.Sheet;

@Data
public class City {

    private Double x;
    private Double y;
    private String licensePlate;
    private String name;

    private City() {}

    public City(Double x, Double y, String licensePlate, String name) {
        this.x = x;
        this.y = y;
        this.licensePlate = licensePlate;
        this.name = name;
    }

    public double distanceToCity(City city) {
        Sheet sheet = Utils.getInstance().getSheet();

        return sheet.getRow(Integer.parseInt(this.licensePlate) + 2)
                .getCell(Integer.parseInt(city.getLicensePlate()) + 1)
                .getNumericCellValue();
    }
}
