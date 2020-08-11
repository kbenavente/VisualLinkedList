import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class LinkedListsGUI extends Application {

    private LinkedList<String, String, Double> list = new LinkedList<>();

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

//        list.add("Saturn", "Titan", 3200.0);
//        list.add("Saturn", "Enceladus", 313.0);
//        list.add("Jupiter", "Titan", 1939.7);
//        list.add("Jupiter", "Io", 2200.0);
//        list.add("Earth", "Moon", 2200.0);
//        list.add("Neptune", "Triton", 1681.9);

        // Initial Setup Page Start

        VBox setupPage = new VBox(30);
        setupPage.prefWidthProperty().bind(primaryStage.widthProperty());
        setupPage.prefHeightProperty().bind(primaryStage.heightProperty());
        setupPage.setAlignment(Pos.CENTER);
        setupPage.setStyle("-fx-padding: 30px; -fx-background-color: #1f3a54;");

        Label lbInstructions = new Label("Enter the number of the category your LinkedList should be grouped by:");
        lbInstructions.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 30px;");

        TextField tfCategoryNumber = new TextField();
        tfCategoryNumber.setStyle("-fx-font-size: 28px;");
        tfCategoryNumber.setAlignment(Pos.CENTER);

        Button btSubmitGrouping = new Button("Submit");
        btSubmitGrouping.getStyleClass().add("btSubmit");

        setupPage.getChildren().addAll(lbInstructions, tfCategoryNumber, btSubmitGrouping);


        // Initial Setup Page End

        VBox container = new VBox();
        container.prefHeightProperty().bind(primaryStage.heightProperty());
        container.prefWidthProperty().bind(primaryStage.widthProperty());

        HBox navigationContainer = new HBox();
        navigationContainer.prefWidthProperty().bind(primaryStage.widthProperty()); // Width of Navigation Container will be 10% of the Stage Width
        navigationContainer.prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.1)); // Width of Navigation Container will be 10% of the Stage Width

        HBox contentContainer = new HBox();
        contentContainer.prefWidthProperty().bind(primaryStage.widthProperty()); // Width of Navigation Container will be 10% of the Stage Width
        contentContainer.prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.9)); // Width of Navigation Container will be 10% of the Stage Width


        // Start Navigation Bar Components

        Label lbProjectTitle = new Label("| L i n k e d  L i s t s |");
        lbProjectTitle.getStyleClass().add("lbProjectTitle");
        lbProjectTitle.prefWidthProperty().bind(navigationContainer.widthProperty().multiply(0.3)); // lbProjectTitle width bound with Navigation Container (20% of the width)
        lbProjectTitle.prefHeightProperty().bind(navigationContainer.heightProperty()); // lbProjectTitle height bound with Navigation Container
        lbProjectTitle.setAlignment(Pos.CENTER);

        Button btDisplayCurrentLinkedList = new Button("Current List");
        btDisplayCurrentLinkedList.getStyleClass().add("btNavigation");
        btDisplayCurrentLinkedList.prefWidthProperty().bind(navigationContainer.widthProperty().multiply(11.67));
        btDisplayCurrentLinkedList.prefHeightProperty().bind(navigationContainer.heightProperty());

        Button btGet = new Button("Get");
        btGet.getStyleClass().add("btNavigation");
        btGet.prefWidthProperty().bind(navigationContainer.widthProperty().multiply(11.67));
        btGet.prefHeightProperty().bind(navigationContainer.heightProperty());

        Button btAdd = new Button("Add");
        btAdd.getStyleClass().add("btNavigation");
        btAdd.prefWidthProperty().bind(navigationContainer.widthProperty().multiply(11.67));
        btAdd.prefHeightProperty().bind(navigationContainer.heightProperty());

        Button btDelete = new Button("Delete");
        btDelete.getStyleClass().add("btNavigation");
        btDelete.prefWidthProperty().bind(navigationContainer.widthProperty().multiply(11.67));
        btDelete.prefHeightProperty().bind(navigationContainer.heightProperty());

        Button btImport = new Button("Import");
        btImport.getStyleClass().add("btNavigation");
        btImport.prefWidthProperty().bind(navigationContainer.widthProperty().multiply(11.67));
        btImport.prefHeightProperty().bind(navigationContainer.heightProperty());

        Button btClear = new Button("Clear");
        btClear.getStyleClass().add("btNavigation");
        btClear.prefWidthProperty().bind(navigationContainer.widthProperty().multiply(11.67));
        btClear.prefHeightProperty().bind(navigationContainer.heightProperty());

        navigationContainer.getChildren().addAll(lbProjectTitle, btDisplayCurrentLinkedList, btGet, btAdd, btDelete, btImport, btClear);

        // End Navigation Bar Components

        // Start Main Content Component

        VBox controlMenu = new VBox();
        controlMenu.getStyleClass().add("controlMenu");
        controlMenu.prefWidthProperty().bind(lbProjectTitle.widthProperty());
        controlMenu.prefHeightProperty().bind(contentContainer.heightProperty());

        Label lbTaskTitle = new Label("> Displaying Current List");
        lbTaskTitle.getStyleClass().add("lbTaskTitle");

        VBox contentPlaceHolder = new VBox(30);
        contentPlaceHolder.prefWidthProperty().bind(controlMenu.widthProperty());
        contentPlaceHolder.setStyle("-fx-padding: 20px 55px 20px 20px;");

        TextField tfSetGroupingCategory = new TextField();
        tfSetGroupingCategory.setPromptText("Grouping Category Number");

        Button btSubmitGroupingCategory = new Button("Set Grouping");
        btSubmitGroupingCategory.getStyleClass().add("btSubmit");

        contentPlaceHolder.getChildren().addAll(tfSetGroupingCategory, btSubmitGroupingCategory);


        controlMenu.getChildren().addAll(lbTaskTitle, contentPlaceHolder);

        // Start LinkedList Components

        VBox linkedListContent = new VBox();
        linkedListContent.getStyleClass().add("linkedListContent");
        linkedListContent.prefWidthProperty().bind(btDisplayCurrentLinkedList.widthProperty().multiply(6));
        linkedListContent.prefHeightProperty().bind(contentContainer.heightProperty());

        Label lbLinkedListSize = new Label("List Size: ");
        lbLinkedListSize.prefWidthProperty().bind(linkedListContent.widthProperty());
        lbLinkedListSize.prefHeightProperty().bind(linkedListContent.heightProperty().multiply(0.05));
        lbLinkedListSize.setAlignment(Pos.CENTER_RIGHT);
        lbLinkedListSize.getStyleClass().add("lbLinkedListSize");

        ScrollPane linkedListContentScrollContainer = new ScrollPane();
        linkedListContentScrollContainer.prefWidthProperty().bind(linkedListContent.widthProperty());
        linkedListContentScrollContainer.prefHeightProperty().bind(linkedListContent.heightProperty());
        linkedListContentScrollContainer.setStyle("-fx-background-color: #e1e2e3; -fx-background: #e1e2e3;");

        HBox linkedListDisplay = populateLinkedListDisplay(list);
        linkedListDisplay.prefWidthProperty().bind(linkedListContentScrollContainer.widthProperty().subtract(5));
        linkedListDisplay.prefHeightProperty().bind(linkedListContentScrollContainer.heightProperty().subtract(5));
        linkedListDisplay.setStyle("-fx-padding: 20px; -fx-background-color: #e1e2e3;");

        linkedListContentScrollContainer.setContent(linkedListDisplay);

        linkedListContent.getChildren().addAll(lbLinkedListSize, linkedListContentScrollContainer);

        contentContainer.getChildren().addAll(controlMenu, linkedListContent);

        // Start LinkedList Components

        // End Main Content Components

        //lbProjectTitle.setVisible(false);
        //lbProjectTitle.setManaged(false);


        container.getChildren().addAll(navigationContainer, contentContainer);

        Scene mainScene = new Scene(setupPage, 1248, 800);
        mainScene.getStylesheets().clear();
		mainScene.getStylesheets().add(getClass().getResource("Resources/linkedList_stylesheet.css").toExternalForm());

        primaryStage.setTitle("Linked Lists");
        primaryStage.setScene(mainScene);
        primaryStage.setMinWidth(1248);
        primaryStage.setMinHeight(800);
        primaryStage.show();

        btSubmitGrouping.setOnAction(event -> {

            list = new LinkedList<>(Integer.parseInt(tfCategoryNumber.getText()), "Planet", "Moon", "Moon Mass");
            lbLinkedListSize.setText("List Size: " + list.size());

            mainScene.setRoot(container);

        });


        btDisplayCurrentLinkedList.setOnAction(e -> {

            lbTaskTitle.setText("> Displaying Current List");

            lbLinkedListSize.setText("List Size: " + list.size());

            HBox newLinkedListDisplay = populateLinkedListDisplay(list);

            if(newLinkedListDisplay.getChildren().size() == 1) {

                newLinkedListDisplay.prefWidthProperty().bind(linkedListContentScrollContainer.widthProperty().subtract(5));
                newLinkedListDisplay.prefHeightProperty().bind(linkedListContentScrollContainer.heightProperty().subtract(5));
                newLinkedListDisplay.setStyle("-fx-padding: 20px; -fx-background-color: #e1e2e3;");

            } else {

                newLinkedListDisplay.setStyle("-fx-padding: 20px; -fx-background-color: #e1e2e3;");

            }

            Pane placeHolder = new Pane();
            controlMenu.getChildren().remove(1);
            controlMenu.getChildren().add(placeHolder);

            linkedListContentScrollContainer.setContent(newLinkedListDisplay);


        });

        btGet.setOnAction(e -> {

            lbTaskTitle.setText("> Get");

            VBox getContentContainer = new VBox(30);
            getContentContainer.prefWidthProperty().bind(controlMenu.widthProperty());
            getContentContainer.setStyle("-fx-padding: 20px 55px 20px 20px;");

            TextField tfColumn = new TextField();
            tfColumn.setPromptText("Column Index");

            TextField tfRow = new TextField();
            tfRow.setPromptText("Row Index");

            TextField tfCategoryNum = new TextField();
            tfCategoryNum.setPromptText("Category Number");

            Button btSubmit = new Button("Get");
            btSubmit.getStyleClass().add("btSubmit");

            getContentContainer.getChildren().addAll(tfColumn, tfRow, tfCategoryNum, btSubmit);

            controlMenu.getChildren().remove(1);
            controlMenu.getChildren().add(getContentContainer);

            btSubmit.setOnAction(f -> {

                if(tfRow.getText().isEmpty()) {

                    int columnIndex = Integer.parseInt(tfColumn.getText());
                    int categoryNumber = Integer.parseInt(tfCategoryNum.getText());

                    Label retrievedInformation = new Label(list.get(columnIndex, categoryNumber));
                    retrievedInformation.prefWidthProperty().bind(linkedListContentScrollContainer.widthProperty());
                    retrievedInformation.setAlignment(Pos.CENTER);
                    retrievedInformation.getStyleClass().add("lbEmptyList");

                    linkedListContentScrollContainer.setContent(retrievedInformation);

                } else {

                    int columnIndex = Integer.parseInt(tfColumn.getText());
                    int rowIndex = Integer.parseInt(tfRow.getText());
                    int categoryNumber = Integer.parseInt(tfCategoryNum.getText());

                    Label retrievedInformation = new Label(list.get(columnIndex, rowIndex, categoryNumber));
                    retrievedInformation.prefWidthProperty().bind(linkedListContentScrollContainer.widthProperty());
                    retrievedInformation.setAlignment(Pos.BASELINE_CENTER);
                    retrievedInformation.getStyleClass().add("lbEmptyList");

                    linkedListContentScrollContainer.setContent(retrievedInformation);

                }

            });


        });

        btAdd.setOnAction(e -> {

            lbTaskTitle.setText("> Add");

            VBox addContentContainer = new VBox(30);
            addContentContainer.prefWidthProperty().bind(controlMenu.widthProperty());
            addContentContainer.setStyle("-fx-padding: 20px 55px 20px 20px;");

            TextField tfPlanet = new TextField();
            tfPlanet.setPromptText("Planet");

            TextField tfMoon = new TextField();
            tfMoon.setPromptText("Moon");

            TextField tfMoonMass = new TextField();
            tfMoonMass.setPromptText("Moon Mass");

            Button btAddNode = new Button("Add");
            btAddNode.getStyleClass().add("btSubmit");

            addContentContainer.getChildren().addAll(tfPlanet, tfMoon, tfMoonMass, btAddNode);

            controlMenu.getChildren().remove(1);
            controlMenu.getChildren().add(addContentContainer);

            btAddNode.setOnAction(f -> {

                list.add(tfPlanet.getText(), tfMoon.getText(), Double.parseDouble(tfMoonMass.getText()));

                tfPlanet.clear();
                tfPlanet.setPromptText("Planet");

                tfMoon.clear();
                tfMoon.setPromptText("Moon");

                tfMoonMass.clear();
                tfMoonMass.setPromptText("Moon Mass");

                lbLinkedListSize.setText("List Size: " + list.size());

                HBox newLinkedListDisplay = populateLinkedListDisplay(list);
                newLinkedListDisplay.setStyle("-fx-padding: 20px;");
                //newLinkedListDisplay.prefWidthProperty().bind(linkedListContentScrollContainer.widthProperty().subtract(5));
                //newLinkedListDisplay.prefHeightProperty().bind(linkedListContentScrollContainer.heightProperty().subtract(5));


                linkedListContentScrollContainer.setContent(newLinkedListDisplay);

            });

        });

        btDelete.setOnAction(e -> {

            lbTaskTitle.setText("> Delete");

            VBox deleteContentContainer = new VBox(30);
            deleteContentContainer.prefWidthProperty().bind(controlMenu.widthProperty());
            deleteContentContainer.setStyle("-fx-padding: 20px 55px 20px 20px;");

            TextField tfColumIndex = new TextField();
            tfColumIndex.setPromptText("Column Index");

            TextField tfRowIndex = new TextField();
            tfRowIndex.setPromptText("Row Index");

            Button btSubmitDelete = new Button("Delete");
            btSubmitDelete.getStyleClass().add("btSubmit");

            Button btDeleteFirst = new Button("Delete First");
            btDeleteFirst.getStyleClass().add("btSubmit");

            Button btDeleteLast = new Button("Delete Last");
            btDeleteLast.getStyleClass().add("btSubmit");

            deleteContentContainer.getChildren().addAll(tfColumIndex, tfRowIndex, btSubmitDelete, btDeleteFirst, btDeleteLast);

            controlMenu.getChildren().remove(1);
            controlMenu.getChildren().add(deleteContentContainer);

            btSubmitDelete.setOnAction(f -> {

                list.delete(Integer.parseInt(tfColumIndex.getText()), Integer.parseInt(tfRowIndex.getText()));

                tfColumIndex.clear();
                tfColumIndex.setPromptText("Column Index");

                tfRowIndex.clear();
                tfRowIndex.setPromptText("Row Index");

                lbLinkedListSize.setText("List Size: " + list.size());

                HBox newLinkedListDisplay = populateLinkedListDisplay(list);
                newLinkedListDisplay.setStyle("-fx-padding: 20px;");

                if(list.size() == 0) {

                    newLinkedListDisplay.prefWidthProperty().bind(linkedListContentScrollContainer.widthProperty().subtract(5));
                    newLinkedListDisplay.prefHeightProperty().bind(linkedListContentScrollContainer.heightProperty().subtract(5));

                }

                linkedListContentScrollContainer.setContent(newLinkedListDisplay);

            });

            btDeleteFirst.setOnAction(f -> {

                list.deleteFirst();

                lbLinkedListSize.setText("List Size: " + list.size());

                HBox newLinkedListDisplay = populateLinkedListDisplay(list);
                newLinkedListDisplay.setStyle("-fx-padding: 20px;");

                if(list.size() == 0) {

                    newLinkedListDisplay.prefWidthProperty().bind(linkedListContentScrollContainer.widthProperty().subtract(5));
                    newLinkedListDisplay.prefHeightProperty().bind(linkedListContentScrollContainer.heightProperty().subtract(5));

                }

                linkedListContentScrollContainer.setContent(newLinkedListDisplay);

            });

            btDeleteLast.setOnAction(f -> {

                list.deleteLast();

                lbLinkedListSize.setText("List Size: " + list.size());

                HBox newLinkedListDisplay = populateLinkedListDisplay(list);
                newLinkedListDisplay.setStyle("-fx-padding: 20px;");

                if(list.size() == 0) {

                    newLinkedListDisplay.prefWidthProperty().bind(linkedListContentScrollContainer.widthProperty().subtract(5));
                    newLinkedListDisplay.prefHeightProperty().bind(linkedListContentScrollContainer.heightProperty().subtract(5));

                }

                linkedListContentScrollContainer.setContent(newLinkedListDisplay);

            });

        });

        btImport.setOnAction(e -> {

            lbTaskTitle.setText("> Import");

            VBox importContentContainer = new VBox(30);
            importContentContainer.prefWidthProperty().bind(controlMenu.widthProperty());
            importContentContainer.setStyle("-fx-padding: 20px 55px 20px 20px;");

            TextField tfFileName = new TextField();
            tfFileName.setPromptText("File Name");

            Button btFile = new Button("Import File");
            btFile.getStyleClass().add("btSubmit");

            importContentContainer.getChildren().addAll(tfFileName, btFile);

            controlMenu.getChildren().remove(1);
            controlMenu.getChildren().add(importContentContainer);

            btFile.setOnAction(f -> {


                try {

                    File file = new File(tfFileName.getText());

                    Scanner chosenCSV = new Scanner(file);

                    list.setCategory1Label(chosenCSV.nextLine());
                    list.setCategory2Label(chosenCSV.nextLine());
                    list.setCategory3Label(chosenCSV.nextLine());
                    chosenCSV.nextLine();

                    while (chosenCSV.hasNextLine()) {

                        String[] nodeInfo = chosenCSV.nextLine().split(",");

                        list.add(nodeInfo[0], nodeInfo[1].substring(1), Double.parseDouble(nodeInfo[2].substring(1)));


                    }

                    lbLinkedListSize.setText("List Size: " + list.size());

                    HBox newLinkedListDisplay = populateLinkedListDisplay(list);
                    newLinkedListDisplay.setStyle("-fx-padding: 20px;");

                    linkedListContentScrollContainer.setContent(newLinkedListDisplay);

                } catch (FileNotFoundException ex) {

                    // No File Found

                }



            });


        });

        btClear.setOnAction(e -> {

            lbTaskTitle.setText("> Clear");

            list.clear();

            lbLinkedListSize.setText("List Size: " + list.size());

            HBox newLinkedListDisplay = populateLinkedListDisplay(list);
            newLinkedListDisplay.prefWidthProperty().bind(linkedListContentScrollContainer.widthProperty().subtract(5));
            newLinkedListDisplay.prefHeightProperty().bind(linkedListContentScrollContainer.heightProperty().subtract(5));


            linkedListContentScrollContainer.setContent(newLinkedListDisplay);

        });

        btDisplayCurrentLinkedList.setOnAction(e -> {

            VBox displayCurrentList = new VBox(30);
            displayCurrentList.prefWidthProperty().bind(controlMenu.widthProperty());
            displayCurrentList.setStyle("-fx-padding: 20px 55px 20px 20px;");

            TextField tfSetGroupingCategory1 = new TextField();
            tfSetGroupingCategory1.setPromptText("Grouping Category Number");

            Button btSubmitGroupingCategory1 = new Button("Set Grouping");
            btSubmitGroupingCategory1.getStyleClass().add("btSubmit");

            displayCurrentList.getChildren().addAll(tfSetGroupingCategory1, btSubmitGroupingCategory1);

            controlMenu.getChildren().remove(1);
            controlMenu.getChildren().add(displayCurrentList);

            btSubmitGroupingCategory1.setOnAction(f -> {

                list.regroup(Integer.parseInt(tfSetGroupingCategory1.getText()));

                tfSetGroupingCategory1.clear();
                tfSetGroupingCategory1.setPromptText("Grouping Category Number");

                HBox newLinkedListDisplay = populateLinkedListDisplay(list);
                newLinkedListDisplay.setStyle("-fx-padding: 20px;");

                if(list.size() == 0) {

                    newLinkedListDisplay.prefWidthProperty().bind(linkedListContentScrollContainer.widthProperty().subtract(5));
                    newLinkedListDisplay.prefHeightProperty().bind(linkedListContentScrollContainer.heightProperty().subtract(5));

                }

                linkedListContentScrollContainer.setContent(newLinkedListDisplay);

            });

        });

        btSubmitGroupingCategory.setOnAction(f -> {

            list.regroup(Integer.parseInt(tfSetGroupingCategory.getText()));

            tfSetGroupingCategory.clear();
            tfSetGroupingCategory.setPromptText("Grouping Category Number");

            HBox newLinkedListDisplay = populateLinkedListDisplay(list);
            newLinkedListDisplay.setStyle("-fx-padding: 20px;");

            if(list.size() == 0) {

                newLinkedListDisplay.prefWidthProperty().bind(linkedListContentScrollContainer.widthProperty().subtract(5));
                newLinkedListDisplay.prefHeightProperty().bind(linkedListContentScrollContainer.heightProperty().subtract(5));

            }

            linkedListContentScrollContainer.setContent(newLinkedListDisplay);

        });

    }

    private static HBox populateLinkedListDisplay(LinkedList<String, String, Double> list) {

        HBox linkedListContainer = new HBox(10);
        linkedListContainer.setAlignment(Pos.TOP_CENTER);

        if(list.size() != 0) {

            int row = 0;
            int col = 0;

            boolean endOfMainList = false;
            boolean endOfSublist = false;

            while (!endOfMainList) {

                VBox columnContainer = new VBox(10);
                columnContainer.setStyle("-fx-background-color: #e1e2e3;");
                columnContainer.setPrefWidth(300);
                columnContainer.setAlignment(Pos.TOP_CENTER);

                if (col >= 1) {

                    ImageView arrows = new ImageView("Resources/opposite-pointing-arrows.png");
                    arrows.setFitHeight(100);
                    arrows.setFitWidth(100);

                    linkedListContainer.getChildren().add(arrows);

                }

                try {

                    String category1Content = list.get(col, 1);
                    String category2Content = list.get(col, 2);
                    String category3Content = list.get(col, 3);

                    NodePane newNode = new NodePane(category1Content, category2Content, Double.parseDouble(category3Content));

                    columnContainer.getChildren().add(newNode);

                    row++;

                } catch (IndexOutOfBoundsException ex) {

                    endOfMainList = true;
                    linkedListContainer.getChildren().remove(linkedListContainer.getChildren().size() - 1);

                }

                while (!endOfSublist && !endOfMainList) {

                    try {

                        String category1Content = list.get(col, row, 1);
                        String category2Content = list.get(col, row, 2);
                        String category3Content = list.get(col, row, 3);

                        NodePane newNode = new NodePane(category1Content, category2Content, Double.parseDouble(category3Content));

                        ImageView arrow = new ImageView("Resources/arrow-pointing-down.png");
                        arrow.setFitHeight(100);
                        arrow.setFitWidth(60);

                        columnContainer.getChildren().addAll(arrow, newNode);

                        row++;

                    } catch (IndexOutOfBoundsException ex) {

                        endOfSublist = true;
                        row = 0;
                        col++;

                    }

                }

                endOfSublist = false;

                linkedListContainer.getChildren().add(columnContainer);

            }

            return linkedListContainer;

        } else {

            Label lbEmptyList = new Label("E M P T Y");
            lbEmptyList.getStyleClass().add("lbEmptyList");
            lbEmptyList.setAlignment(Pos.CENTER);

            linkedListContainer.getChildren().add(lbEmptyList);
            linkedListContainer.setAlignment(Pos.CENTER);

            return linkedListContainer;

        }

    }

}
