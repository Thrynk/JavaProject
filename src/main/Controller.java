package main;

import calculator.*;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Controller {

    private Tokenizer tokenizer;
    private Parser parser;
    private final CalculModel calculModel = new CalculModel();

    @FXML
    private TableView<Calcul> tableView;

    @FXML
    private TableColumn<Calcul, String> calculTableColumn;

    @FXML
    private TableColumn<Calcul, String> resultTableColumn;

    @FXML
    private TextField calculText;

    @FXML
    private ListView<String> variablesListView;

    @FXML
    private ListView<String> functionsListView;

    public Controller() throws ParserException {
        this.tokenizer = new Tokenizer();
        this.tokenizer.add("sin|cos|exp|ln|sqrt", 6); // function
        this.tokenizer.add("\\+", 1); // +
        this.tokenizer.add("-", 2); // -
        this.tokenizer.add("\\*", 3); // *
        this.tokenizer.add("/", 4); // / division
        this.tokenizer.add("\\=", 10); // =
        this.tokenizer.add("[0-9]+[.,]?[0-9]*", 9); // number
        this.tokenizer.add("\\(", 7); // opening bracket
        this.tokenizer.add("\\)", 8); // closing bracket
        this.tokenizer.add("([a-zA-Z][a-zA-Z0-9_]*)\\([a-zA-Z0-9_\\(\\)\\+-/\\*]*\\)", 13); // function expression evaluated
        this.tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 5); // variable

        /*for (Token token: tokenizer.getTokens()){
            System.out.println("" + token.token + " " + token.sequence);
        }*/

        this.parser = new Parser();
    }

    public void initialize(){
        // Set columns to be responsive
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Set properties for column to be editable
        calculTableColumn.setCellValueFactory(new PropertyValueFactory<Calcul, String>("calcul"));
        calculTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Handle cell edition / updating Calcul
        calculTableColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Calcul, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Calcul, String> cellEditEvent) {
                        cellEditEvent.getTableView()
                                .getItems()
                                .get(
                                        cellEditEvent
                                                .getTablePosition()
                                                .getRow()
                                ).setCalcul(cellEditEvent.getNewValue());
                        try {
                            String result;
                            tokenizer.tokenize(cellEditEvent.getNewValue().trim().replace(" ", ""));
                            for (Token token: tokenizer.getTokens()){
                                System.out.println("" + token.getTokenName() + " " + token.sequence);
                            }
                            Node expression = parser.parse(tokenizer.getTokens());
                            if(expression != null){
                                System.out.println(expression.getValue());
                                result = String.valueOf(expression.getValue());
                                cellEditEvent.getTableView()
                                        .getItems()
                                        .get(
                                                cellEditEvent
                                                        .getTablePosition()
                                                        .getRow()
                                        )
                                        .setResult(result);
                            }
                        }
                        catch(ParserException e){
                            System.out.println(e);
                        }
                        for(Calcul calc : calculModel.getCalculs()){
                            System.out.println(calc.getResult());
                        }
                        tableView.refresh();
                    }
                }
        );

        resultTableColumn.setCellValueFactory(new PropertyValueFactory<Calcul, String>("result"));

        this.tableView.setEditable(true);
    }

    @FXML
    public void calculButtonClicked(){
        String text = calculText.getText();
        String result = "";
        try {
            this.tokenizer.tokenize(text.trim().replace(" ", ""));

            Node expression = this.parser.parse(this.tokenizer.getTokens());
            if(expression != null){
                /*System.out.println(expression.getValue());*/
                result = String.valueOf(expression.getValue());
            }
        }
        catch(ParserException e){
            System.out.println(e);
        }
        calculModel.addCalcul(text, result);
        tableView.setItems(calculModel.getCalculs());
        variablesListView.setItems(calculModel.getVariables());
        functionsListView.setItems(calculModel.getFunctions());
    }

    @FXML
    public void graphButtonClicked() throws ParserException {
        ObservableList selectedIndices = functionsListView.getSelectionModel().getSelectedIndices();
        String function = "";
        String functionName = "";

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("x");
        yAxis.setLabel("y");

        LineChart<Number, Number> graph = new LineChart<Number, Number>(xAxis, yAxis);

        graph.setTitle(function);
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        series.setName(functionName);

        Rectangle rect = new Rectangle(0, 0);
        rect.setVisible(false);

        if(selectedIndices.size() > 0) {
            function = functionsListView.getItems().get((Integer) selectedIndices.get(0));
            functionName = function.split("\\(")[0];
            System.out.println(functionName);
            for(double it = -30; it < 30; it+=0.1){
                try {
                    Node functionNode = new FunctionExpressionNode(functionName, String.valueOf(it));
                    XYChart.Data<Number, Number> data = new XYChart.Data<Number, Number>(it, functionNode.getValue());
                    data.setNode(rect);
                    series.getData().add(data);
                } catch (ParserException e) {
                    System.out.println(e);
                }
            }
            graph.getData().add(series);
        }



        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(graph);

        Scene secondScene = new Scene(secondaryLayout, 640, 356);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Graphique");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(200);
        newWindow.setY(100);

        newWindow.show();

    }

}
