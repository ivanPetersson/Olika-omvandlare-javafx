import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class TextEditor extends Application {

    private TextField inputOldWord;
    private TextField inputNewWord;
    private TextArea textArea;
    private Button swapWordBtn;
    private Button eraseBtn;
    private ArrayList<String> text;
    private Scanner scanner;
    Stage primaryStage = new Stage();



    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPrefWidth(600);
        root.setPrefHeight(400);

        FlowPane bottom = new FlowPane();
        swapWordBtn = new Button("Byt ord");
        swapWordBtn.setStyle("-fx-background-color: lightblue;" + "-fx-font-weight: bold");
        swapWordBtn.setPadding(new Insets(20));
        swapWordBtn.setPrefWidth(150);
        swapWordBtn.setOnAction(new SwapHandler());
        swapWordBtn.setOnMouseEntered(new HighlightOn());
        swapWordBtn.setOnMouseExited(new HighlightOff());

        eraseBtn = new Button("Återställ");
        eraseBtn.setStyle("-fx-background-color: lightblue;" + "-fx-font-weight: bold");
        eraseBtn.setPadding(new Insets(20));
        eraseBtn.setPrefWidth(150);
        eraseBtn.setOnAction(new EraseHandler());
        eraseBtn.setOnMouseEntered(new HighlightOn());
        eraseBtn.setOnMouseExited(new HighlightOff());
        bottom.setHgap(20);
        bottom.setVgap(20);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(20));
        bottom.getChildren().addAll(swapWordBtn, eraseBtn);
        root.setBottom(bottom);

        FlowPane top = new FlowPane();
        top.setAlignment(Pos.CENTER);
        top.setHgap(5);
        top.setPadding(new Insets(10));
        inputNewWord = new TextField();
        inputNewWord.setPromptText("Vilket ord vill du byta till?");
        inputOldWord = new TextField();
        inputOldWord.setPromptText("Vilket ord vill du ändra?");
        Label label1 = new Label("Gamla ordet: ");
        label1.setStyle("-fx-font-weight: bold");
        Label label2 = new Label("Nya ordet: ");
        label2.setStyle("-fx-font-weight: bold");
        top.getChildren().addAll(label1, inputOldWord, label2, inputNewWord);
        root.setTop(top);


        textArea = new TextArea();
        textArea.setPromptText("Skriv in en text här...");
        root.setCenter(textArea);


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Text editor");
        stage.show();
    }

    class EraseHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            textArea.clear();
            inputNewWord.clear();
            inputOldWord.clear();
        }
    }

    class SwapHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            StringBuilder sb = new StringBuilder();
            scanner = new Scanner(textArea.getText());
            String oldWord = inputOldWord.getText();
            String newWord = inputNewWord.getText();
            text = new ArrayList<String>();
            while (scanner.hasNext()) {
                text.add(scanner.next());
            }
            for (int i = 0; i < text.size(); i++) {
                if (text.get(i).equalsIgnoreCase(oldWord)) {
                    text.remove(i);
                    text.add(i, newWord);
                }
            }
            for(String s : text){
                sb.append(s + " ");
            }
            textArea.setText(sb.toString());
        }
    }

    class HighlightOn implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent mouseEvent) {
            if(mouseEvent.getSource().equals(eraseBtn)) {
                eraseBtn.setStyle("-fx-background-color: pink;" + "-fx-font-weight: bold");
            }else {
                swapWordBtn.setStyle("-fx-background-color: pink;" + "-fx-font-weight: bold");
            }
        }
    }
    class HighlightOff implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent mouseEvent) {
            if(mouseEvent.getSource().equals(eraseBtn)) {
                eraseBtn.setStyle("-fx-background-color: lightblue;" + "-fx-font-weight: bold");
            }else{
                swapWordBtn.setStyle("-fx-background-color: lightblue;" + "-fx-font-weight: bold");
            }

        }
    }
}