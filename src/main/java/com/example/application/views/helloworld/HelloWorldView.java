package com.example.application.views.helloworld;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.application.views.MainLayout;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.mpr.LegacyWrapper;
import com.vaadin.mpr.core.HasLegacyComponents;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

@PageTitle("Hello World")
@Route(value = "hello")
@RouteAlias(value = "")
public class HelloWorldView extends HorizontalLayout implements HasLegacyComponents {

    public HelloWorldView() {
        Label legacy_component = new Label("Legacy component");
        add(legacy_component);

        add(new TextField("Legacy input fields"));

        add(new Button("Legacy button"));
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
