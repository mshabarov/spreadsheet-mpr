package com.example.application.views.helloworld;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.application.views.MainLayout;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.mpr.LegacyWrapper;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;

@PageTitle("Hello World")
@Route(value = "hello")
@RouteAlias(value = "")
public class HelloWorldView extends HorizontalLayout {

    public HelloWorldView() {
        List<Version> versions = new ArrayList<>();
        versions.add(
                new Version("Spring Framework", SpringVersion.getVersion()));
        versions.add(
                new Version("Spring Boot", SpringBootVersion.getVersion()));

        Grid<Version> grid = new Grid<>();
        grid.setItems(versions);
        grid.addColumn(Version::getProduct).setCaption("Product");
        grid.addColumn(Version::getVersion).setCaption("Version");

        LegacyWrapper legacyWrapper = new LegacyWrapper(grid);

        VerticalLayout legacy = new VerticalLayout();

        legacy.add(legacyWrapper);

//        com.vaadin.flow.component.grid.Grid<Version> flowGrid = new com.vaadin.flow.component.grid.Grid<>();
//        flowGrid.setItems(versions);
//        flowGrid.addColumn(Version::getProduct).setHeader("Product");
//        flowGrid.addColumn(Version::getVersion).setHeader("Version");


        Button flowButton = new Button("Add product (Flow)", click -> {
            Version version = new Version("New Product", "1.0.0");
            versions.add(version);
            grid.setItems(versions);
        });

        legacy.add(flowButton);

//        com.vaadin.ui.Button legacyButton = new com.vaadin.ui.Button(
//                "Add product (Vaadin 8)", click -> flowButton.setEnabled(!flowButton.isEnabled()));

//        LegacyWrapper legacyButtonWrapper = new LegacyWrapper(legacyButton);
//        legacy.add(legacyButtonWrapper);

//        VerticalLayout verticalLayout = new VerticalLayout();
//        verticalLayout.add(flowGrid, flowButton);
//        verticalLayout.add(flowButton);

        add(legacy);
    }

    public static class Version implements Serializable {
        private String product;
        private String version;

        public Version(String product, String version) {
            this.product = product;
            this.version = version;
        }

        public String getProduct() {
            return product;
        }

        public String getVersion() {
            return version;
        }
    }

}
