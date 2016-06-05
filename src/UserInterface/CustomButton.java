package UserInterface;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomButton extends Button {
    private CustomButton(double x, double y, String text) {
        super.setLayoutX(x);
        super.setLayoutY(y);
        super.setText(text);
        super.setStyle("-fx-background-color: #009f8d; -fx-border-color: white;");
        super.setTextFill(Color.WHITE);
        super.setFont(Font.font("Harrington", 15));
    }

    public CustomButton(double x, double y, String text, int s) {
        this(x, y, text);
        super.setStyle("-fx-background-color: #009f8d; -fx-border-color: white;");
        super.setTextFill(Color.WHITE);
        this.setFont(Font.font("Harrington", s));
    }

    public CustomButton(String text) {
        super.setText(text);
        super.setStyle("-fx-background-color: #009f8d; -fx-border-color: white;");
        super.setTextFill(Color.WHITE);
        this.setFont(Font.font("Harrington", 15));
    }


}
