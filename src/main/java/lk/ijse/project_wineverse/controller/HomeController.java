package lk.ijse.project_wineverse.controller;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import lk.ijse.project_wineverse.bo.BOFactory;
import lk.ijse.project_wineverse.bo.custom.HomeBO;
import lk.ijse.project_wineverse.db.DBConnection;
import lk.ijse.project_wineverse.util.ValidateField;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane adminchangingPane;

    @FXML
    private ImageView logoutbtn1;

    @FXML
    private Label logoutlabel1;

    @FXML
    private PieChart piechartincomelast5months;

    @FXML
    private BarChart<String, Integer> lowstockitems;

    @FXML
    private Label eventimageslabel;

    @FXML
    private Label lbllowstockitems;

    private int currentIndex;

    @FXML
    private AreaChart<String, Double> areachart;

    @FXML
    private Label eventidlbl;

    @FXML
    private Label lblwrongyearformat;

    @FXML
    private TextField txtyear;

    @FXML
    private Label eventidlabel;

    @FXML
    private Group eventimagesgroup;

    @FXML
    private Rectangle rectangle;

    private static final int UPDATE_INTERVAL = 5000; // in milliseconds

    HomeBO homeBO = BOFactory.getBOFactory().getBO(BOFactory.BOTypes.HOME_BO);

    @FXML
    void initialize() throws SQLException, FileNotFoundException {
//        TimeAndDateController timeobject = new TimeAndDateController();
//        timeobject.timenow(time,date);

        assert adminchangingPane != null : "fx:id=\"adminchangingPane\" was not injected: check your FXML file 'homeform.fxml'.";
        assert logoutbtn1 != null : "fx:id=\"logoutbtn1\" was not injected: check your FXML file 'homeform.fxml'.";
        assert logoutlabel1 != null : "fx:id=\"logoutlabel1\" was not injected: check your FXML file 'homeform.fxml'.";
        assert piechartincomelast5months != null : "fx:id=\"piechartincomelast5months\" was not injected: check your FXML file 'homeform.fxml'.";

        setDataToPieChart();
        setDataToBarChart();

        setImage();

        lbllowstockitems.setVisible(false);
        lbllowstockitems.setText("");
        getDataToLowStockItemsLabel();

        lowstockitems.getXAxis().setStyle("-fx-background-color: white");
        lowstockitems.getYAxis().setStyle("-fx-background-color: white");

        data = homeBO.getDataToAreaChart(String.valueOf(2023));
        XYChart.Series<String, Double> series = new XYChart.Series<>(String.valueOf(2023), FXCollections.observableArrayList(data));
        areachart.getData().add(series);

        //eventimageslabel.setStyle("-fx-background-radius: 20");
    }

    public void logoutbtnMousePressed(MouseEvent mouseEvent) throws IOException {
        adminchangingPane.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginform.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void logoutlabelMousePressed(MouseEvent mouseEvent) throws IOException {
        adminchangingPane.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginform.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void setDataToPieChart() throws SQLException {
     //   ObservableList<PieChart.Data> pieChartData = OrderDetailModel.getDataToPieChart();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        ArrayList<PieChart.Data> all = homeBO.getDataToPieChart();

        pieChartData.addAll(all);

        piechartincomelast5months.setData(pieChartData);
    }

    public void setDataToBarChart() throws SQLException {
      //  ObservableList<XYChart.Series<String, Integer>> barChartData = ItemModel.getDataToBarChart();
        ObservableList<XYChart.Series<String, Integer>> barChartData = FXCollections.observableArrayList();

        ArrayList<XYChart.Series<String, Integer>> all = homeBO.getDataToBarChart();

        barChartData.addAll(all);

        lowstockitems.setData(barChartData);
    }

    List<XYChart.Data<String, Double>> data;
    public void setDataToAreaChart() throws SQLException {
        String year=txtyear.getText();
       //  data = CashierOrderModel.getDataToAreaChart(year);
        data = homeBO.getDataToAreaChart(year);

        XYChart.Series<String, Double> series = new XYChart.Series<>(year, FXCollections.observableArrayList(data));

        areachart.getData().add(series);
    }

    /*public void setImage(){
        try {
            List<Image> imageData  = AddEventImageModel.eventData();
            System.out.println(imageData);
            Image image = imageData.get(currentIndex);
            ImageView imageView = new ImageView(image);
            eventimageslabel.setGraphic((imageView));

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                currentIndex = (currentIndex + 1) % imageData.size();
                Image newImage = imageData.get(currentIndex);
                ImageView imageView1 = new ImageView(newImage);

                // Update the image view
                eventimageslabel.setGraphic((imageView1));
            }));
            System.out.println(currentIndex);
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

        } catch (Exception e) {
            System.out.println(e);
        }
    }*/

    public void setImage() {
        try {
         //   List<Pair<String, Image>> imageData = AddEventImageModel.eventDataWithIds();
            List<Pair<String, Image>> imageData = homeBO.eventDataWithIds();
            Pair<String, Image> eventImage = imageData.get(currentIndex);
            String eventId = eventImage.getKey();
            Image image = eventImage.getValue();
            ImageView imageView = new ImageView(image);
            eventimageslabel.setGraphic(imageView);
            eventidlabel.setText(eventId.toUpperCase());

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                currentIndex = (currentIndex + 1) % imageData.size();
                Pair<String, Image> newEventImage = imageData.get(currentIndex);
                String newEventId = newEventImage.getKey();
                Image newImage = newEventImage.getValue();
                ImageView newImageView = new ImageView(newImage);

                // Update the image view and event id label
                eventimageslabel.setGraphic(newImageView);
                eventidlabel.setText(newEventId.toUpperCase());
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

            ////////////////////////////
            rectangle.setOnMouseEntered(event -> {
                // Your event handling code goes here
                System.out.println("enteridlbl");
                timeline.pause();
            });

            rectangle.setOnMouseExited(event -> {
                // Your event handling code goes here
                System.out.println("exitidlbl");
                timeline.play();
            });
            /////////////////////////

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void lowstockitemsOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        InputStream resource = this.getClass().getResourceAsStream("/assets/reports/inventory.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lbllowstockitemsOnMouseEntered(MouseEvent mouseEvent) {
        lbllowstockitems.setVisible(true);
    }

    public void lbllowstockitemsOnMouseExited(MouseEvent mouseEvent) {
        lbllowstockitems.setVisible(false);

    }

    public void getDataToLowStockItemsLabel() throws SQLException {
      //  ObservableList<XYChart.Series<String, Integer>> barChartData = ItemModel.getDataToBarChart();

        ArrayList<XYChart.Series<String, Integer>> all = homeBO.getDataToBarChart();
        ObservableList<XYChart.Series<String, Integer>> barChartData = FXCollections.observableArrayList();

        barChartData.addAll(all);

        for (XYChart.Series<String, Integer> series : barChartData) {
            for (XYChart.Data<String, Integer> data : series.getData()) {
                //System.out.println("  Category: " + data.getXValue() + ", Value: " + data.getYValue());
                lbllowstockitems.setText(lbllowstockitems.getText()+"\n"+"Item Name: "+data.getXValue() + ", QTY: " + data.getYValue());
            }
        }

    }

    public void lowstockitemsOnMouseEntered(MouseEvent mouseEvent) {
        lbllowstockitems.setVisible(true);
    }

    public void lowstockitemsOnMouseExited(MouseEvent mouseEvent) {
        lbllowstockitems.setVisible(false);
    }

    public void txtyearOnAction(ActionEvent actionEvent) throws SQLException {
        lblwrongyearformat.setVisible(false);
        if (!txtyear.getText().isEmpty()) {
            if (ValidateField.yearCheck(txtyear.getText())) {
                setDataToAreaChart();
                areachart.setData(FXCollections.observableArrayList());
                setDataToAreaChart();
            } else {
                txtyear.setStyle("-fx-text-fill: red");
                lblwrongyearformat.setText("Wrong Year Format");
                lblwrongyearformat.setVisible(true);
            }
        }else{
            lblwrongyearformat.setText("Please enter an year first");
            lblwrongyearformat.setVisible(true);
        }

    }

    public void txtyearOnMouseClicked(MouseEvent mouseEvent) {
        txtyear.setStyle("-fx-text-fill: black");
        lblwrongyearformat.setVisible(false);
    }

    public void areachartOnMouseClicked(MouseEvent mouseEvent) {
        txtyear.setText("");
        txtyear.setStyle("-fx-text-fill: black");
        lblwrongyearformat.setVisible(false);
    }

    public void txtyearOnKeyTyped(KeyEvent keyEvent) {
        txtyear.setStyle("-fx-text-fill: black");
    }

    public void eventimageslabelOnMouseEntered(MouseEvent mouseEvent) {
    }

    public void eventimageslabelOnMouseExited(MouseEvent mouseEvent) {
    }


//    public boolean imagecheck() throws FileNotFoundException, SQLException {
//        String sql ="INSERT INTO event_images VALUES(?,?)";
//
//        //InputStream in = new FileInputStream("C:\\Users\\Owner\\OneDrive\\Documents\\IJSE\\Assignments\\project-wineverse\\src\\main\\resources\\lk.ijse.project_wineverse.assets\\righticon.png");
//        InputStream in = new FileInputStream("C:\\Users\\Owner\\OneDrive\\Documents\\IJSE\\1st Semester\\1st Semester Final Project\\Images\\refresh2.jpg");
//
//        String id="event-003";
//        return CrudUtil.execute(
//                sql,
//                id,
//                in
//        );
//    }
//
//    public void handleFileButtonAction(ActionEvent actionEvent) {
//        FileChooser fileChooser = new FileChooser();
//        File selectedFile = fileChooser.showOpenDialog(null);
//        if (selectedFile != null) {
//            String filePath = selectedFile.getAbsolutePath();
//            System.out.println(filePath);
//            // Do something with the selected file
//        }
//    }
}
