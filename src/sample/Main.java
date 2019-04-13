package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    FileChooser mediaChooser;
    Player mediaPlayer;
    MenuBar menu;
    Menu fileMenu;
    MenuItem openItem;

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 400);

        mediaChooser = new FileChooser();

        menu = new MenuBar();
        fileMenu = new Menu("file");
        openItem = new MenuItem("open");

        fileMenu.getItems().add(openItem);
        menu.getMenus().add(fileMenu);

        openItem.setOnAction((e)->{
            try{
            File mediaFile = mediaChooser.showOpenDialog(primaryStage);
            if(mediaPlayer!=null){
                mediaPlayer.player.dispose();
            }
            System.out.println(mediaFile.getAbsolutePath() + ":" +mediaFile.toURI().toURL().toExternalForm());
            mediaPlayer = new Player(mediaFile.toURI().toURL().toExternalForm());

                root.setCenter(mediaPlayer);

        } catch(Exception e1){
            e1.printStackTrace();
        }});

        root.setTop(menu);
        primaryStage.widthProperty().addListener((obs,oldval,newVal) -> {
            if(mediaPlayer != null)
            mediaPlayer.view.setFitWidth(scene.getWidth());
        });
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
