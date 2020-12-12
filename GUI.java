import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
public class GUI extends Application{
    Stage window;
    Scene sceneStart, sceneLogin, sceneCreateAccount, sceneDepLogin, userDashboard, scenePropList, scenePropDetails, sceneManageProps, sceneMakePayment;
    Scene scenePaymentHistory;
    String userId, password;
    Admin admin = new Admin();
    @Override
    public void start(Stage primaryStage) {
        admin.createRegistryOfOwners();
        admin.populatelistOfOwners();
        admin.createRegistryOfproperties();
        window = primaryStage;

        VBox layoutStart = new VBox();
        layoutStart.getChildren().add(new Label("What would you like to do?"));

        HBox loginArea = new HBox(new Label("Login: "));
        Button btLogin = new Button(" ");
        btLogin.setOnAction(e -> window.setScene(sceneLogin));
        loginArea.getChildren().add(btLogin);
        layoutStart.getChildren().add(loginArea);

        HBox createAccountArea = new HBox(new Label("Create Account: "));
        Button btCA = new Button(" ");
        btCA.setOnAction(e -> window.setScene(sceneCreateAccount));
        createAccountArea.getChildren().add(btCA);
        layoutStart.getChildren().add(createAccountArea);

        HBox depLoginArea = new HBox(new Label("Department of Environment Login: "));
        Button btDep = new Button(" ");
        btDep.setOnAction(e -> window.setScene(sceneDepLogin));
        depLoginArea.getChildren().add(btDep);
        layoutStart.getChildren().add(depLoginArea);

        HBox exitArea = new HBox(new Label("Exit: "));
        Button btExit = new Button(" ");
        btExit.setOnAction(e -> window.close());
        exitArea.getChildren().add(btExit);
        layoutStart.getChildren().add(exitArea);

        sceneStart = new Scene(layoutStart, 300, 200);

        VBox layoutLogin = new VBox();
        layoutLogin.getChildren().add(new Label("Enter your login details bellow:"));

        HBox userIDArea = new HBox(new Label("Enter userID: "));
        TextField userIDField = new TextField();
        userIDArea.getChildren().add(userIDField);
        layoutLogin.getChildren().add(userIDArea);

        HBox passwordArea = new HBox(new Label("Enter password: "));
        PasswordField passwordField = new PasswordField();
        passwordArea.getChildren().add(passwordField);
        layoutLogin.getChildren().add(passwordArea);
        Button btEnter = new Button("Enter");
        Label liStatus = new Label();
        btEnter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    userId = userIDField.getText();
                    password = passwordField.getText();
                    if(admin.checkLogin(userId, password) == true) {
                        liStatus.setText("");
                        window.setScene(userDashboard);
                    } else {
                        liStatus.setText("Invalid login details");
                    }
                }
            });
        Button btReturn = new Button("Back");
        btReturn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    window.setScene(sceneStart);
                    liStatus.setText("");
                }
            });
        HBox loginButtons = new HBox();
        loginButtons.getChildren().add(btEnter);
        loginButtons.getChildren().add(btReturn);
        layoutLogin.getChildren().add(loginButtons);
        layoutLogin.getChildren().add(liStatus);
        sceneLogin = new Scene(layoutLogin, 300, 200);

        VBox layoutCA = new VBox();
        layoutCA.getChildren().add(new Label("Enter your desired account details below:"));

        HBox userIDAreaCA = new HBox(new Label("Enter userID: "));
        TextField userIDFieldCA = new TextField();
        userIDAreaCA.getChildren().add(userIDFieldCA);
        layoutCA.getChildren().add(userIDAreaCA);

        HBox passwordAreaCA = new HBox(new Label("Enter password: "));
        PasswordField passwordFieldCA = new PasswordField();
        passwordAreaCA.getChildren().add(passwordFieldCA);
        layoutCA.getChildren().add(passwordAreaCA);
        Button btEnterCA = new Button("Enter");
        Label liStatusCA = new Label();
        btEnterCA.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    userId = userIDFieldCA.getText();
                    password = passwordFieldCA.getText();
                    if(admin.createOwner(userId, password) == true) {
                        liStatusCA.setText("");
                        window.setScene(userDashboard);
                    } else {
                        liStatusCA.setText("UserID already in use.");
                    }
                }
            });
        layoutCA.getChildren().add(btEnterCA);
        layoutCA.getChildren().add(liStatusCA);
        sceneCreateAccount = new Scene(layoutCA, 300, 200);

        VBox layoutUserDash = new VBox();
        layoutUserDash.getChildren().add(new Label("Welcome to the user dashboard"));

        HBox propListArea = new HBox(new Label("View Property List: "));
        Button btPropList = new Button(" ");
        btPropList.setOnAction(e -> window.setScene(scenePropList));
        propListArea.getChildren().add(btPropList);
        layoutUserDash.getChildren().add(propListArea);
        btPropList.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    VBox propList = new VBox(10);
                    propList.getChildren().add(admin.getOurStringProperties());
                    scenePropList = new Scene(propList,300,200);
                    HBox ExitList = new HBox(new Label("Exit "));
                    Button btExitList = new Button(" ");
                    ExitList.getChildren().add(btExitList);
                    propList.getChildren().add(ExitList);
                    btExitList.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                window.setScene(userDashboard);
                            }
                        });
                    window.setScene(scenePropList);
                }
            });

        HBox propDetailsArea = new HBox(new Label("View Property Details: "));
        Button btPropDetails = new Button(" ");
        propDetailsArea.getChildren().add(btPropDetails);
        layoutUserDash.getChildren().add(propDetailsArea);
        btPropDetails.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    VBox propListDetailed = new VBox(10);
                    propListDetailed.getChildren().add(admin.getOurStringPropertiesDetailed());
                    scenePropDetails = new Scene(propListDetailed,300,200);
                    HBox ExitListDetailed = new HBox(new Label("Exit "));
                    Button btExitListDetailed = new Button(" ");
                    ExitListDetailed.getChildren().add(btExitListDetailed);
                    propListDetailed.getChildren().add(ExitListDetailed);
                    btExitListDetailed.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                window.setScene(userDashboard);
                            }
                        });
                    window.setScene(scenePropDetails);
                }
            });


        HBox managePropsArea = new HBox(new Label("Manage Properties: "));
        Button btManageProp = new Button(" ");
        btManageProp.setOnAction(e -> window.setScene(sceneManageProps));
        managePropsArea.getChildren().add(btManageProp);
        layoutUserDash.getChildren().add(managePropsArea);

        HBox makePaymentArea = new HBox(new Label("Make A Payment: "));
        Button btMakePayment = new Button(" ");
        btMakePayment.setOnAction(e -> window.setScene(sceneMakePayment));
        makePaymentArea.getChildren().add(btMakePayment);
        layoutUserDash.getChildren().add(makePaymentArea);

        HBox paymentHistArea = new HBox(new Label("View Payment History: "));
        Button btPaymentHist = new Button(" ");
        btPaymentHist.setOnAction(e -> window.setScene(scenePaymentHistory));
        paymentHistArea.getChildren().add(btPaymentHist);
        layoutUserDash.getChildren().add(paymentHistArea);

        HBox logoutArea = new HBox(new Label("Logout: "));
        Button btLogout = new Button(" ");
        btLogout.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    window.setScene(sceneStart);
                    liStatus.setText("");
                }
            });
        logoutArea.getChildren().add(btLogout);
        layoutUserDash.getChildren().add(logoutArea);

        userDashboard = new Scene(layoutUserDash, 300, 200);

        primaryStage.setTitle("CS4013 Project");
        primaryStage.setScene(sceneStart);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
