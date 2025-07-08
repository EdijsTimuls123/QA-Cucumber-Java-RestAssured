module com.example.qalogintest {
  requires javafx.controls;
  requires javafx.fxml;


  opens com.example.qalogintest to javafx.fxml;
  exports com.example.qalogintest;
}