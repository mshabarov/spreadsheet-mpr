package com.example.application.views.helloworld;

import com.example.application.views.MainLayout;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.addon.spreadsheet.Spreadsheet;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.mpr.LegacyWrapper;
import com.vaadin.shared.ui.colorpicker.Color;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public HelloWorldView() {

        Spreadsheet spreadsheet = new Spreadsheet();


        Spreadsheet.SelectionChangeListener selectionChangeListener = new Spreadsheet.SelectionChangeListener() {

            @Override
            public void onSelectionChange(Spreadsheet.SelectionChangeEvent event) {
                CellReference selectedCell = event.getSelectedCellReference();
                Cell cell = spreadsheet.getCell(selectedCell.getRow(), selectedCell.getCol());
                if (cell != null) {
                    // This cast an only be done when using .xlsx files
                    XSSFCellStyle style = (XSSFCellStyle) cell.getCellStyle();
                    if (style != null) {
                        XSSFFont font = style.getFont();
                        if (font != null) {
                            XSSFColor xssfFontColor = font.getXSSFColor();
                            if (xssfFontColor != null) {

                            }
                        }
                        XSSFColor foregroundColor = style.getFillForegroundColorColor();
                        if (foregroundColor != null) {
//                            backgroundColor.setValue(convertColor(foregroundColor));
                        }
                    }
                }
            }
        };

        spreadsheet.addSelectionChangeListener(selectionChangeListener);

        Font fontBoldExample = spreadsheet.getWorkbook().createFont();
        fontBoldExample.setBold(true);
        CellStyle fontBoldExampleStyle = spreadsheet.getWorkbook()
                .createCellStyle();
        fontBoldExampleStyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
        fontBoldExampleStyle.setFont(fontBoldExample);
        Cell fontExampleCell = spreadsheet.createCell(0, 0,
                "Click the 'B' button in the top left corner to toggle bold font on and off.");
        fontExampleCell.setCellStyle(fontBoldExampleStyle);

        CellStyle backgroundColorStyle = spreadsheet.getWorkbook()
                .createCellStyle();
        backgroundColorStyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
        Cell backgroundExampleCell = spreadsheet.createCell(2, 0,
                "Click the 'Background Color' button to select and change the background color of a cell.");
        backgroundExampleCell.setCellStyle(backgroundColorStyle);

        Font fontColorExample = spreadsheet.getWorkbook().createFont();
        fontColorExample.setColor(HSSFColor.LIGHT_BLUE.index);
        CellStyle fontColorExampleStyle = spreadsheet.getWorkbook()
                .createCellStyle();
        fontColorExampleStyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
        fontColorExampleStyle.setFont(fontColorExample);
        Cell fontColorExampleCell = spreadsheet.createCell(4, 0,
                "Click the 'Font Color' button to select and change the font color of a cell.");
        fontColorExampleCell.setCellStyle(fontColorExampleStyle);

        Cell cell;
        for (int i = 0; i <= 4; i = i + 2) {
            for (int j = 1; j <= 9; j++) {
                cell = spreadsheet.createCell(i, j, "");
                cell.setCellStyle(backgroundColorStyle);
            }
        }

        spreadsheet.refreshCells(fontExampleCell, backgroundExampleCell,
                fontColorExampleCell);
        spreadsheet.setSizeFull();




        LegacyWrapper legacyWrapper = new LegacyWrapper(spreadsheet);

        LegacyWrapper test = new LegacyWrapper(new com.vaadin.ui.Button("Test"));
        add(test, legacyWrapper);
    }

}
