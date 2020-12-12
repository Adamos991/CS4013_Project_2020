import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.collections.*;
public class GUI extends Application{
    Stage window;
    Scene sceneStart, sceneLogin, sceneCreateAccount, sceneDepLogin, userDashboard, scenePropList, scenePropDetails, sceneManageProps, sceneMakePayment,sceneRegProp,sceneRemProp;
    Scene scenePaymentHistory;
    String userId, password;
    String Address, Eircode, loCat;
    boolean ppr;
    int lastYear;
    double value;
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

        VBox manageProperties = new VBox();
        manageProperties.getChildren().add(new Label("Manage your properties"));

        HBox registerProp = new HBox(new Label("Register Property:"));
        Button btRegProp = new Button(" ");
        btRegProp.setOnAction( e -> window.setScene(sceneRegProp));
        registerProp.getChildren().add(btRegProp);
        manageProperties.getChildren().add(registerProp);

        HBox removeProp = new HBox(new Label("Remove Property:"));
        Button btRemProp = new Button(" ");
        btRemProp.setOnAction( e -> window.setScene(sceneRemProp));
        removeProp.getChildren().add(btRemProp);
        manageProperties.getChildren().add(removeProp);
        btRemProp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    VBox propListToRemove = new VBox(10);
                    propListToRemove.getChildren().add(admin.getOurStringProperties());
                    sceneRemProp = new Scene(propListToRemove,300,200);
                    HBox ExitListToRemove = new HBox();
                    Button btExitListToRemove = new Button(" Cancel ");
                    Button btRemoveProp = new Button(" Remove ");
                    HBox typeInEircode = new HBox(new Label("Eircode of property you want to remove:"));
                    TextField eircodeGetter = new TextField();
                    typeInEircode.getChildren().add(eircodeGetter);
                    propListToRemove.getChildren().add(typeInEircode);
                    ExitListToRemove.getChildren().add(btRemoveProp);
                    ExitListToRemove.getChildren().add(btExitListToRemove);
                    propListToRemove.getChildren().add(ExitListToRemove);
                    btExitListToRemove.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                window.setScene(sceneManageProps);
                            }
                        });
                    btRemoveProp.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                String eircode = eircodeGetter.getText();
                                for (int i=0; i<admin.getPropertiesOfCurrentOwner().size(); i++){
                                    System.out.println("We're inside");
                                    if (eircode.equals(admin.getEircodeOfCurrentOwner(i))){
                                        System.out.println("We are removing");
                                        admin.removePropertyFromOwnerReal(i);
                                    }
                                }
                                window.setScene(sceneManageProps);
                            }
                        });
                    window.setScene(sceneRemProp);
                }
            });

        HBox ExitProp = new HBox(new Label("Exit:"));
        Button btExitProp = new Button(" ");
        btExitProp.setOnAction( e -> window.setScene(userDashboard));
        ExitProp.getChildren().add(btExitProp);
        manageProperties.getChildren().add(ExitProp);

        sceneManageProps = new Scene(manageProperties,300,200);
        //Register a property
        VBox layoutRegisterProperties = new VBox();
        layoutRegisterProperties.getChildren().add(new Label("Enter your property details below:"));

        HBox PropertyAddressArea = new HBox(new Label("Enter Property Address: "));
        TextField PropertyAddressField = new TextField();
        PropertyAddressArea.getChildren().add(PropertyAddressField);
        layoutRegisterProperties.getChildren().add(PropertyAddressArea); 

        HBox eircodeArea = new HBox(new Label("Enter Eircode: "));
        TextField eircodeField = new TextField();
        eircodeArea.getChildren().add(eircodeField);
        layoutRegisterProperties.getChildren().add(eircodeArea);

        HBox valueArea = new HBox(new Label("Enter Property Value: "));
        TextField valueField = new TextField();
        valueArea.getChildren().add(valueField);
        layoutRegisterProperties.getChildren().add(valueArea);

        HBox loCatArea = new HBox(new Label("Enter Property Location Category(City, Large Town, Small Town, Village, Countryside: "));
        TextField loCatField = new TextField();
        loCatArea.getChildren().add(loCatField);
        layoutRegisterProperties.getChildren().add(loCatArea);

        HBox pprArea = new HBox(new Label("Is the property your Principal Private Residence? True/False: "));
        TextField pprField = new TextField();
        pprArea.getChildren().add(pprField);
        layoutRegisterProperties.getChildren().add(pprArea);

        HBox lastYearArea = new HBox(new Label("Enter the last year you paid tax on the property: "));
        TextField lastYearField = new TextField();
        lastYearArea.getChildren().add(lastYearField);
        layoutRegisterProperties.getChildren().add(lastYearArea);

        HBox RegisterButtons = new HBox();
        Button btRegister = new Button("Register");
        RegisterButtons.getChildren().add(btRegister);
        Button btbackToManageProperties = new Button("Back");
        RegisterButtons.getChildren().add(btbackToManageProperties);
        btbackToManageProperties.setOnAction( e -> window.setScene(sceneManageProps));
        layoutRegisterProperties.getChildren().add(RegisterButtons);

        sceneRegProp =  new Scene(layoutRegisterProperties,300,200);

        btRegister.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Address = PropertyAddressField.getText();
                    Eircode= eircodeField.getText();
                    value = Double.parseDouble(valueField.getText());
                    loCat = loCatField.getText();
                    ppr = Boolean.parseBoolean(pprField.getText());
                    lastYear = Integer.parseInt(lastYearField.getText());
                    admin.registerProperty(admin.getCurrentOwner().getOwnerId(), Address, Eircode, value, loCat, ppr, lastYear);
                    window.setScene(userDashboard);
                }
            });

        primaryStage.setTitle("CS4013 Project");
        primaryStage.setScene(sceneStart);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
