package application;
	
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {
	private final String str ="Brick Breaker";
	Integer healths=3;
	 Integer hit=0;
	 Integer level=1;
		Timeline timeline2;
		Rectangle bottomZone,paddle,paddle2;
		Circle lifeC1,lifeC2,lifeC3;
		Scene scene,preGame,game,postGame,about,help;
		Circle ball;
		ArrayList<Rectangle> allBricks = new ArrayList<>();
		ArrayList<Rectangle> allImaginaryBricks = new ArrayList<>();
		 Integer score= 0;
		 Text l1=new Text();
		 Pane secondScene;
		 TranslateTransition animate7;
		 HBox lifeContainer=new HBox(5);
		 
	
	@Override
	public void start(Stage primaryStage) {
	
	
		try {
			
// ----------------------------------- Main Scene ----------------------------------------
//		
StackPane paneS=new StackPane();
VBox bgPane=new VBox();
VBox root = new VBox(10);
root.setId("pane1");	
HBox header=new HBox(100);
header.setPadding(new Insets(0,40,40,40));
Text t1=new Text();
//t1.setText("Brick Breaker");
t1.setId("t1");
t1.setStroke(Color.GREEN);
t1.setStrokeWidth(1);

header.getChildren().add(t1);
header.setAlignment(Pos.CENTER);
//----------------------------------- Sounds ----------------------------------------
String path = "F:\\\\3rd Semester\\\\OOP\\\\Fx\\\\JavaProject\\\\src\\\\images\\\\mainSound.mp3";
Media m1=new Media(new File(path).toURI().toString());
MediaPlayer mediaPlayer = new MediaPlayer(m1);  
//Code to Repeat Music
mediaPlayer.setOnEndOfMedia(new Runnable() {
  public void run() {
    mediaPlayer.seek(Duration.ZERO);
  }
});
//by setting this property to true, the audio will be played   
mediaPlayer.setAutoPlay(true);

String path2 = "F:\\\\3rd Semester\\\\OOP\\\\Fx\\\\JavaProject\\\\src\\\\images\\\\paddleCollide.mp3";
Media m2=new Media(new File(path2).toURI().toString());
String path3 = "F:\\\\3rd Semester\\\\OOP\\\\Fx\\\\JavaProject\\\\src\\\\images\\\\bCollide.mp3";
Media m3=new Media(new File(path3).toURI().toString());
String path4 = "F:\\\\3rd Semester\\\\OOP\\\\Fx\\\\JavaProject\\\\src\\\\images\\\\lifeLoose.mp3";
Media m4=new Media(new File(path4).toURI().toString());
String path5 = "F:\\\\3rd Semester\\\\OOP\\\\Fx\\\\JavaProject\\\\src\\\\images\\\\brickSmash.mp3";
Media m5=new Media(new File(path5).toURI().toString());
MediaPlayer bSmash = new MediaPlayer(m5); 
bSmash.setAutoPlay(false);
String path6 = "F:\\\\3rd Semester\\\\OOP\\\\Fx\\\\JavaProject\\\\src\\\\images\\\\gameWin.mp3";
Media m6=new Media(new File(path6).toURI().toString());
MediaPlayer gameWin = new MediaPlayer(m6);    
gameWin.setAutoPlay(false);

String path7 = "F:\\\\3rd Semester\\\\OOP\\\\Fx\\\\JavaProject\\\\src\\\\images\\\\gameOver.mp3";
Media m7=new Media(new File(path7).toURI().toString());
MediaPlayer gameOver = new MediaPlayer(m7);  
//by setting this property to true, the audio will be played   
gameOver.setAutoPlay(false);
//----------------------------------- Sounds ----------------------------------------

Image img1=new Image(new FileInputStream("F:\\3rd Semester\\OOP\\Fx\\JavaProject\\src\\images\\unmute.png"));
ImageView image1=new ImageView(img1);
image1.setFitWidth(30);
image1.setFitHeight(30);

Button unmute=new Button();
unmute.getStyleClass().add("btn_Sound");
unmute.setGraphic(image1);

Image img2=new Image(new FileInputStream("F:\\3rd Semester\\OOP\\Fx\\JavaProject\\src\\images\\mute.png"));
ImageView image2=new ImageView(img2);
image2.setFitWidth(30);
image2.setFitHeight(30);
Button mute=new Button();
mute.getStyleClass().add("btn_Sound");
mute.setGraphic(image2);
HBox volContainer=new HBox();
volContainer.getChildren().add(unmute);
volContainer.setPadding(new Insets(10,20,0,10));
volContainer.setAlignment(Pos.TOP_RIGHT);
unmute.setOnAction(e->{
	volContainer.getChildren().add(mute);
	volContainer.getChildren().remove(unmute);
	System.out.println("Mute");
	mediaPlayer.stop();
	
});
mute.setOnAction(e->{
	volContainer.getChildren().add(unmute);
	volContainer.getChildren().remove(mute);
	System.out.println("UnMute");
	mediaPlayer.play();
});
  
//----------->Button Container<------------
VBox btnContainer=new VBox(30);
Button btnStart=new Button("Start");
btnStart.getStyleClass().add("btn_primary");
Button btnHelp=new Button("Levels");
btnHelp.getStyleClass().add("btn_primary");
Button btnAbout=new Button("About");
btnAbout.getStyleClass().add("btn_primary");
Button btnExit=new Button("Quit");
btnExit.getStyleClass().add("btn_primary");

btnContainer.getChildren().addAll(btnStart,btnHelp,btnAbout,btnExit);
btnContainer.setAlignment(Pos.CENTER);
//Footer
HBox box=new HBox();
box.setAlignment(Pos.BOTTOM_CENTER);
box.setPadding(new Insets(30,10,10,10));
Text copyRight=new Text("Copy Right 2022");
copyRight.setFont(Font.font(null,FontWeight.MEDIUM,FontPosture.ITALIC,15));
copyRight.setFill(Color.CRIMSON);
box.getChildren().addAll(copyRight);
root.getChildren().addAll(volContainer,header,btnContainer,box);

root.setAlignment(Pos.TOP_CENTER);
//paneS.getChildren().addAll(bgPane,root);
scene = new Scene(root,800,600);
//----------->Main Scene Animations<------------
final IntegerProperty i = new SimpleIntegerProperty(0);
Timeline timeline = new Timeline();
KeyFrame keyFrame = new KeyFrame(
        Duration.seconds(.3),
        event -> {
            if (i.get() > str.length()) {
                timeline.stop();
            } else {
                t1.setText(str.substring(0, i.get()));
                i.set(i.get() + 1);
            }
        });
timeline.getKeyFrames().add(keyFrame);
timeline.setCycleCount(Animation.INDEFINITE);
timeline.play();


//----------->Main Scene Animations<------------

//----------------------------------- Main End ----------------------------------------


//----------------------------------- Pre GAMe ----------------------------------------
VBox gBox1=new VBox();
gBox1.getStyleClass().add("game");
gBox1.setAlignment(Pos.CENTER);

Text pgt1=new Text("Press Enter to Continue");

pgt1.getStyleClass().add("h_Secondary");
gBox1.getChildren().add(pgt1);
//----------->Pre Game  Animations<------------
//paneS2.getChildren().addAll(bgPane2,gBox1);
preGame=new Scene(gBox1,800,600);
TranslateTransition animate6=new TranslateTransition();
animate6.setFromX(-1000);
animate6.setToX(0);
animate6.setDuration(Duration.millis(1000));
animate6.setCycleCount(1);
animate6.setNode(pgt1);
//----------->Pre Game Animations<------------

//----------------------------------- Pre Game End ----------------------------------------

//----------------------------------- Game Scene ----------------------------------------

Pane pane2=new Pane();
VBox resultContainer=new VBox(40);
resultContainer.getStyleClass().add("game");
resultContainer.setAlignment(Pos.CENTER);
StackPane paneS5=new StackPane();
VBox bgPane5=new VBox();
//bgPane5.getChildren().add(v5);
Text result=new Text();
result.getStyleClass().add("result");
HBox textLine=new HBox();
textLine.setAlignment(Pos.CENTER);
Text scoreDesc=new Text("Your Score are ");
scoreDesc.getStyleClass().add("result");
Text finalScore=new Text();
finalScore.getStyleClass().add("finalScore");
Button btn_level=new Button("Play Next Level");
btn_level.getStyleClass().add("btn_primary");
Button btn_StartAgain=new Button("Play Again");
btn_StartAgain.getStyleClass().add("btn_primary");
Button btn_back2=new Button("Back");
btn_back2.getStyleClass().add("btn_primary");
btn_back2.setOnAction(e->{
	healths=3;
	level=1;
	resetScore();
	l1.setText("Score: "+score);
	createBricks();
		paddle.relocate(370, 560);
		paddle2.relocate(360, 560);
		ball.relocate(420, 500);
		lifeContainer.getChildren().addAll(lifeC1,lifeC2,lifeC3);
		resultContainer.getChildren().remove(btn_back2);

	primaryStage.setScene(scene);
});
btn_level.setOnAction(e->{
	if(level<=2) {
		level+=1;
		animate6.play();
		primaryStage.setScene(preGame);
		
	}else if(level==3) {
//		level=1;
//		btn_level.setText("Back");
//		primaryStage.setScene(scene);
		  resultContainer.getChildren().remove(btn_level);
	}
	createBricks();
	
	
	paddle.relocate(370, 560);
	paddle2.relocate(360, 560);
	ball.relocate(420, 500);
});

btn_StartAgain.setOnAction(e->{
//	level=1;
	healths=3;
resetScore();
l1.setText("Score: "+score);
createBricks();
	paddle.relocate(370, 560);
	paddle2.relocate(360, 560);
	ball.relocate(420, 500);
	lifeContainer.getChildren().addAll(lifeC1,lifeC2,lifeC3);
	primaryStage.setScene(preGame);	
});

textLine.getChildren().addAll(scoreDesc,finalScore);
resultContainer.getChildren().addAll(result,textLine);

postGame=new Scene(resultContainer,800,600);


//Pane to Display Bricks 
secondScene=new Pane();
secondScene.setPadding(new Insets(0,60,60,60));
pane2.getStyleClass().add("game");
animate7=new TranslateTransition();
animate7.setFromX(1000);
animate7.setToX(0);
animate7.setDuration(Duration.millis(1000));
animate7.setCycleCount(1);
animate7.setNode(secondScene);
//For Loop to diplay Bricks 

 createBricks();   

//----------->Images for Lives <------------
lifeC1=new Circle(10,10,15);
Image life1=new Image(new FileInputStream("F:\\3rd Semester\\OOP\\Fx\\JavaProject\\src\\images\\heart.png"));	
lifeC1.setFill(new ImagePattern(life1));

 lifeC2=new Circle(10,10,15);
Image life2=new Image(new FileInputStream("F:\\3rd Semester\\OOP\\Fx\\JavaProject\\src\\images\\heart.png"));
lifeC2.setFill(new ImagePattern(life2));

 lifeC3=new Circle(10,10,15);
Image life3=new Image(new FileInputStream("F:\\3rd Semester\\OOP\\Fx\\JavaProject\\src\\images\\heart.png"));
lifeC3.setFill(new ImagePattern(life3));

lifeContainer.getChildren().addAll(lifeC1,lifeC2,lifeC3);
//----------->Images for Lives <------------

//Basic Setu for ball and paddle
ball= new Circle(5,5,7, Color.CRIMSON);
ball.relocate(420, 500);
paddle= new Rectangle(120,15);
paddle.getStyleClass().add("paddle");
paddle.setArcHeight(20);
paddle.setArcWidth(20);
//Paddle2 is an imaginary paddle to detect collisions 
paddle2=new Rectangle(125,15);
paddle2.setFill(Color.TRANSPARENT);
paddle2.setArcHeight(20);
paddle2.setArcWidth(20);
paddle.relocate(370, 560);
paddle2.relocate(360, 560);
Pane thirdPane=new Pane();
secondScene.getChildren().addAll(paddle,paddle2, ball);
thirdPane.getChildren().add(secondScene);
thirdPane.setPadding(new Insets(100,200,0,200));
//controls paddle movement

int movement = 30;

//Timeline2 to create an indefinite bouncing ball

 timeline2 = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {

	  double dx= -5; 
    double dy = -3; 

   public void handle(ActionEvent t) {
        //ball movement
        ball.setLayoutX(ball.getLayoutX() + dx);
        ball.setLayoutY(ball.getLayoutY() + dy);
    
        boolean leftWall = ball.getLayoutX() <= 0; 
        boolean topWall = ball.getLayoutY() < 55;
        boolean rightWall = ball.getLayoutX() >= 790;
        boolean bottomWall = ball.getLayoutY() >= 580;
    
        // If the top wall has been touched, the ball reverses direction.
        if (topWall) {
           dy = dy * -1;
        }
    
        // If the left or right wall has been touched, the ball reverses direction.
        if (leftWall || rightWall) {
            dx = dx * -1;
        }
        if (bottomWall) {
            dy = dy * -1;
         }
    
        //if ball collides with paddle
        if (ball.getBoundsInParent().intersects(paddle2.getBoundsInParent())) {
        	  
        	boolean rightBorder = ball.getLayoutX() >= ((paddle2.getLayoutX() + paddle2.getWidth()) - ball.getRadius());
            boolean leftBorder = ball.getLayoutX() <= (paddle2.getLayoutX() + ball.getRadius());
            boolean bottomBorder = ball.getLayoutY() >= ((paddle2.getLayoutY() + paddle2.getHeight()) - ball.getRadius());
            boolean topBorder = ball.getLayoutY() <= (paddle2.getLayoutY() + ball.getRadius()+1);
      
            if (rightBorder || leftBorder ||bottomBorder ) {
            	 dx *= -1;
            	  System.out.println("Malik");
            }
            dy *= -1;
//            sound effect
            MediaPlayer pCollide = new MediaPlayer(m2);
            pCollide.play();
            System.out.println("Ball collide");
        
        }
   
    
        //if ball and brick collides, remove brick
        Rectangle temp=null;
       
        for(Rectangle brick:allBricks) { 
        
                if(ball.getBoundsInParent().intersects(brick.getBoundsInParent())) {
                	
                	boolean rightBorder = (ball.getLayoutX()-100) >= (((brick.getX()) + (brick.getWidth())) - ball.getRadius());
                    boolean leftBorder = (ball.getLayoutX()-100) <= ((brick.getX()) + (brick.getWidth()));
                    boolean bottomBorder = ball.getLayoutY() >= ((brick.getY() + (brick.getHeight())) - ball.getRadius());
                    boolean topBorder = ball.getLayoutY() <= (brick.getY() + ball.getRadius());
    

                    
//
//                if (rightBorder ||leftBorder ) {
//                  	 dx *= -1 ;                  	  
//                  	 dy *= .8;
//                  	
//                }  
                    if (bottomBorder || topBorder || rightBorder ||leftBorder) {
                          dy *= -1;                 }
//                Sound Effect
                  MediaPlayer bCollide = new MediaPlayer(m3); 
                  bCollide.play();       
//                  If condition to change color of brick
                		if(brick.getFill().equals(Color.RED)) {       
                			temp=brick;
                           secondScene.getChildren().remove(brick);
                           score +=20;
//                           Sound Effect
                           MediaPlayer bSmash = new MediaPlayer(m5);  
                           bSmash.play();
                           l1.setText("Score: "+score);
                		}else if(brick.getFill().equals(Color.LIMEGREEN)) {       
                			brick.setFill(Color.YELLOW);
                			   score +=5;
                			   l1.setText("Score: "+score);
                			
                 		}else if(brick.getFill().equals(Color.YELLOW)) {       
                 			brick.setFill(Color.RED);
                 		   score +=10;
                 		   l1.setText("Score: "+score);
                 		}
                   
                	}
            }
        
        allBricks.remove(temp);
        temp=null;
//        To check Bottom zone Collision 
        if(ball.getBoundsInParent().intersects(bottomZone.getBoundsInParent())){
       	 timeline2.stop();
       	healths--;
           if(healths==2) {
           	ball.relocate(420, 500);
           	paddle.relocate(370, 560);
           	paddle2.relocate(360, 560);
           	lifeContainer.getChildren().remove(lifeC1);
//           	Sound Efect
           	MediaPlayer lifeS = new MediaPlayer(m4);  
         	lifeS.play();
         
           }else if(healths==1) { 
           	ball.relocate(420, 500);
           	paddle.relocate(370, 560);
           	paddle2.relocate(360, 560);
           	lifeContainer.getChildren().remove(lifeC2);
//           	Sound Effect
           	MediaPlayer lifeS = new MediaPlayer(m4);  
         	lifeS.play();
    
           	
           }else if(healths==0) {
            lifeContainer.getChildren().remove(lifeC3);
            result.setText("You Loose The Game");
            resultContainer.getChildren().remove(btn_level);
            resultContainer.getChildren().remove(btn_StartAgain);
          
            resultContainer.getChildren().addAll(btn_StartAgain);
            resultContainer.getChildren().remove(btn_back2);
            resultContainer.getChildren().add(btn_back2);
        
//        Sound Effect
            removeBricks();
            MediaPlayer gameOver = new MediaPlayer(m7); 
            gameOver.play();

//           	System.out.println("Healths " + healths);
           }
  
       }
        if(allBricks.size()==0 && healths>0) {
        if(level==1 || level==2) {
        	result.setText("Level Cleared");
        	resultContainer.getChildren().remove(btn_level);
        	resultContainer.getChildren().add(btn_level);
        }else {
        	result.setText("You Won The Game");
        	resultContainer.getChildren().remove(btn_level);
        	resultContainer.getChildren().remove(btn_back2);
        }
        	System.out.println("U Won");
          	 timeline2.stop();
        	gameWin.play();
        	
            resultContainer.getChildren().remove(btn_StartAgain);
        }
        if(healths==0 || allBricks.size()==0) {
        	finalScore.setText(" " + score);
          	primaryStage.setScene(postGame);
		    primaryStage.setTitle("Game Ended");
           	System.out.println("Game over!");
        }
      
        
    }
    
}));

bottomZone=new Rectangle();
bottomZone.setFill(Color.TRANSPARENT);
bottomZone.setWidth(800);
bottomZone.setHeight(10);
bottomZone.setLayoutY(590);
HBox nav=new HBox();
nav.setAlignment(Pos.TOP_CENTER);
HBox scoreContainer=new HBox();

l1.setText("Score : " + score);
l1.getStyleClass().add("score_label");

scoreContainer.getChildren().add(l1);
scoreContainer.setPadding(new Insets(20,0,0,30));
lifeContainer.setPadding(new Insets(28,0,0,500));

nav.getChildren().addAll(scoreContainer,lifeContainer);
pane2.getChildren().addAll(nav,thirdPane,bottomZone);

game=new Scene(pane2,800,600);



//----------------------------------- Game End ----------------------------------------

//----------------------------------- Help Scene ----------------------------------------

VBox pane3=new VBox(10);
pane3.setAlignment(Pos.TOP_CENTER);
pane3.setId("help");
Text ht1=new Text("Game Levels");
ht1.getStyleClass().add("h_main");

HBox gContainer=new HBox(80);
gContainer.setPadding(new Insets(105,0,0,0));
Button level1=new Button("Level 1");
level1.getStyleClass().add("btn_Level");
Button level2=new Button("Level 2");
level2.getStyleClass().add("btn_Level");
Button level3=new Button("Level 3");
level3.getStyleClass().add("btn_Level");
level1.setOnAction(e->{
	createBricks();
	System.out.println("Level" + level);
	primaryStage.setScene(preGame);
	primaryStage.setTitle("Game");	
	animate6.play();
});
level2.setOnAction(e->{
	level+=1;
//	System.out.println("Level" + level);
	removeBricks();
	createBricks();
	primaryStage.setScene(preGame);
	primaryStage.setTitle("Game");	
	animate6.play();
	
});
level3.setOnAction(e->{
	level+=2;
//	System.out.println("Level" + level);
	removeBricks();
	createBricks();
	primaryStage.setScene(preGame);
	primaryStage.setTitle("Game");	
	animate6.play();
});


gContainer.getChildren().addAll(level1,level2,level3);
gContainer.setAlignment(Pos.TOP_CENTER);
VBox btnContainer2=new VBox();
btnContainer2.setPadding(new Insets(90,0,0,0));
btnContainer2.setAlignment(Pos.CENTER);
Button btnBack=new Button("Back");
btnBack.getStyleClass().add("btn_primary");
btnBack.setOnAction(e->{
	primaryStage.setScene(scene);
	primaryStage.setTitle("Home");
});
btnContainer2.getChildren().add(btnBack);



pane3.getChildren().addAll(ht1,gContainer,btnContainer2);

help=new Scene(pane3,800,600);
FadeTransition f1=new FadeTransition();
f1.setFromValue(0.1);
f1.setToValue(1);
f1.setDuration(Duration.millis(1000));
f1.setNode(gContainer);

TranslateTransition animate5=new TranslateTransition();
animate5.setFromX(1000);
animate5.setToX(0);
animate5.setDuration(Duration.millis(1000));
animate5.setCycleCount(1);
animate5.setNode(gContainer);

//----------------------------------- Help End ----------------------------------------

// ----------------------------------- About Scene ----------------------------------------

VBox pane4=new VBox();
pane4.setAlignment(Pos.TOP_CENTER);
pane4.setId("about");
Text at1=new Text("Our Team");
at1.getStyleClass().add("h_main");
//All profiles container
GridPane teamC=new GridPane();
teamC.setPadding(new Insets(80,20,20,20));
HBox profileContainer=new HBox();
//Profile 1
profileContainer.getStyleClass().add("container");
HBox imageContainer=new HBox();
VBox infoContainer=new VBox();
infoContainer.setPadding(new Insets(0,0,0,20));
Image p1=new Image(new FileInputStream("F:\\3rd Semester\\OOP\\Fx\\JavaProject\\src\\images\\p1.jpeg"));
Circle c1=new Circle(50,50,50);
//will set image as background
c1.setFill(new ImagePattern(p1));
imageContainer.getChildren().add(c1);
Text p1Name=new Text("Shahab Malik");
p1Name.getStyleClass().add("profile_name");
Text p1Desc=new Text("A passionate Web Developer.");
p1Desc.getStyleClass().add("profile_desc");
Hyperlink p1Link=new Hyperlink("View Github Profile");
p1Link.getStyleClass().add("links");
p1Link.setTooltip(new Tooltip("https://github.com/Shahab-Malikk"));

infoContainer.getChildren().addAll(p1Name,p1Desc,p1Link);
profileContainer.getChildren().addAll(imageContainer,infoContainer);

//Profile 2
HBox profileContainer2=new HBox();
profileContainer2.getStyleClass().add("container");
HBox imageContainer2=new HBox();
VBox infoContainer2=new VBox();
infoContainer2.setPadding(new Insets(0,0,0,20));
Image p2=new Image(new FileInputStream("F:\\3rd Semester\\OOP\\Fx\\JavaProject\\src\\images\\p2.png"));
Circle c2=new Circle(50,50,50);
//will set image as background
c2.setFill(new ImagePattern(p2));
imageContainer2.getChildren().add(c2);
Text p2Name=new Text("Laiba Iftikhar");
p2Name.getStyleClass().add("profile_name");
Text p2Desc=new Text("A passionate UI/UX Designer");
p2Desc.getStyleClass().add("profile_desc");
Hyperlink p2Link=new Hyperlink("View Github Profile");
p2Link.getStyleClass().add("links");
p2Link.setTooltip(new Tooltip("https://github.com/LaibaIftikhar"));

infoContainer2.getChildren().addAll(p2Name,p2Desc,p2Link);

profileContainer2.getChildren().addAll(imageContainer2,infoContainer2);

//Profile 3

HBox profileContainer3=new HBox();
profileContainer3.getStyleClass().add("container");
HBox imageContainer3=new HBox();
VBox infoContainer3=new VBox();
infoContainer3.setPadding(new Insets(0,0,0,20));
Image p3=new Image(new FileInputStream("F:\\3rd Semester\\OOP\\Fx\\JavaProject\\src\\images\\p2.png"));
Circle c3=new Circle(50,50,50);
//will set image as background
c3.setFill(new ImagePattern(p3));
imageContainer3.getChildren().add(c3);
Text p3Name=new Text("Zainab Tariq");
p3Name.getStyleClass().add("profile_name");
Text p3Desc=new Text("A passionate UI/UX Designer.");
p3Desc.getStyleClass().add("profile_desc");
Hyperlink p3Link=new Hyperlink("View Github Profile");
p3Link.getStyleClass().add("links");
p3Link.setTooltip(new Tooltip("https://github.com/engr-Zainab"));

infoContainer3.getChildren().addAll(p3Name,p3Desc,p3Link);

profileContainer3.getChildren().addAll(imageContainer3,infoContainer3);

//Profile 4

HBox profileContainer4=new HBox();
profileContainer4.getStyleClass().add("container");
HBox imageContainer4=new HBox();
VBox infoContainer4=new VBox();
infoContainer4.setPadding(new Insets(0,0,0,20));
Image p4=new Image(new FileInputStream("F:\\3rd Semester\\OOP\\Fx\\JavaProject\\src\\images\\p3.jpeg"));
Circle c4=new Circle(50,50,50);
//will set image as background
c4.setFill(new ImagePattern(p4));
imageContainer4.getChildren().add(c4);
Text p4Name=new Text("M Azeem");
p4Name.getStyleClass().add("profile_name");
Text p4Desc=new Text("A passionate Web Developer.");
p4Desc.getStyleClass().add("profile_desc");
Hyperlink p4Link=new Hyperlink("View Github Profile");
p4Link.getStyleClass().add("links");
p4Link.setTooltip(new Tooltip("https://github.com/Shahab-Malikk"));

infoContainer4.getChildren().addAll(p4Name,p4Desc,p4Link);

profileContainer4.getChildren().addAll(imageContainer4,infoContainer4);
//Adding elements to grid
teamC.add(profileContainer,0,0);
teamC.add(profileContainer2, 1, 0);
teamC.add(profileContainer3, 0,1);
teamC.add(profileContainer4, 1, 1);
teamC.setHgap(100);
teamC.setVgap(50);
teamC.setAlignment(Pos.CENTER);
//Profile Cards Animation
TranslateTransition animate1=new TranslateTransition();
animate1.setFromX(1000);
animate1.setToX(0);
animate1.setDuration(Duration.millis(1000));
animate1.setCycleCount(1);
animate1.setNode(profileContainer);
TranslateTransition animate2=new TranslateTransition();
animate2.setFromX(-500);
animate2.setToX(0);
animate2.setDuration(Duration.millis(1000));
animate2.setCycleCount(1);
animate2.setNode(profileContainer2);
TranslateTransition animate3=new TranslateTransition();
animate3.setFromX(1000);
animate3.setToX(0);
animate3.setDuration(Duration.millis(1000));
animate3.setCycleCount(1);
animate3.setNode(profileContainer3);
TranslateTransition animate4=new TranslateTransition();
animate4.setFromX(-500);
animate4.setToX(0);
animate4.setDuration(Duration.millis(1000));
animate4.setCycleCount(1);
animate4.setNode(profileContainer4);

// Links Action

p1Link.setOnAction(e->{
	Hyperlink h = (Hyperlink) e.getTarget();
    String s = h.getTooltip().getText();
    getHostServices().showDocument(s);
    e.consume();
});

p2Link.setOnAction(e->{
	Hyperlink h = (Hyperlink) e.getTarget();
    String s = h.getTooltip().getText();
    getHostServices().showDocument(s);
    e.consume();
});

p3Link.setOnAction(e->{
	Hyperlink h = (Hyperlink) e.getTarget();
    String s = h.getTooltip().getText();
    getHostServices().showDocument(s);
    e.consume();
});

p4Link.setOnAction(e->{
	Hyperlink h = (Hyperlink) e.getTarget();
    String s = h.getTooltip().getText();
    getHostServices().showDocument(s);
    e.consume();
});

VBox btn_Container3=new VBox();
btn_Container3.setPadding(new Insets(60,0,0,0));
btn_Container3.setAlignment(Pos.CENTER);
Button btn_back=new Button("Back");
//Button Action
btn_back.getStyleClass().add("btn_primary");
btn_back.setOnAction(e->{
	primaryStage.setScene(scene);
	primaryStage.setTitle("Home");
});
btn_Container3.getChildren().add(btn_back);
pane4.getChildren().addAll(at1,teamC,btn_Container3);
//paneS4.getChildren().addAll(bgPane4,pane4);
about=new Scene(pane4,800,600);

//----------------------------------- About End ----------------------------------------

		
//----------------------------------- Buttons Actions ----------------------------------------
			btnStart.setOnAction(e->{
				primaryStage.setScene(preGame);
				primaryStage.setTitle("Game");	
				animate6.play();
		
			});
			
			btnAbout.setOnAction(e->{
				primaryStage.setScene(about);
				primaryStage.setTitle("About");	
				animate1.play();
				animate2.play();
				animate3.play();
				animate4.play();
			});
			
			btnHelp.setOnAction(e->{
				primaryStage.setScene(help);
				primaryStage.setTitle("Help");	
				f1.play();
				animate5.play();
			});
			
			btnExit.setOnAction(e->{
				e.consume();
				logout(primaryStage);
			});
			
			preGame.setOnKeyPressed(e->{
				if(e.getCode()==KeyCode.ENTER) {
					animate7.play();
					primaryStage.setScene(game);
					primaryStage.setTitle("Game");
					mediaPlayer.pause();					
				}

				});
			
			game.setOnKeyPressed(event -> {
			    if (event.getCode() == KeyCode.LEFT) {
			        if(paddle.getLayoutX() <= 10 ||paddle2.getLayoutX() < 0) {
			             paddle.setLayoutX(paddle.getLayoutX()+movement);
			             paddle2.setLayoutX(paddle2.getLayoutX()+movement);
			        }
			        paddle.setLayoutX(paddle.getLayoutX()-movement);
			        paddle2.setLayoutX(paddle2.getLayoutX()-movement);
					timeline2.setCycleCount(Timeline.INDEFINITE);
					timeline2.play();
			    }

			    if (event.getCode() == KeyCode.RIGHT) {
			        if(paddle.getLayoutX() > 600 || paddle2.getLayoutX() > 600) {
			             paddle.setLayoutX(640);
			             paddle2.setLayoutX(640);
			        } 
			    	
			        paddle.setLayoutX(paddle.getLayoutX()+movement);
			        
			        paddle2.setLayoutX(paddle2.getLayoutX()+movement);
					timeline2.setCycleCount(Timeline.INDEFINITE);
					timeline2.play();
			    }
			 });
			
// ----------------------------------- Button Actions ----------------------------------------
			
// ----------------------------------- Style Sheets ----------------------------------------
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			preGame.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			game.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			postGame.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			about.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			help.getStylesheets().add(getClass().getResource("application.css").toExternalForm());	
// ----------------------------------- Style Sheets ----------------------------------------

			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Home");
			Image icon=new Image("logo.jpg");
			primaryStage.getIcons().add(icon);
			primaryStage.show();
			primaryStage.setResizable(false);	
			
			primaryStage.setOnCloseRequest(event -> {
				event.consume();
				logout(primaryStage);	
				
			});
		} catch(Exception e) {
			e.printStackTrace();
		}



	}
//	Exit Function
	public void logout(Stage stage){	
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("You're about to Exit!");
		alert.setContentText("Do you really want to exit");
		
		if (alert.showAndWait().get() == ButtonType.OK){
		
			stage.close();
		} 
		
	}
	
	public void createBricks() {
switch(level) {
case 1:{
	for(int x=0; x<4; x++) {
	    for(int y=0; y<6; y++) {
	        Rectangle brick=new Rectangle(85,20);
	    
	        if(x%2==0 && y%2==0){
	        	 brick.setFill(Color.RED);
	           
	        }
	        else if(y>1 && y<=6){
	            brick.setFill(Color.YELLOW);
	        }
	        else {
	        	 
	        	  brick.setFill(Color.LIMEGREEN);
	        }
	        
	        brick.setLayoutX((x*97)+200);
	        brick.setLayoutY((26*y)+90);
//	        secondScene.setPadding(new Insets(0,300,0,300));
	        secondScene.getChildren().addAll(brick);
	       
	        allBricks.add(brick);
	       

	    }
	}
};
	break;
	case 2:{

		for(int x=0; x<7; x++) {
		    for(int y=0; y<8; y++) {
		        Rectangle brick=new Rectangle(85,20);
		    
		        if(x%2==0 && y%2==0){
		        	 brick.setFill(Color.RED);
		           
		        }
		        else if(y>1 && y<=6){
		            brick.setFill(Color.YELLOW);
		        }
		        else {
		        	 
		        	  brick.setFill(Color.LIMEGREEN);
		        }
		        
		        brick.setLayoutX((x*97)+60);
		        brick.setLayoutY((26*y)+90);
		       
		        secondScene.getChildren().addAll(brick);
		        
		        allBricks.add(brick);
		       

		    }
		}
		
		
	};
	break;
	case 3:{

		for(int x=0; x<8; x++) {
		    for(int y=0; y<10; y++) {
		        Rectangle brick=new Rectangle(85,20);
		    
		        if(x%2==0 && y%2==0){
		        	 brick.setFill(Color.YELLOW);
		           
		        }
		       
		        
		        else {
		        	 
		        	  brick.setFill(Color.LIMEGREEN);
		        }
		        
		        brick.setLayoutX((x*97)+21);
		        brick.setLayoutY((26*y)+90);
		        secondScene.getChildren().addAll(brick);
		        
		        allBricks.add(brick);
		       

		    }
		}
		System.out.println("Level" + level);
		
		
	};
	break;

}


	}
	public void removeBricks() {
//      To remove all bricks from screen
      allBricks.forEach(brick -> secondScene.getChildren().remove(brick));
      allBricks.clear();
	}
	public void resetScore() {
		score=0;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
