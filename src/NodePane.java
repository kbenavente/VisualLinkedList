import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class NodePane extends VBox {

    private String category1Content;
    private String category2Content;
    private double category3Content;


    public NodePane(String category1Content, String category2Content, double category3Content) {

        super(4);

        this.category1Content = category1Content;
        this.category2Content = category2Content;
        this.category3Content = category3Content;

        this.setPrefHeight(140);
        this.setPrefWidth(175);

        this.setStyle("-fx-background-color: #57c6c9; -fx-text-fill: #1f3a54; -fx-background-radius: 10; -fx-padding: 20px;");

        Label lbPlanet = new Label("Planet:         " + this.category1Content);
        lbPlanet.prefWidthProperty().bind(this.widthProperty());

        Label lbMoon = new Label("Moon:           " + this.category2Content);
        lbMoon.prefWidthProperty().bind(this.widthProperty());

        Label lbMoonMass = new Label("Moon Mass:  " + this.category3Content + " x 10^23 kg");
        lbMoonMass.prefWidthProperty().bind(this.widthProperty());

        this.getChildren().addAll(lbPlanet, lbMoon, lbMoonMass);

    }

}
