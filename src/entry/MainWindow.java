package entry;

import controller.simple2.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.RentalSys;
import view.simpleView2.ViewModel2;

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Demo");
        ViewModel2 root = new ViewModel2();
        RentalSys rentalSys = new RentalSys();
        Controller controller = new Controller(rentalSys,root);
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
