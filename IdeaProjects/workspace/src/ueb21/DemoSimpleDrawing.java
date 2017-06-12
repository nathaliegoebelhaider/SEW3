package ueb21;

/**
 * Created by Nathalie on 07.04.2017.
 */
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



/**
 * Demonstriert ein triviales Zeichnen auf einer Canvas
 *
 * @author DI Franz Breunig, Februar 2017
 */
public class DemoSimpleDrawing extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("JavaFX Demo: simple drawing on canvas");

        Canvas canvas = new Canvas(600, 600);

        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(new BorderPane(canvas)));
        primaryStage.show();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawSierpinsikiTriangle(new Point2D(300, 10), new Point2D(590, 590),
                new Point2D(10, 590), 10, gc) ;
    }

    public void drawSierpinsikiTriangle(Point2D a, Point2D b, Point2D c, int min, GraphicsContext gc) {
            if(a.distance(b) < min){
                return;
            }

            gc.strokeLine(a.getX(),a.getY(), b.getX(), b.getY());
            gc.strokeLine(b.getX(),b.getY(),c.getX(),c.getY());
            gc.strokeLine(c.getX(),c.getY(),a.getX(),a.getY());

            Point2D d = a.midpoint(b);
            Point2D e = b.midpoint(c);
            Point2D f = c.midpoint(a);

            drawSierpinsikiTriangle(a,d,f,min,gc);
            drawSierpinsikiTriangle(d,b,e,min,gc);
            drawSierpinsikiTriangle(f,e,c,min,gc);
    }


}
