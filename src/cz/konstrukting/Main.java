package cz.konstrukting;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) {
        /*System.out.println("Hello world!");
        Matica J = new Matica(5, 4);
        J = J.normal();
        Matica Jt = J.transpose();
        Matica JtJ = Jt.times(J);
        Matica invJtJ = JtJ.inverse();
        Matica C = invJtJ.times(Jt);
        C.show();
        Filer A = new Filer();*/
        launch(args);

    }

  @Override
  public void start(Stage stage)
  {
    Filer A = new Filer();
    stage.setTitle("Line Chart Sample");
    //defining the axes
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Number of Month");
    //creating the chart
    final LineChart<Number, Number> lineChart =
        new LineChart<Number, Number>(xAxis, yAxis);

    lineChart.setTitle("Stock Monitoring, 2010");
    //defining a series
    XYChart.Series series = new XYChart.Series();
    series.setName("My portfolio");
    //populating the series with data

    for (int i = 0; i < A.data.length; i++)
    {
      series.getData().add(new XYChart.Data(A.data[i][0], A.data[i][1]));
    }


    Scene scene = new Scene(lineChart, 800, 600);
    lineChart.getData().add(series);

    stage.setScene(scene);
    stage.show();
  }
}
