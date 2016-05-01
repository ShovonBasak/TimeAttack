package scenes;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoadingScreen {
    Stage window;
    Scene scene;
    Text companyName;
    Text presents;
    Thread thisThread;

    public LoadingScreen() {

        companyName = new Text("২ টাকার Developer");
        companyName.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        companyName.setCache(true);
        companyName.setFill(Color.RED);
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        companyName.setEffect(r);

        presents = new Text("Presents");
        presents.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        presents.setCache(true);
        presents.setFill(Color.RED);
        presents.setTranslateX(presents.getTranslateX() + 300);

    }

    public void show() {
        window = new Stage();
        VBox layout = new VBox(20, companyName, presents);
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout, 800, 600);
        window.setScene(scene);
        window.show();
    }


}
