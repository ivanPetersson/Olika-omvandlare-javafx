import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class MainMenu extends Application {

    Button tempBtn = new Button("Tempraturomvandlare");
    Button viktBtn = new Button("Viktomvandlare");
    Button editorBtn = new Button("Texteditor");
    FlowPane center = new FlowPane();


    @Override
    public void start(Stage stage) throws Exception {

        BorderPane root = new BorderPane();
        root.setPrefWidth(500);
        root.setPrefHeight(630);
        tempBtn.setOnAction(new LaunchHandler());
        viktBtn.setOnAction(new LaunchHandler());
        editorBtn.setOnAction(new LaunchHandler());
        center.setHgap(20);
        center.setPadding(new Insets(10));
        center.getChildren().addAll(tempBtn,viktBtn, editorBtn);
        root.setCenter(center);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    class LaunchHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(tempBtn)) {
                Omvandlare omvandlare = new Omvandlare();
                try {
                    omvandlare.start(omvandlare.primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (actionEvent.getSource().equals(viktBtn)) {
                ViktOmvandlare viktOmvandlare = new ViktOmvandlare();
                try {
                    viktOmvandlare.start(viktOmvandlare.primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (actionEvent.getSource().equals(editorBtn)) {
                TextEditor textEditor = new TextEditor();
                try {
                    textEditor.start(textEditor.primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
