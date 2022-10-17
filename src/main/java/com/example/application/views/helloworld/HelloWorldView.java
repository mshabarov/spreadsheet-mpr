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
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
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

        LegacyWrapper legacyWrapper1 = new LegacyWrapper(grid);

        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.add(legacyWrapper1);

        Button flow_button = new Button("Flow button", click -> {
            Version version = new Version("My Product", "My version");
            versions.add(version);
            grid.setItems(versions);
        });

        com.vaadin.ui.Button disable_flow_button = new com.vaadin.ui.Button(
                "Disable flow button", click -> flow_button.setEnabled(!flow_button.isEnabled()));
        LegacyWrapper legacyWrapper = new LegacyWrapper(disable_flow_button);

        verticalLayout.add(legacyWrapper, flow_button);
        add(verticalLayout);
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
