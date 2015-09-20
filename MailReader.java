import javafx.application.Application;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

/**
 * Runs "MailCall", the MailReader pseudo email application/GUI.
 *
 * @author Alex Thompson
 * @version 1
 */
public class MailReader extends Application {

    /**
     * Gathers elements (mailboxes and messages), instantiates them
     * (while also making use of Server and Person), and runs the application.
     *
     * @param stage a Stage object, needed fr javafx applications.
     */
    public void start(Stage stage) {

        Mailbox inbox = new Mailbox("Inbox");
        Mailbox important = new Mailbox("Important");
        Mailbox trash = new Mailbox("Trash");

        ObservableList<Mailbox> folders = FXCollections.observableArrayList();
        folders.add(inbox);
        folders.add(important);
        folders.add(trash);
        ListView<Mailbox> viewFolders = new ListView<Mailbox>(folders);
        ListView<Message> viewMessages = new ListView<Message>(inbox.getList());

        ObservableList<String> body = FXCollections.observableArrayList();

        viewFolders.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                body.setAll();
                viewMessages.getSelectionModel().clearSelection();
                viewMessages.setItems(newValue.getList());
            });
        viewMessages.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    body.setAll("FROM: " + newValue.getSenderName()
                        + " (" + newValue.getSenderEmail() + ")",
                        "TO: " + newValue.getRecipients(),
                        "DATE: " + newValue.getDate(),
                        "SUBJECT: " + newValue.getSubject(),
                        String.format("%n"),
                        newValue.getBody());
                }
            });

        ListView<String> viewBody = new ListView<String>(body);

        Button refresh = new Button();
        refresh.setText("Refresh");
        refresh.setPrefWidth(75);
        refresh.setOnAction(e -> {
                inbox.add();
            });
        Button sortByDate = new Button();
        sortByDate.setText("Sort->Date");
        sortByDate.setPrefWidth(120);
        sortByDate.setOnAction(e -> {
                Mailbox current = viewFolders.getSelectionModel()
                    .getSelectedItem();
                if (current == null) {
                    current = inbox;
                }
                viewMessages.setItems(current.sort());
            });
        Button sortBySender = new Button();
        sortBySender.setPrefWidth(120);
        sortBySender.setText("Sort->Sender");
        sortBySender.setOnAction(e -> {
                Mailbox current = viewFolders.getSelectionModel()
                    .getSelectedItem();
                if (current == null) {
                    current = inbox;
                }
                viewMessages.setItems(current.sortSender());
            });
        Button sortBySubject = new Button();
        sortBySubject.setText("Sort->Subject");
        sortBySubject.setPrefWidth(120);
        sortBySubject.setOnAction(e -> {
                Mailbox current = viewFolders.getSelectionModel()
                    .getSelectedItem();
                if (current == null) {
                    current = inbox;
                }
                viewMessages.setItems(current.sortSubject());
            });
        Button toTrash = new Button();
        toTrash.setText("Trash");
        toTrash.setPrefWidth(75);
        toTrash.setOnAction(e-> {
                Mailbox current = viewFolders.getSelectionModel()
                    .getSelectedItem();
                if (current == null) {
                    current = inbox;
                }
                Message m = viewMessages.getSelectionModel()
                    .getSelectedItem();
                if (!(current.toString().equals("Trash"))) {
                    if (m != null) {
                        current.remove(m);
                        trash.add(m);
                    }
                } else {
                    current.remove(m);
                }
            });
        Button makeFlag = new Button();
        makeFlag.setText("Flag");
        makeFlag.setPrefWidth(75);
        makeFlag.setOnAction(e -> {
                Mailbox current = viewFolders.getSelectionModel()
                    .getSelectedItem();
                if (current == null) {
                    current = inbox;
                }
                if (!(current.toString().equals("Important"))) {
                    Message m = viewMessages.getSelectionModel()
                        .getSelectedItem();
                    if (m != null) {
                        current.remove(m);
                        important.add(m);
                    }
                }
            });

        HBox sortButtons = new HBox();
        sortButtons.getChildren().addAll(refresh, sortBySubject,
            sortBySender, sortByDate, toTrash, makeFlag);
        sortButtons.setSpacing(20);
        sortButtons.setPrefWidth(600);
        sortButtons.setPrefHeight(50);
        sortButtons.setAlignment(Pos.CENTER);
        sortButtons.setStyle("-fx-background-color: #336699;");

        VBox chooseFolder = new VBox();
        chooseFolder.getChildren().add(viewFolders);
        chooseFolder.setSpacing(20);
        chooseFolder.setPrefHeight(400);
        chooseFolder.setPrefWidth(100);

        VBox chooseMessage = new VBox();
        chooseMessage.setSpacing(20);
        chooseMessage.getChildren().add(viewMessages);
        chooseMessage.setPrefHeight(400);

        VBox viewMessage = new VBox();
        viewMessage.setSpacing(20);
        viewMessage.getChildren().add(viewBody);
        viewMessage.setPrefHeight(400);
        viewMessage.setPrefWidth(500);

        BorderPane border = new BorderPane();
        border.setLeft(chooseFolder);
        border.setTop(sortButtons);
        border.setCenter(chooseMessage);
        border.setRight(viewMessage);
        Scene scene = new Scene(border);
        stage.setScene(scene);
        stage.setTitle("MailCall");
        stage.show();
    }
}