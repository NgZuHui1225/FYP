import java.sql.*;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;

public class IQTesting2 extends Application{
    
    PreparedStatement preparedStatement;
    Statement statement ;
    ResultSet result;
    Connection con;
    
    //Start
    Button btnstrt = new Button("START");
    
    //Login
    Label lbllogin= new Label("User Login");
    Label lblIQT= new Label("");
    Image imgIQT = new Image("Images/logre.png");
    TextField tfuser = new TextField();
    PasswordField tfpass = new PasswordField();
    Button btnlogin = new Button("Login");
    Button btnregi = new Button("Don't have an account?Register");
    
    //Register
    Label lblregi= new Label("User Registration");
    Label lblIQT2= new Label("");
    TextField tfuserr = new TextField();
    PasswordField tfpassr = new PasswordField();
    PasswordField tfpasscom = new PasswordField();
    TextField tfgend = new TextField();
    TextField tfpnum = new TextField();
    TextField tfmail = new TextField();
    Button btnregis = new Button("Register");
    Button btnblogin = new Button("Back to Login");
    
    //User Interface
    Button btntest = new Button("IQ Test");
    Button btnldb = new Button("Leaderboard");
    Button btnhome = new Button("Home");
    Button btnabt = new Button("About");
    Button btnlogout = new Button("Logout");
    Button btnannounce = new Button("");
    Button btnmessage = new Button("");
    Button btnsetting = new Button("");
    Label lbluser = new Label(" ");
    Label lblspcing= new Label("");
    
    //Test
    int scores=0;
    int correctcount=0;
    int wrongcount=0;
    int i=1;
    double pb = 0;
    Label lblques = new Label("");
    Label lblicon = new Label("");
    RadioButton rA = new RadioButton("A.");
    RadioButton rB = new RadioButton("B.");
    RadioButton rC = new RadioButton("C.");
    RadioButton rD = new RadioButton("D.");
    ToggleGroup tg = new ToggleGroup();
    Button btnback = new Button("Back");
    Button btnnext = new Button("Next");
    Button btnsubmit = new Button("Submit");
    ProgressBar progressBar = new ProgressBar(pb);
    
    //Score
    Label lblicon2 = new Label("");
    Label lbliq = new Label("Your IQ Score is ");
    Label lblimg = new Label("");
    Label lblspc = new Label("");
    Button btnback2 = new Button("Back");
    
    //Announce
    Label lblicon3 = new Label("");
    Button btnback3 = new Button("Back");
    Label lblann = new Label();
    
    //Leaderboard
    List<String> leaderboardData = new ArrayList<>();
    String userScore = "";
    Label lblicon4 = new Label("");
    Button btnback4 = new Button("Back");
    Label lblldb = new Label();
    Label userLabel = new Label();
    
    //About
    Label lblicon5 = new Label("");
    Button btnback5 = new Button("Back");
    Label lblauthor = new Label("Ng Zu Hui");
    //Setting
    Label lblicon6 = new Label("");
    Button btnback6 = new Button("Back");
    Button btnupt = new Button("Update");
    Label lbluser2 = new Label("");
    TextField tfgend2 = new TextField();
    TextField tfpnum2 = new TextField();
    TextField tfmail2 = new TextField();
    //Admin Interface
    Button btnann = new Button("Announcement");
    Button btnaddann = new Button("Announce");
    TextField tfann = new TextField("");
    Label lblicon7 = new Label("");
    Button btnback7 = new Button("Back");
    Button btnadmin = new Button("Add Admin");
    Button btnaddadmin = new Button("Add Admin");
    Label lbladmin= new Label("Admin Registration");
    Label lblIQT3= new Label("");
    TextField tfadmin = new TextField();
    PasswordField tfapass = new PasswordField();
    Button btnback8 = new Button("Back");
    Button btnlogout2 = new Button("Logout");
    Label lblicon8 = new Label("");
    
    @Override
    public void start(Stage primaryStage){
        initializeDB();
        //set Images of label & button
        lblIQT.setGraphic(new ImageView(imgIQT));
        lblIQT2.setGraphic(new ImageView(imgIQT));
        lblIQT3.setGraphic(new ImageView(imgIQT));
        btntest.setGraphic(new ImageView(new Image("Button/testic.png")));
        btnldb.setGraphic(new ImageView(new Image("Button/leaderb.png")));
        btnhome.setGraphic(new ImageView(new Image("Button/homeic.png")));
        btnabt.setGraphic(new ImageView(new Image("Button/aboutic.png")));
        btnlogout.setGraphic(new ImageView(new Image("Button/logout.png")));
        btnannounce.setGraphic(new ImageView(new Image("Button/annic.png")));
        btnmessage.setGraphic(new ImageView(new Image("Button/msgic.png")));
        btnsetting.setGraphic(new ImageView(new Image("Button/setic.png")));
        btnann.setGraphic(new ImageView(new Image("Button/addannic.png")));
        btnadmin.setGraphic(new ImageView(new Image("Button/addadmin.png")));
        btnlogout2.setGraphic(new ImageView(new Image("Button/logout.png")));
        lbluser.setGraphic(new ImageView(new Image("Button/useric.png")));
        lblicon.setGraphic(new ImageView(new Image("Images/IQTlogo.png")));
        lblicon2.setGraphic(new ImageView(new Image("Images/IQTlogo.png")));
        lblicon3.setGraphic(new ImageView(new Image("Images/IQTlogo.png")));
        lblicon4.setGraphic(new ImageView(new Image("Images/IQTlogo.png")));
        lblicon5.setGraphic(new ImageView(new Image("Images/IQTlogo.png")));
        lblicon6.setGraphic(new ImageView(new Image("Images/IQTlogo.png")));
        lblicon7.setGraphic(new ImageView(new Image("Images/IQTlogo.png")));
        lblicon8.setGraphic(new ImageView(new Image("Images/IQTlogo.png")));
        lblauthor.setGraphic(new ImageView(new Image("Images/Author.jpg")));
        //Set Style class
        btntest.getStyleClass().add("btnset1");
        btnldb.getStyleClass().add("btnset1");
        btnabt.getStyleClass().add("btnset1");
        btnlogout.getStyleClass().add("btnset1");
        btnann.getStyleClass().add("btnset1");
        btnadmin.getStyleClass().add("btnset1");
        btnlogout2.getStyleClass().add("btnset1");
        btnhome.getStyleClass().add("btnspe");
        btnannounce.getStyleClass().add("btnset2");
        btnmessage.getStyleClass().add("btnset2");
        btnsetting.getStyleClass().add("btnset2");
        btnback.getStyleClass().add("btnset3");
        btnback2.getStyleClass().add("btnset3");
        btnback3.getStyleClass().add("btnset3");
        btnback4.getStyleClass().add("btnset3");
        btnback5.getStyleClass().add("btnset3");
        btnback6.getStyleClass().add("btnset3");
        btnback7.getStyleClass().add("btnset3");
        btnback8.getStyleClass().add("btnset3");
        btnnext.getStyleClass().add("btnset3");
        btnsubmit.getStyleClass().add("btnset3");
        btnupt.getStyleClass().add("btnset3");
        btnaddann.getStyleClass().add("btnset3");
        tfuser.getStyleClass().add("tfset1");
        tfuserr.getStyleClass().add("tfset1");
        tfpass.getStyleClass().add("tfset1");
        tfpassr.getStyleClass().add("tfset1");
        tfpasscom.getStyleClass().add("tfset1");
        tfgend.getStyleClass().add("tfset1");
        tfpnum.getStyleClass().add("tfset1");
        tfmail.getStyleClass().add("tfset1");
        tfgend2.getStyleClass().add("tfset1");
        tfpnum2.getStyleClass().add("tfset1");
        tfmail2.getStyleClass().add("tfset1");
        tfadmin.getStyleClass().add("tfset1");
        tfapass.getStyleClass().add("tfset1");
        //set Display
        btntest.setContentDisplay(ContentDisplay.TOP);
        btnldb.setContentDisplay(ContentDisplay.TOP);
        btnhome.setContentDisplay(ContentDisplay.TOP);
        btnabt.setContentDisplay(ContentDisplay.TOP);
        btnlogout.setContentDisplay(ContentDisplay.TOP);
        lbluser.setContentDisplay(ContentDisplay.LEFT);
        lblauthor.setContentDisplay(ContentDisplay.LEFT);
        
        lblques.setGraphic(new ImageView(new Image("Q1/Q1.jpg")));
        rA.setGraphic(new ImageView(new Image("Q1/Q1A1.jpg")));
        rB.setGraphic(new ImageView(new Image("Q1/Q1A2.jpg")));
        rC.setGraphic(new ImageView(new Image("Q1/Q1A3.jpg")));
        rD.setGraphic(new ImageView(new Image("Q1/Q1A4.jpg")));
        rA.setToggleGroup(tg);
        rB.setToggleGroup(tg);
        rC.setToggleGroup(tg);
        rD.setToggleGroup(tg);
        rA.setContentDisplay(ContentDisplay.RIGHT);
        rB.setContentDisplay(ContentDisplay.RIGHT);
        rC.setContentDisplay(ContentDisplay.RIGHT);
        rD.setContentDisplay(ContentDisplay.RIGHT);
        
        
        //----------------------------Start----------------------------//
        StackPane sp = new StackPane();
         sp.setId("root");
         sp.getChildren().add(btnstrt);
         sp.setStyle("-fx-background-image:url('start.jpg')");
         sp.setAlignment(btnstrt, Pos.BOTTOM_CENTER);
         Insets padding = new Insets(0, 0, 100, 0);
         sp.setPadding(padding);
         btnstrt.setStyle("-fx-background-color:transparent;"
                        + "-fx-font-family:Stencil;"
                        + "-fx-font-size:52px;");
         this.btnstrt.setUnderline(true);
         
        //----------------------------Login----------------------------//
        VBox v1= new VBox();
        v1.getChildren().addAll(lbllogin,tfuser,tfpass,btnlogin,btnregi);
        lbllogin.setStyle("-fx-font-family:Wingdings 3;"
                + "-fx-font-size:40px;");
        tfuser.setPromptText("Username");
        tfuser.setMaxWidth(450);
        tfpass.setPromptText("Password");
        tfpass.setMaxWidth(450);
        btnlogin.setStyle("-fx-background-color:#000000;"
                + "-fx-text-fill:#FFFFFF;"
                + "-fx-font-size:25px;");
        btnregi.setStyle("-fx-background-color:transparent");
        v1.setStyle("-fx-background-color:#FFFFFF");
        v1.setAlignment(Pos.CENTER);
        v1.setSpacing(30);
        v1.setPrefWidth(600);
        HBox h1 = new HBox();
        h1.setPrefWidth(1200);
        h1.getChildren().addAll(lblIQT,v1);
        
        //----------------------------Register scene----------------------------//
        VBox v2= new VBox();
        v2.getChildren().addAll(lblregi,tfuserr,tfgend,tfpnum,tfmail,tfpassr,tfpasscom,btnregis,btnblogin);
        lblregi.setStyle("-fx-font-family:Wingdings 3;"
                + "-fx-font-size:40px;");
        tfuserr.setPromptText("Username(cannot be changed)");
        tfuserr.setMaxWidth(450);
        tfgend.setPromptText("Gender");
        tfgend.setMaxWidth(450);
        tfpnum.setPromptText("Phone Number");
        tfpnum.setMaxWidth(450);
        tfmail.setPromptText("E-mail");
        tfmail.setMaxWidth(450);
        tfpassr.setPromptText("Password");
        tfpassr.setMaxWidth(450);
        tfpasscom.setPromptText("Comfirm Password");
        tfpasscom.setMaxWidth(450);
        btnregis.setStyle("-fx-background-color:#000000;"
                + "-fx-text-fill:#FFFFFF;"
                + "-fx-font-size:25px;");
        btnblogin.setStyle("-fx-background-color:#000000;"
                + "-fx-text-fill:#FFFFFF;"
                + "-fx-font-size:25px;");
        v2.setStyle("-fx-background-color:#FFFFFF");
        v2.setAlignment(Pos.CENTER);
        v2.setSpacing(30);
        v2.setPrefWidth(600);
        HBox h2 = new HBox();
        h2.setPrefWidth(1200);
        h2.getChildren().addAll(lblIQT2,v2);
        
        //----------------------------User Interface----------------------------//
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(20));
        HBox h3 = new HBox();
        h3.getChildren().addAll(btnannounce,btnsetting);
        bp.setRight(h3);
        HBox h4 = new HBox();
        h4.getChildren().addAll(lbluser);
        lbluser.setStyle("-fx-background-color:#FFFFFF;"
                + "-fx-text-fill: #937D87;"
                + "-fx-font-size:20px;"
                + "-fx-font-family:Ravie;"
                + "-fx-max-height: 50px;");
        bp.setLeft(h4);
        HBox h5 = new HBox();
        h5.getChildren().addAll(lblspcing,btntest,btnldb,btnhome,btnabt,btnlogout);
        lblspcing.setMaxWidth(200);
        StackPane sp1 = new StackPane(h5);
        h5.setPrefWidth(1200);
        h5.setSpacing(10);
        sp1.setAlignment(h5, Pos.CENTER);
        bp.setBottom(sp1);
        bp.setStyle("-fx-background-image:url('Images/userintpink.png')");
        
        //----------------------------Test Scene----------------------------//
        HBox htest = new HBox();
        GridPane p1 = new GridPane();
        p1.add(rA,0,0);
        p1.add(rB,2,0);
        p1.add(rC,0,2);
        p1.add(rD,2,2);
        htest.getChildren().addAll(lblques,p1);
        htest.setMinHeight(500);
        htest.setMinWidth(1000);
        htest.setMaxHeight(500);
        htest.setMaxWidth(1000);
        htest.setSpacing(100);
        htest.setStyle("-fx-border-color: black; "
                + "-fx-border-width: 2px;"
                + "-fx-background-color:#F4F7FC;");
        htest.setPadding(new Insets(20));
        HBox h6 = new HBox();
        h6.getChildren().addAll(lblicon,btnback);
        h6.setSpacing(800);
        h6.setPadding(new Insets(20));
        h6.setMaxHeight(100);
        VBox v3 = new VBox();
        v3.getChildren().addAll(h6,htest,btnnext,progressBar);
        v3.setStyle("-fx-background-color:#E4C7D4;");
        v3.setMinHeight(750);
        v3.setMinWidth(1200);
        v3.setMaxHeight(750);
        v3.setMaxWidth(1200);
        v3.setAlignment(Pos.CENTER);
        v3.setSpacing(20);
        v3.setPadding(new Insets(20));
        progressBar.setPrefWidth(1200);
        progressBar.setPrefHeight(40);
         progressBar.setStyle("-fx-accent: green;"
                 + "-fx-background-color: #f0f0f0;"
                 + "-fx-text-box-border: transparent;");
         
        //----------------------------Score scene----------------------------//
        
        lbliq.setStyle("-fx-font-size:35px;" 
                + "-fx-font-family:Nirmala UI;"
                + "-fx-background-color:#F4F7FC;"
                + "-fx-text-fill:#28527A;");
        HBox h7 = new HBox();
        h7.getChildren().addAll(lblicon2,btnback2);
        h7.setSpacing(800);
        h7.setPadding(new Insets(20));
        h7.setMaxHeight(100);
        GridPane p3 = new GridPane();
        p3.add(new Label("Score "),0,0);
        p3.add(new Label("Classification"),1,0);
        p3.add(new Label("130 and above "),0,1);
        p3.add(new Label("Very Superior"),1,1);
        p3.add(new Label("120 - 129 "),0,2);
        p3.add(new Label("Superior"),1,2);
        p3.add(new Label("110 - 119 "),0,3);
        p3.add(new Label("High average"),1,3);
        p3.add(new Label("90 - 109 "),0,4);
        p3.add(new Label("Average"),1,4);
        p3.add(new Label("80 - 89 "),0,5);
        p3.add(new Label("Low Average"),1,5);
        p3.setAlignment(Pos.CENTER);
        p3.setHgap(10);
        p3.setVgap(10);
        p3.setPadding(new Insets(20));
        p3.setStyle("-fx-border-color: black; "
                + "-fx-border-width: 2px;"
                + "-fx-background-color:#F4F7FC;");
        p3.getStyleClass().add("gp2");
        lblspc.setMinWidth(300);
        p3.setMaxHeight(250);
        HBox hiq = new HBox();
        hiq.getChildren().addAll(lblspc,lblimg,p3);
        hiq.setAlignment(Pos.CENTER);
        hiq.setSpacing(20);
        lblimg.setGraphic(new ImageView(new Image("Score/midscr.jpg")));
        VBox v4 = new VBox();
        v4.getChildren().addAll(h7,lbliq,hiq);
        v4.setMinHeight(750);
        v4.setMinWidth(1200);
        v4.setMaxHeight(750);
        v4.setMaxWidth(1200);
        v4.setAlignment(Pos.CENTER);
        v4.setSpacing(20);
        v4.setPadding(new Insets(20));
        
        //----------------------------Announce scene----------------------------//
        VBox v5 = new VBox();
        v5.setMinHeight(750);
        v5.setMinWidth(1200);
        v5.setMaxHeight(750);
        v5.setMaxWidth(1200);
        v5.setAlignment(Pos.CENTER);
        v5.setSpacing(20);
        v5.setPadding(new Insets(20));
        HBox h8 = new HBox();
        h8.getChildren().addAll(lblicon3,btnback3);
        h8.setSpacing(800);
        h8.setPadding(new Insets(20));
        h8.setMaxHeight(100);
        VBox v6 = new VBox();
        v6.setMinHeight(500);
        v6.setMinWidth(1000);
        v6.setMaxHeight(500);
        v6.setMaxWidth(1000);
        v6.setAlignment(Pos.CENTER);
        v6.setSpacing(20);
        v6.setPadding(new Insets(20));
        v6.setStyle("-fx-border-color: black; "
                + "-fx-border-width: 2px;"
                + "-fx-background-color:#F4F7FC;");
        v5.getChildren().addAll(h8,v6);
        try {
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iqtesting", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT announce FROM announce");
            while (resultSet.next()) {
                String announceText = resultSet.getString("announce");
                Label lblann = new Label(announceText);
                lblann.setStyle("-fx-font-size:35px;" 
                        + "-fx-font-family:Nirmala UI;"
                        + "-fx-text-fill:#28527A;"
                        + "-fx-border-color: #28527A;"
                        + "-fx-border-width: 1px;");
                lblann.setMinWidth(960);
                lblann.setWrapText(true);
                v6.getChildren().add(lblann); // Add label to the layout
            }
            
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        //----------------------------Leaderboard scene----------------------------//
        retrieveLeaderboardData();
        fetchUserScore(tfuser.getText());
        VBox v7 = new VBox();
        v7.setMinHeight(750);
        v7.setMinWidth(1200);
        v7.setMaxHeight(750);
        v7.setMaxWidth(1200);
        v7.setAlignment(Pos.CENTER);
        v7.setSpacing(20);
        v7.setPadding(new Insets(20));
        HBox h9 = new HBox();
        h9.getChildren().addAll(lblicon4,btnback4);
        h9.setSpacing(800);
        h9.setPadding(new Insets(20));
        h9.setMaxHeight(100);
        VBox v8 = new VBox();
        v8.setMinHeight(500);
        v8.setMinWidth(1000);
        v8.setMaxHeight(500);
        v8.setMaxWidth(1000);
        v8.setAlignment(Pos.CENTER);
        v8.setSpacing(20);
        v8.setPadding(new Insets(20));
        v8.setStyle("-fx-border-color: black; "
                + "-fx-border-width: 2px;"
                + "-fx-background-color:#F4F7FC;");
        v7.getChildren().addAll(h9,v8);
        for (String data : leaderboardData) {
            Label lblldb = new Label(data);
            lblldb.setStyle("-fx-font-size:35px;" 
                        + "-fx-font-family:Nirmala UI;"
                        + "-fx-text-fill:#28527A;"
                        + "-fx-border-color: #28527A;"
                        + "-fx-border-width: 1px;");
                lblldb.setMinWidth(960);
                lblldb.setWrapText(true);
            v8.getChildren().add(lblldb);
        }
            Label userLabel = new Label(userScore);
            userLabel.setStyle("-fx-font-size:35px;" 
                        + "-fx-font-family:Nirmala UI;"
                        + "-fx-text-fill:#28527A;"
                        + "-fx-border-color: #28527A;"
                        + "-fx-border-width: 1px;");
            v8.getChildren().add(userLabel);
            
        //----------------------------About scene----------------------------//
        VBox v9 = new VBox();
        v9.setMinHeight(750);
        v9.setMinWidth(1200);
        v9.setMaxHeight(750);
        v9.setMaxWidth(1200);
        v9.setAlignment(Pos.CENTER);
        v9.setSpacing(20);
        v9.setPadding(new Insets(20));
        HBox h10 = new HBox();
        h10.getChildren().addAll(lblicon5,btnback5);
        h10.setSpacing(800);
        h10.setPadding(new Insets(20));
        h10.setMaxHeight(100);
        GridPane p2 = new GridPane();
        p2.setMinHeight(500);
        p2.setMinWidth(1000);
        p2.setMaxHeight(500);
        p2.setMaxWidth(1000);
        p2.add(new Label("Author "),0,0);
        p2.add(lblauthor,1,0);
        p2.add(new Label("Student ID "),0,1);
        p2.add(new Label("B210064A"),1,1);
        p2.add(new Label("Batch"),0,2);
        p2.add(new Label("BoS21-A1"),1,2);
        p2.add(new Label("Contact number    "),0,3);
        p2.add(new Label("016-7330535"),1,3);
        p2.add(new Label("Email"),0,4);
        p2.add(new Label("B210064A@sc.edu.my"),1,4);
        p2.add(new Label("Supervisor"),0,5);
        p2.add(new Label("Ms.Noor Fatihah Bt Mazlam"),1,5);
        p2.add(new Label("Programme"),0,6);
        p2.add(new Label("Bachelor of Software Engineering (Honours)"),1,6);
        p2.add(new Label("Special thanks"),0,7);
        p2.add(new Label("Art Illustration:Anonymous"),1,7);
        p2.getStyleClass().add("gp");
        
        p2.setAlignment(Pos.CENTER);
        p2.setHgap(10);
        p2.setVgap(10);
        p2.setPadding(new Insets(20));
        p2.setStyle("-fx-border-color: black; "
                + "-fx-border-width: 2px;"
                + "-fx-background-color:#F4F7FC;");
        v9.getChildren().addAll(h10,p2);
        
        //----------------------------Setting scene----------------------------//
        VBox v10 = new VBox();
        v10.setMinHeight(750);
        v10.setMinWidth(1200);
        v10.setMaxHeight(750);
        v10.setMaxWidth(1200);
        v10.setAlignment(Pos.CENTER);
        v10.setSpacing(20);
        v10.setPadding(new Insets(20));
        HBox h11 = new HBox();
        h11.getChildren().addAll(lblicon6,btnback6);
        h11.setSpacing(800);
        h11.setPadding(new Insets(20));
        h11.setMaxHeight(100);
        VBox v11 = new VBox();
        v11.setMinHeight(500);
        v11.setMinWidth(1000);
        v11.setMaxHeight(500);
        v11.setMaxWidth(1000);
        v11.setAlignment(Pos.CENTER);
        v11.setSpacing(20);
        v11.setPadding(new Insets(20));
        v11.setStyle("-fx-border-color: black; "
                + "-fx-border-width: 2px;"
                + "-fx-background-color:#F4F7FC;");
        v11.getChildren().addAll(lbluser2,tfgend2,tfpnum2,tfmail2);

        lbluser2.setStyle("-fx-text-fill: #28527A;"
                + "-fx-font-size:25px;"
                + "-fx-font-family:Nirmala UI;");
        tfgend2.setMaxWidth(450);
        tfpnum2.setMaxWidth(450);
        tfmail2.setMaxWidth(450);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/iqtesting","root","")) {
            String query = "SELECT gender,pnum,mail FROM userinfo WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, tfuser.getText());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String gender =rs.getString("gender");
                String pnum =rs.getString("pnum");
                String mail =rs.getString("mail");
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
        
        tfgend2.setPromptText("Gender");
        tfpnum2.setPromptText("Phone Number");
        tfmail2.setPromptText("Email");
        v10.getChildren().addAll(h11,v11,btnupt);
        
        //----------------------------Admin scene----------------------------//
        HBox h12 = new HBox();
        h12.setMinHeight(500);
        h12.setMinWidth(1000);
        h12.setMaxHeight(500);
        h12.setMaxWidth(1000);
        h12.setAlignment(Pos.CENTER);
        h12.setSpacing(20);
        h12.setPadding(new Insets(20));
        h12.getChildren().addAll(btnann,btnadmin,btnlogout2);
        VBox v12 = new VBox();
        v12.setMinHeight(750);
        v12.setMinWidth(1200);
        v12.setMaxHeight(750);
        v12.setMaxWidth(1200);
        v12.setAlignment(Pos.CENTER);
        v12.setSpacing(20);
        v12.setPadding(new Insets(20));
        v12.getChildren().addAll(lblicon8,h12);
        
        VBox v13 = new VBox();
        v13.setMinHeight(750);
        v13.setMinWidth(1200);
        v13.setMaxHeight(750);
        v13.setMaxWidth(1200);
        v13.setAlignment(Pos.CENTER);
        v13.setSpacing(20);
        v13.setPadding(new Insets(20));
        HBox h13 = new HBox();
        h13.getChildren().addAll(lblicon7,btnback7);
        h13.setSpacing(800);
        h13.setPadding(new Insets(20));
        h13.setMaxHeight(100);
        VBox v14 = new VBox();
        v14.setMinHeight(500);
        v14.setMinWidth(1000);
        v14.setMaxHeight(500);
        v14.setMaxWidth(1000);
        v14.setAlignment(Pos.CENTER);
        v14.setSpacing(20);
        v14.setPadding(new Insets(20));
        v14.setStyle("-fx-border-color: black; "
                + "-fx-border-width: 2px;"
                + "-fx-background-color:#F4F7FC;");
        v14.getChildren().addAll(tfann);
        v13.getChildren().addAll(h13,v14,btnaddann);
        
        VBox v15= new VBox();
        v15.getChildren().addAll(lbladmin,tfadmin,tfapass,btnaddadmin,btnback8);
        lbladmin.setStyle("-fx-font-family:Wingdings 3;"
                + "-fx-font-size:40px;");
        tfadmin.setPromptText("Admin Username");
        tfadmin.setMaxWidth(450);
        tfapass.setPromptText("Password");
        tfapass.setMaxWidth(450);
        btnaddadmin.setStyle("-fx-background-color:#000000;"
                + "-fx-text-fill:#FFFFFF;"
                + "-fx-font-size:25px;");
        
        v15.setStyle("-fx-background-color:#FFFFFF");
        v15.setAlignment(Pos.CENTER);
        v15.setSpacing(30);
        v15.setPrefWidth(600);
        HBox h14 = new HBox();
        h14.setPrefWidth(1200);
        h14.getChildren().addAll(lblIQT3,v15);
        
        //----------------------------Scene settings----------------------------//
        Scene scene1 = new Scene(sp,1200,750);
        Scene scene2 = new Scene(h1,1200,750);
        Scene scene3 = new Scene(h2,1200,750);
        Scene scene4 = new Scene(bp,1200,750);
        Scene scene5 = new Scene(v3,1200,750);
        Scene scene6 = new Scene(v4,1200,750);
        Scene scene7 = new Scene(v5,1200,750);
        Scene scene8 = new Scene(v7,1200,750);
        Scene scene9 = new Scene(v9,1200,750);
        Scene scene10 = new Scene(v10,1200,750);
        Scene scene11 = new Scene(v12,1200,750);
        Scene scene12 = new Scene(v13,1200,750);
        Scene scene13 = new Scene(h14,1200,750);
         String css = this.getClass().getResource("style/style.css").toExternalForm(); 
         scene1.getStylesheets().add(css);
         scene2.getStylesheets().add(css);
         scene3.getStylesheets().add(css);
         scene4.getStylesheets().add(css);
         scene5.getStylesheets().add(css);
         scene6.getStylesheets().add(css);
         scene7.getStylesheets().add(css);
         scene8.getStylesheets().add(css);
         scene9.getStylesheets().add(css);
         scene10.getStylesheets().add(css);
         scene11.getStylesheets().add(css);
         scene12.getStylesheets().add(css);
         scene13.getStylesheets().add(css);
        
        //----------------------------Button events----------------------------//
        //----------------------------Mouse Entered&Exited Events----------------------------//
        //----------------------------Start scene----------------------------//
        btnstrt.setOnMouseEntered(e ->{
            btnstrt.setCursor(Cursor.HAND);
            btnstrt.setStyle("-fx-background-color:#000000;"
                    + "-fx-font-family:Stencil;"
                    + "-fx-font-size:52px;"
                    + "-fx-text-fill:#FFFFFF");
        });
        btnstrt.setOnMouseExited(e ->{
            btnstrt.setCursor(Cursor.DEFAULT);
            btnstrt.setStyle("-fx-background-color:transparent;"
                    + "-fx-font-family:Stencil;"
                    + "-fx-font-size:52px;"
                    + "-fx-text-fill:#000000");
        });
        //----------------------------Login & register scene----------------------------//
        btnlogin.setOnMouseEntered(e ->{
            btnlogin.setCursor(Cursor.HAND);
        });
        btnlogin.setOnMouseExited(e ->{
            btnlogin.setCursor(Cursor.DEFAULT);
        });
        btnregi.setOnMouseEntered(e ->{
            this.btnregi.setUnderline(true);
            btnregi.setCursor(Cursor.HAND);
        });
        btnregi.setOnMouseExited(e ->{
            this.btnregi.setUnderline(false);
            btnregi.setCursor(Cursor.DEFAULT);
        });
        btnregis.setOnMouseEntered(e ->{
            btnregis.setCursor(Cursor.HAND);
        });
        btnregis.setOnMouseExited(e ->{
            btnregis.setCursor(Cursor.DEFAULT);
        });
        btnblogin.setOnMouseEntered(e ->{
            btnblogin.setCursor(Cursor.HAND);
        });
        btnblogin.setOnMouseExited(e ->{
            btnblogin.setCursor(Cursor.DEFAULT);
        });
        
        //----------------------------User Interface scene----------------------------//
        btntest.setOnMouseEntered(e ->{
            btntest.setCursor(Cursor.HAND);
        });
        btntest.setOnMouseExited(e ->{
            btntest.setCursor(Cursor.DEFAULT);
        });
        btnldb.setOnMouseEntered(e ->{
            btnldb.setCursor(Cursor.HAND);
        });
        btnldb.setOnMouseExited(e ->{
            btnldb.setCursor(Cursor.DEFAULT);
        });
        btnhome.setOnMouseEntered(e ->{
            btnhome.setCursor(Cursor.HAND);
        });
        btnhome.setOnMouseExited(e ->{
            btnhome.setCursor(Cursor.DEFAULT);
        });
        btnabt.setOnMouseEntered(e ->{
            btnabt.setCursor(Cursor.HAND);
        });
        btnabt.setOnMouseExited(e ->{
            btnabt.setCursor(Cursor.DEFAULT);
        });
        btnlogout.setOnMouseEntered(e ->{
            btnlogout.setCursor(Cursor.HAND);
        });
        btnlogout.setOnMouseExited(e ->{
            btnlogout.setCursor(Cursor.DEFAULT);
        });
        btnannounce.setOnMouseEntered(e ->{
            btnannounce.setCursor(Cursor.HAND);
        });
        btnannounce.setOnMouseExited(e ->{
            btnannounce.setCursor(Cursor.DEFAULT);
        });
        btnmessage.setOnMouseEntered(e ->{
            btnmessage.setCursor(Cursor.HAND);
        });
        btnmessage.setOnMouseExited(e ->{
            btnmessage.setCursor(Cursor.DEFAULT);
        });
        btnsetting.setOnMouseEntered(e ->{
            btnsetting.setCursor(Cursor.HAND);
        });
        btnsetting.setOnMouseExited(e ->{
            btnsetting.setCursor(Cursor.DEFAULT);
        });
        btnnext.setOnMouseEntered(e ->{
            btnnext.setCursor(Cursor.HAND);
        });
        btnnext.setOnMouseExited(e ->{
            btnnext.setCursor(Cursor.DEFAULT);
        });
        btnback.setOnMouseEntered(e ->{
            btnback.setCursor(Cursor.HAND);
        });
        btnback.setOnMouseExited(e ->{
            btnback.setCursor(Cursor.DEFAULT);
        });
        btnsubmit.setOnMouseEntered(e ->{
            btnsubmit.setCursor(Cursor.HAND);
        });
        btnsubmit.setOnMouseExited(e ->{
            btnsubmit.setCursor(Cursor.DEFAULT);
        });
        btnback2.setOnMouseEntered(e ->{
            btnback2.setCursor(Cursor.HAND);
        });
        btnback2.setOnMouseExited(e ->{
            btnback2.setCursor(Cursor.DEFAULT);
        });
        btnback3.setOnMouseEntered(e ->{
            btnback3.setCursor(Cursor.HAND);
        });
        btnback3.setOnMouseExited(e ->{
            btnback3.setCursor(Cursor.DEFAULT);
        });
        btnback4.setOnMouseEntered(e ->{
            btnback4.setCursor(Cursor.HAND);
        });
        btnback4.setOnMouseExited(e ->{
            btnback4.setCursor(Cursor.DEFAULT);
        });
        btnback5.setOnMouseEntered(e ->{
            btnback5.setCursor(Cursor.HAND);
        });
        btnback5.setOnMouseExited(e ->{
            btnback5.setCursor(Cursor.DEFAULT);
        });
        btnback6.setOnMouseEntered(e ->{
            btnback6.setCursor(Cursor.HAND);
        });
        btnback6.setOnMouseExited(e ->{
            btnback6.setCursor(Cursor.DEFAULT);
        });
        btnback7.setOnMouseEntered(e ->{
            btnback7.setCursor(Cursor.HAND);
        });
        btnback7.setOnMouseExited(e ->{
            btnback7.setCursor(Cursor.DEFAULT);
        });
        btnback8.setOnMouseEntered(e ->{
            btnback8.setCursor(Cursor.HAND);
        });
        btnback8.setOnMouseExited(e ->{
            btnback8.setCursor(Cursor.DEFAULT);
        });
        btnlogout2.setOnMouseEntered(e ->{
            btnlogout2.setCursor(Cursor.HAND);
        });
        btnlogout2.setOnMouseExited(e ->{
            btnlogout2.setCursor(Cursor.DEFAULT);
        });
        btnann.setOnMouseEntered(e ->{
            btnann.setCursor(Cursor.HAND);
        });
        btnann.setOnMouseExited(e ->{
            btnann.setCursor(Cursor.DEFAULT);
        });
        btnaddann.setOnMouseEntered(e ->{
            btnaddann.setCursor(Cursor.HAND);
        });
        btnaddann.setOnMouseExited(e ->{
            btnaddann.setCursor(Cursor.DEFAULT);
        });
        btnadmin.setOnMouseEntered(e ->{
            btnadmin.setCursor(Cursor.HAND);
        });
        btnadmin.setOnMouseExited(e ->{
            btnadmin.setCursor(Cursor.DEFAULT);
        });
        btnaddadmin.setOnMouseEntered(e ->{
            btnaddadmin.setCursor(Cursor.HAND);
        });
        btnaddadmin.setOnMouseExited(e ->{
            btnaddadmin.setCursor(Cursor.DEFAULT);
        });
        
        //----------------------------Button Clicked Events----------------------------//
        btnstrt.setOnAction(e -> {
            primaryStage.setScene(scene2);
            h1.requestFocus();
        });
        btnregi.setOnAction(e -> {
            primaryStage.setScene(scene3);
            h2.requestFocus();
        });
        btntest.setOnAction(e -> {
            primaryStage.setScene(scene5);
            v3.requestFocus();
        });
        btnblogin.setOnAction(e -> {
            primaryStage.setScene(scene2);
            h1.requestFocus();
        });
        btnback.setOnAction(e -> {
            primaryStage.setScene(scene4);
            bp.requestFocus();
        });
        btnback2.setOnAction(e -> {
            lblques.setGraphic(new ImageView(new Image("Q1/Q1.jpg")));
            rA.setGraphic(new ImageView(new Image("Q1/Q1A1.jpg")));
            rB.setGraphic(new ImageView(new Image("Q1/Q1A2.jpg")));
            rC.setGraphic(new ImageView(new Image("Q1/Q1A3.jpg")));
            rD.setGraphic(new ImageView(new Image("Q1/Q1A4.jpg")));
            v3.getChildren().remove(btnsubmit);
            v3.getChildren().addAll(btnnext,progressBar);
            pb=0;
            progressBar.setProgress(pb);
            primaryStage.setScene(scene4);
            bp.requestFocus();
        });
        btnback3.setOnAction(e -> {
            primaryStage.setScene(scene4);
            bp.requestFocus();
        });
        btnback4.setOnAction(e -> {
            primaryStage.setScene(scene4);
            bp.requestFocus();
        });
        btnback5.setOnAction(e -> {
            primaryStage.setScene(scene4);
            bp.requestFocus();
        });
        btnback6.setOnAction(e -> {
            primaryStage.setScene(scene4);
            bp.requestFocus();
        });
        btnback7.setOnAction(e -> {
            primaryStage.setScene(scene11);
            v12.requestFocus();
        });
        btnback8.setOnAction(e -> {
            primaryStage.setScene(scene11);
            v12.requestFocus();
        });
        btnannounce.setOnAction(e -> {
            primaryStage.setScene(scene7);
            v5.requestFocus();
            
        });
        btnldb.setOnAction(e -> {
            if(userLabel.getText()==""){
                userLabel.setStyle("-fx-border-width: 0px;");
            }
            fetchUserScore(lbluser.getText());
            primaryStage.setScene(scene8);
            v7.requestFocus();  
        });
        btnabt.setOnAction(e -> {
            primaryStage.setScene(scene9);
            v9.requestFocus();  
        });
        btnlogout.setOnAction(e -> {
            clear();
            i=1;
            Info("You have logged out the system successfully");
            lbluser.setText("");
            primaryStage.setScene(scene2);
            h1.requestFocus();  
        });
        btnlogout2.setOnAction(e -> {
            clear();
            i=1;
            Info("You have logged out the system");
            lbluser.setText("");
            primaryStage.setScene(scene2);
            h1.requestFocus();  
        });
        btnsetting.setOnAction(e -> {
            primaryStage.setScene(scene10);
            v10.requestFocus();  
        });
        btnann.setOnAction(e -> {
            primaryStage.setScene(scene12);
            v13.requestFocus(); 
        });
        btnadmin.setOnAction(e -> {
            primaryStage.setScene(scene13);
            h14.requestFocus();
        });
        
        btnaddann.setOnAction(e -> {
            if(tfann.getText()==""){
                displayError("There are no words in announcement!");
            }else{
                try{
                    String sql ="INSERT INTO announce(announce) VALUES (?)";
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1,tfann.getText());
                    int rows = statement.executeUpdate();
                    if(rows>0){
                        Info("A new announcement has been applied!");
                        tfann.setText("");
            }
                }catch(Exception ex){  
                    }
            }
        });
        
        btnaddadmin.setOnAction(e -> {
            String username = tfadmin.getText();
            if(findName(username)){
          displayError("The username has been taken"); 
          tfadmin.setText("");
          tfapass.setText("");
          h14.requestFocus();
        }else{
            try{
        String sql ="INSERT INTO usersauth(username,password,usertype) VALUES (?,?,?)";
         PreparedStatement statement = con.prepareStatement(sql);
         statement.setString(1,tfadmin.getText());
         statement.setString(2,tfapass.getText());
         statement.setString(3,"admin");
         int rows = statement.executeUpdate();
                    if(rows>0){
                        Info("A new admin has been added!");
                        tfadmin.setText("");
                        tfapass.setText("");
            }
         }catch(Exception ex){  
                    }
            }
        });
        
        btnnext.setOnAction(e->{
            int temp =i;
            for(i=temp;i<=25;i++){
                lblques.setGraphic(new ImageView(new Image("Q"+i+"/Q"+i+".jpg")));
                rA.setGraphic(new ImageView(new Image("Q"+i+"/Q"+i+"A1.jpg")));
                rB.setGraphic(new ImageView(new Image("Q"+i+"/Q"+i+"A2.jpg")));
                rC.setGraphic(new ImageView(new Image("Q"+i+"/Q"+i+"A3.jpg")));
                rD.setGraphic(new ImageView(new Image("Q"+i+"/Q"+i+"A4.jpg")));
                if(i==2){
                    if(rB.isSelected())
                        correctcount++;
                }
                if(i==3){
                    if(rC.isSelected())
                        correctcount++;
                }
                if(i==4){
                    if(rB.isSelected())
                        correctcount++;
                }
                if(i==5){
                    if(rA.isSelected())
                        correctcount++;
                }
                if(i==6){
                    if(rA.isSelected())
                        correctcount++;
                }
                if(i==7){
                    if(rB.isSelected())
                        correctcount++;
                }
                if(i==8){
                    if(rD.isSelected())
                        correctcount++;
                }
                if(i==9){
                    if(rA.isSelected())
                        correctcount++;
                }
                if(i==10){
                    if(rD.isSelected())
                        correctcount++;
                }
                if(i==11){
                    if(rA.isSelected())
                        correctcount++;
                }
                if(i==12){
                    if(rC.isSelected())
                        correctcount++;
                }
                if(i==13){
                    if(rD.isSelected())
                        correctcount++;
                }
                if(i==14){
                    if(rC.isSelected())
                        correctcount++;
                }
                if(i==15){
                    if(rA.isSelected())
                        correctcount++;
                }
                if(i==16){
                    if(rB.isSelected())
                        correctcount++;
                }
                if(i==17){
                    if(rD.isSelected())
                        correctcount++;
                }
                if(i==18){
                    if(rA.isSelected())
                        correctcount++;
                }
                if(i==19){
                    if(rA.isSelected())
                        correctcount++;
                }
                if(i==20){
                    if(rD.isSelected())
                        correctcount++;
                }
                if(i==21){
                    if(rC.isSelected())
                        correctcount++;
                }
                if(i==22){
                    if(rB.isSelected())
                        correctcount++;
                }
                if(i==23){
                    if(rD.isSelected())
                        correctcount++;
                }
                if(i==24){
                    if(rC.isSelected())
                        correctcount++;
                }
                if(i==25){
                    if(rB.isSelected())
                        correctcount++;
                }
                if(i==26){
                    if(rC.isSelected())
                        correctcount++;
                }
                rA.setSelected(false);
                rB.setSelected(false);
                rC.setSelected(false);
                rD.setSelected(false);
                i++;
                break;
            }
            pb = pb+0.04;
            progressBar.setProgress(pb);
            correctcount=correctcount;
            if(i==26){
            v3.getChildren().removeAll(btnnext,progressBar);
            v3.getChildren().add(btnsubmit);
            }
            
        });
        
        btnsubmit.setOnAction(e -> {
            i=1;
            int score = Scores();
            try{
         String sql ="UPDATE userscores SET score=? WHERE username=?";
         PreparedStatement statement = con.prepareStatement(sql);
         statement.setInt(1,score);
         String username = tfuser.getText();
         if(username == null || username.isEmpty()) {
            System.out.println("Username is null or empty. Check tfuser.getText()!");
         } else {
        statement.setString(2, username);
        int rowsUpdated = statement.executeUpdate();
        
                if (rowsUpdated > 0) {
                    System.out.println("Data updated successfully!");
                }else {
                    System.out.println("No rows were updated. Check your SQL query or username.");
                }
        }
            }catch(SQLException ex){         
                ex.printStackTrace();
            }catch(NullPointerException npex){
                npex.printStackTrace();
            }
            primaryStage.setScene(scene6);
            lbliq.setText(" Your IQ Score is "+Integer.toString(Scores())+" ");
            if(Scores()>124){
                lblimg.setGraphic(new ImageView(new Image("Score/highscr.jpg")));
            }else if(Scores()<124&&Scores()>=100){
                lblimg.setGraphic(new ImageView(new Image("Score/midscr.jpg")));
            }else if(Scores()<100&&Scores()>=80){
                lblimg.setGraphic(new ImageView(new Image("Score/lowscr.jpg")));
            }
        });
        
        btnupt.setOnAction(e ->{
            try{
         String sql ="UPDATE userinfo SET gender=?, pnum=?, mail=? WHERE username=?";
         PreparedStatement statement = con.prepareStatement(sql);
         statement.setString(1,tfgend2.getText());
         statement.setString(2,tfpnum2.getText());
         statement.setString(3,tfmail2.getText());
         String username = tfuser.getText();
         if(username == null || username.isEmpty()) {
            System.out.println("Username is null or empty. Check tfuser.getText()!");
         } else {
        statement.setString(4, username);
        int rowsUpdated = statement.executeUpdate();
        
                if (rowsUpdated > 0) {
                    Info("Data updated successfully!");
                    tfgend2.setText("");
                    tfpnum2.setText("");
                    tfmail2.setText("");
                }else {
                    System.out.println("No rows were updated. Check your SQL query or username.");
                }
        }
            }catch(SQLException ex){         
                ex.printStackTrace();
            }catch(NullPointerException npex){
                npex.printStackTrace();
            }
            primaryStage.setScene(scene4);
            bp.requestFocus();
            
        });
        
        btnregis.setOnAction(e -> {
        String passr =tfpassr.getText();
        String passcom =tfpasscom.getText();
        if(passr.equals(passcom)){
        String username =tfuserr.getText();
        if(findName(username)){
          displayError("The username has been taken"); 
          clear();
          h2.requestFocus();
        }else{
            try{
        String sql ="INSERT INTO usersauth(username,password,usertype) VALUES (?,?,?)";
         PreparedStatement statement = con.prepareStatement(sql);
         statement.setString(1,tfuserr.getText());
         statement.setString(2,tfpassr.getText());
         statement.setString(3,"user");
         
         int rows = statement.executeUpdate();
        String sql2 ="INSERT INTO userinfo(username,gender,pnum,mail) VALUES (?,?,?,?)";
         PreparedStatement statement2 = con.prepareStatement(sql2);
         statement2.setString(1,tfuserr.getText());
         statement2.setString(2,tfgend.getText());
         statement2.setString(3,tfpnum.getText());
         statement2.setString(4,tfmail.getText());
         statement2.executeUpdate();
        String sql3 ="INSERT INTO userscores(username,score) VALUES (?,?)";
         PreparedStatement statement3 = con.prepareStatement(sql3);
         statement3.setString(1,tfuserr.getText());
         statement3.setInt(2,0);
         statement3.executeUpdate();
         if(rows>0){
             Info("A new user has been inserted successfully");
             clear();
             primaryStage.setScene(scene2);
            }
      }catch(Exception ex){  
                }
            }
        }
        else{
            displayError("password doesn't match");
            tfpassr.setText("");
            tfpasscom.setText("");
            tfpassr.requestFocus();
            }
        });
        
        btnlogin.setOnAction(e->{ 
            String username = tfuser.getText();
            String password = tfpass.getText();
            
            String usertype = authenticateUser(username, password);
            if (usertype != null) {
                if (usertype.equals("admin")) {
                    primaryStage.setScene(scene11);
                } else if (usertype.equals("user")) {
                    Info("Welcome, "+tfuser.getText());
                    primaryStage.setScene(scene4);
                    bp.requestFocus();
                    lbluser.setText(tfuser.getText()+" ");
                    lbluser2.setText(tfuser.getText());
                    retrieveLeaderboardData();
                    fetchUserScore(tfuser.getText());
                }
            else{
                displayError("username and password do not matched");
                clear();
                }
            }
        });
        
        
        
        
        primaryStage.setTitle("Gamification IQ Testing");
        primaryStage.setScene(scene1);
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }
    
    public boolean findName(String username){
 
      try{
       con = DriverManager.getConnection("jdbc:mysql://localhost/iqtesting","root","");
       String searchSQL ="SELECT * FROM usersauth WHERE username=?";
       PreparedStatement statement = con.prepareStatement(searchSQL);
       statement.setString(1,username);
       result = statement.executeQuery();
       return result.next();
       
      }catch(SQLException e){         
          e.printStackTrace();
      }
      return false;
    }
    
    private String authenticateUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/iqtesting","root","")) {
            String query = "SELECT usertype FROM usersauth WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("usertype");
            }
            else{
                displayError("Username and password do not matched or username not found!");
                clear();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
        return null; // Return null if authentication fails
    }
    
    public int Scores(){
        wrongcount= 25-correctcount;
        if(wrongcount==0||wrongcount==1)
            scores=145;
        if(wrongcount==2)    
            scores=142;
        if(wrongcount==3)    
            scores=138;
        if(wrongcount==4)    
            scores=135;
        if(wrongcount==5)    
            scores=133;
        if(wrongcount==6)    
            scores=131;
        if(wrongcount==7)    
            scores=128;
        if(wrongcount==8)    
            scores=125;
        if(wrongcount==9)    
            scores=121;
        if(wrongcount==10)    
            scores=118;
        if(wrongcount==11)    
            scores=115;
        if(wrongcount==12)    
            scores=112;
        if(wrongcount==13)    
            scores=110;
        if(wrongcount==14)    
            scores=107;
        if(wrongcount==15)    
            scores=105;
        if(wrongcount==16)    
            scores=102;
        if(wrongcount==17)    
            scores=100;
        if(wrongcount==18)    
            scores=97;
        if(wrongcount==19)    
            scores=95;
        if(wrongcount==20)    
            scores=92;
        if(wrongcount==21)    
            scores=90;
        if(wrongcount==22)    
            scores=88;
        if(wrongcount==23)    
            scores=86;
        if(wrongcount==24)    
            scores=83;
        if(wrongcount==25)    
            scores=80;
        return scores;
    }
    
    private void retrieveLeaderboardData() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iqtesting","root","")) {
            String query = "SELECT username, score FROM userscores ORDER BY score DESC";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                int count = 0;
                while (resultSet.next() && count < 3) {
                    String username = resultSet.getString("username");
                    int score = resultSet.getInt("score");
                    leaderboardData.add((count+1)+"   "+username + " : " + score);
                    count++;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle any database access exceptions
        }
    }
    
    private void fetchUserScore(String username) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iqtesting","root","")) {
            String query = "SELECT score FROM userscores WHERE username = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                 statement.setString(1,username);
                 ResultSet rs = statement.executeQuery(); 
                if (rs.next()) {
                    int score = rs.getInt("score");
                    userScore = "Your score: " + score;
                } 
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any database access exceptions
        }
    }
    
    public void displayError(String msg){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(msg);
    alert.show();
    }
    
    public void Info(String msg){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setHeaderText(null);
    alert.setContentText(msg);
    alert.show();
    }
       
    public void clear(){
        tfuser.setText("");
        tfpass.setText("");
        tfuserr.setText("");
        tfpassr.setText("");
        tfpasscom.setText("");
        tfgend.setText("");
        tfpnum.setText("");
        tfmail.setText("");
    }
    
    private void initializeDB(){
     String url="jdbc:mysql://localhost:3306/iqtesting";
      String username="root";
      String password="";
        try{
        con = DriverManager.getConnection(url,username,password);
            if(con!=null){
          System.out.println("Connected to database");
               }   
      }catch(SQLException e){           
          e.printStackTrace();
      }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
