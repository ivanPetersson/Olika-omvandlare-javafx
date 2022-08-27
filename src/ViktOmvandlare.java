import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class ViktOmvandlare extends Application {

    Button omvandla = new Button("Omvandla");
    Button radera = new Button("Återställ");
    TextField firstTextField;
    TextField secondTextField;
    ComboBox firstComboBox;
    ComboBox secondComboBox;
    Label resultatLabel;
    DecimalFormat numberFormat = new DecimalFormat("#.00");
    Stage primaryStage = new Stage();

    public double kgToLb(double kg){
        return kg  * 2.20;
    }

    public double lbToKg(double lb){
        return lb  / 2.20;
    }


    @Override
    public void start(Stage stage) throws Exception {

        BorderPane root = new BorderPane();
        root.setPrefWidth(370);
        root.setPrefHeight(400);

        FlowPane bottomFlowPane = new FlowPane();
        bottomFlowPane.setHgap(5);
        bottomFlowPane.setPadding(new Insets(30));
        bottomFlowPane.getChildren().addAll(omvandla, radera);
        omvandla.setPadding(new Insets(10));
        omvandla.setStyle("-fx-background-color: blue;" +
                "-fx-font-weight: bold;");
        omvandla.setOnAction(new OmvandlaHandler());
        omvandla.setOnMouseEntered(new HiglightHandler());
        omvandla.setOnMouseExited(new RemoveHiglightHandler());
        radera.setPadding(new Insets(10));
        radera.setStyle("-fx-background-color: blue;" +
                "-fx-font-weight: bold;");
        radera.setOnAction(new RaderaHandler());
        radera.setOnMouseEntered(new HiglightHandler());
        radera.setOnMouseExited(new RemoveHiglightHandler());
        root.setBottom(bottomFlowPane);

        VBox center = new VBox();
        center.setPadding(new Insets(30));
        center.setSpacing(10);
        Label topLabel = new Label("Tal och enhet som ska omvandlas");
        topLabel.setStyle("-fx-font-size: 20");
        HBox firstInput = new HBox();
        firstInput.setSpacing(10);
        firstTextField = new TextField();
        firstTextField.setMaxWidth(200);
        firstComboBox = new ComboBox();
        firstComboBox.setPromptText("Omvandla från");
        firstComboBox.getItems().addAll(
                "Kilogram (kg)",
                "Pounds (lb)"

        );
        firstInput.getChildren().addAll(firstTextField, firstComboBox);
        Label middleLabel = new Label("Omvandlat tal och enhet");
        middleLabel.setStyle("-fx-font-size: 20");
        HBox secondInput = new HBox();
        secondInput.setSpacing(10);
        secondTextField = new TextField();
        secondTextField.setMaxWidth(200);
        secondComboBox = new ComboBox();
        secondComboBox.setPromptText("Omvandla till");
        secondComboBox.getItems().addAll(
                "Kilogram (kg)",
                "Pounds (lb)"
        );
        secondInput.getChildren().addAll(secondTextField, secondComboBox);
        Label bottomLabel = new Label("Resultat");
        bottomLabel.setStyle("-fx-font-size: 20");
        bottomLabel.setPadding(new Insets(20));
        resultatLabel = new Label("0 kilogram (kg) = 0 pounds (lb)");
        resultatLabel.setStyle("-fx-font-size: 15;" + "-fx-font-weight: bold");
        resultatLabel.setPadding(new Insets(20));
        center.getChildren().addAll(topLabel, firstInput, middleLabel, secondInput, bottomLabel, resultatLabel);
        root.setCenter(center);
        stage.setTitle("Viktomvandlare");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    class RaderaHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            firstTextField.clear();
            secondTextField.clear();
            firstComboBox.getSelectionModel().clearSelection();
            secondComboBox.getSelectionModel().clearSelection();
            resultatLabel.setText("0 kilogram (kg) = 0 pounds (lb)");
        }
    }

    class OmvandlaHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            double value = 0;
            if (firstComboBox.getSelectionModel().isSelected(0)
                    && secondComboBox.getSelectionModel().isSelected(1)) {
                value = kgToLb(Double.parseDouble(firstTextField.getText()));
                secondTextField.setText(String.valueOf(numberFormat.format(value)));
                resultatLabel.setText(firstTextField.getText() + " kilogram (kg) = " + numberFormat.format(value) + " pounds (lb)");
            }
            if (firstComboBox.getSelectionModel().isSelected(1)
                    && secondComboBox.getSelectionModel().isSelected(0)) {
                value = lbToKg(Double.parseDouble(firstTextField.getText()));
                secondTextField.setText(String.valueOf(numberFormat.format(value)));
                resultatLabel.setText(firstTextField.getText() + " pounds (lb) = " + numberFormat.format(value) + " kilogram (kg)");
            }
        }
    }

    class HiglightHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent mouseEvent) {
            if(mouseEvent.getSource().equals(omvandla)) {
                omvandla.setStyle("-fx-background-color: indigo;" +
                        "-fx-font-weight: bold");
            }else {
                radera.setStyle("-fx-background-color: indigo;" +
                        "-fx-font-weight: bold");
            }
        }
    }
    class RemoveHiglightHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent mouseEvent) {
            if(mouseEvent.getSource().equals(omvandla)) {
                omvandla.setStyle("-fx-background-color: blue;" +
                        "-fx-font-weight: bold");
            }else{
                radera.setStyle("-fx-background-color: blue;" +
                        "-fx-font-weight: bold");
            }

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

