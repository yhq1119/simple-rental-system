package view.simpleView2;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.swing.*;

public class ViewModel2 extends VBox {

    Button[] buttons;
    double b_width = 200;
    double b_height = 30;

    Text display;

    public ViewModel2() {
        init();
    }

    private void init() {
        this.display = new Text("Empty");
        this.buttons = new Button[7];
        for (int i = 0; i < 7; i++) {
            buttons[i] = new Button();
            buttons[i].setPrefSize(b_width, b_height);
        }
        buttons[0].setText("Add New Property");
        buttons[1].setText("Rent Property");
        buttons[2].setText("Return Property");
        buttons[3].setText("Perform Maintenance");
        buttons[4].setText("Finish Maintenance");
        buttons[5].setText("Display All Properties");
        buttons[6].setText("Exit");

        HBox group = new HBox();

        VBox leftPanel = new VBox();
        VBox rightPanel = new VBox();
        leftPanel.getChildren().addAll(buttons);
        rightPanel.getChildren().add(display);

        leftPanel.setAlignment(Pos.CENTER);
        rightPanel.setAlignment(Pos.CENTER);
        group.getChildren().addAll(leftPanel, rightPanel);
        group.setAlignment(Pos.CENTER);
        this.getChildren().addAll(group);
        this.setAlignment(Pos.CENTER);
    }

    public void setButtons(Button[] buttons) {
        this.buttons = buttons;
    }

    public void setB_width(double b_width) {
        this.b_width = b_width;
    }

    public void setB_height(double b_height) {
        this.b_height = b_height;
    }

    public void setDisplay(Text display) {
        this.display = display;
    }

    public Button[] getButtons() {
        return buttons;
    }

    public double getB_width() {
        return b_width;
    }

    public double getB_height() {
        return b_height;
    }

    public Text getDisplay() {
        return display;
    }
}
