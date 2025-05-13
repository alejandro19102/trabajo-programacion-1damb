package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.RankingDAO;
import modelo.ScoreEntry;

public class RankingController {

    @FXML private TableView<ScoreEntry> rankingTable;
    @FXML private TableColumn<ScoreEntry, String> playerColumn;
    @FXML private TableColumn<ScoreEntry, Integer> scoreColumn;

    @FXML
    public void initialize() {
        playerColumn.setCellValueFactory(new PropertyValueFactory<>("player"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        rankingTable.setItems(FXCollections.observableArrayList(new RankingDAO().getTop10()));
    }

    @FXML
    private void volverMenu(ActionEvent event) {
        try {
            java.net.URL loc = getClass().getResource("/view/Menu.fxml");
            Parent root = FXMLLoader.load(loc);
            Stage stage = (Stage) rankingTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
