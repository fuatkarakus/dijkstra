package com.koucs;

import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Assert;
import org.junit.Test;

import static com.koucs.util.Utils.getExcelSheet;

public class ExcelUnitTest {

    @Test
    public void shouldReturnDistance() {
        Sheet sheet = getExcelSheet();

        // row(plaka+2).cell(plaka+1)
        Assert.assertEquals(111, sheet.getRow(36).getCell(42).getNumericCellValue(), 0.0);

    }

    @Test
    public void shouldReturnDistanceIstanbulAndTekirdag() {
        Sheet sheet = getExcelSheet();

        double dist =  sheet.getRow(36).getCell(60).getNumericCellValue() +
                sheet.getRow(61).getCell(23).getNumericCellValue() ;
        // row(plaka+2).cell(plaka+1)
        Assert.assertEquals(271, dist, 0.0);

    }

    @Test
    public void shouldReturnCityName() {
        Sheet sheet = getExcelSheet();

        Assert.assertEquals("ADANA", sheet.getRow(2).getCell(2).getStringCellValue());

    }

}
