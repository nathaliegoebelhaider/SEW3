package ueb22.ueb22_2;
/**
 * Created by Nathalie on 27.04.2017.
 */

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Pferderennen extends Application {
    Stack<ProgressBar> pferde = new Stack<>();
    List<Thread>  threads = new ArrayList<>();

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) {
       //GUI
        stage.setTitle("Pferderennen");
        stage.setWidth(800);
        stage.setHeight(400);
        stage.setMaxWidth(800);
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 400, 46); // w, h
        stage.setScene(scene);



        Label label = new Label("Pferderennen - mit Threads");

        Button plus = new Button("+ ");
        Button minus = new Button("-  ");
        Button start = new Button("start");

       TextArea textArea = new TextArea();
       textArea.setEditable(false);

       textArea.setMaxHeight(200);
       textArea.setMaxWidth(800);

        ProgressBar thread1 = new ProgressBar();
        thread1.setMaxWidth(Double.MAX_VALUE);
        pferde.push(thread1);

        VBox vbox_plusminus = new VBox();
        vbox_plusminus.setSpacing(10);
        vbox_plusminus.getChildren().addAll(plus,minus);


        VBox progress = new VBox();
        progress.setSpacing(10);
        progress.getChildren().addAll(thread1);

        borderPane.setTop(label);
        borderPane.setBottom(textArea);
        borderPane.setLeft(vbox_plusminus);
        borderPane.setRight(start);
        borderPane.setCenter(progress);
        borderPane.setPadding(new Insets(0,0,0,0));

        // Events

        plus.setOnAction(event -> {
            ProgressBar newProgress = new ProgressBar();
            newProgress.setMaxWidth(Double.MAX_VALUE);
            pferde.push(newProgress);
            progress.getChildren().setAll(pferde);
            textArea.appendText("Pferd hinzugefügt\n");

        });

        minus.setOnAction(event -> {
            if(pferde.size() > 1) {
                pferde.pop();
                progress.getChildren().setAll(pferde);
                textArea.appendText("Pferd entfernt\n");

            }
        });

        start.setOnAction(event ->  {
            textArea.clear();
            textArea.appendText("Adding " + pferde.size() + " Bars\n Ergebnis:\n");
            int number = 1;
            for (int i = 0; i < pferde.size() ; i++) {
                Task<Number> task = new Task<Number>() {
                    @Override
                    protected Number call() throws Exception {
                        int i = 0;
                        while (i < 100) {
                            i += 1 + Math.random() * 3;

                            updateProgress(i, 100);

                            Thread.sleep((long) (50 + Math.random() * 151));
                        }
                        textArea.appendText("Horse" + Thread.currentThread().getName() + " ist im Ziel angekommen\n");
                        return null;
                    }
                };

                ProgressBar thredpferd = pferde.get(i);
                thredpferd.progressProperty().bind(task.progressProperty());
                // Das heißt du setzt den Progress der Bar gleich dem Progress des Task, den du im Thread aktualisierst.
                //Task.progress = ProgressBar.progress

                Thread thread  = new Thread(task);
                thread.setName("/Thread-" + number);
                number ++;
                thread.start();
                threads.add(thread);


            }


        });

        stage.show();



    }
}
