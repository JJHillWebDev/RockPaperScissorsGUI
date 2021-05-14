import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import java.util.Random; 

/**
    This GUI application lets the user play a game of Rock, Paper,
    Scissors against the computer.

    @author Jeremy Hill
    @version 1.8.0_271
 */
public class RockPaperScissors extends Application 
{
	// Fields
	private int userNumber;
	private int computerNumber;
	private String userChoice;
	private Label directionsLabel;
	private Label computerChoiceLabel;
	private Label userChoiceLabel;

	/**
	* The main method calls the Application class launch
	* @param args the command line arguments
	*/
  	public static void main(String[] args) 
	{
		launch(args);
	}

	/**
	* The start method takes a Stage object as an argument.
	* It also generates a random number betwen 0-2 to represent the computer's
	* choice for rock, paper or scissors. It displays images of a rock, paper
	* and scissors for the user to click on for their choice. It then calls
	* the checkWinner method to see if the user or computer won the game. 
	* @param primaryStage Stage object to display scene
	*/
	@Override
	public void start(Stage primaryStage)
	{  
		// generate random number 0-2 for the computer's choice
		Random rand = new Random();
		computerNumber = rand.nextInt(3);

		// label holds the intructions and game's outcome
		directionsLabel = new Label("Choose Rock, Paper, or Scissors");

		// create image objects for rock, paper and scissors
		Image rock = new Image("file:rock.png");
		Image paper = new Image("file:paper.png");
		Image scissors = new Image("file:scissors.png");
		
		// create ImageView objects to display the images objects
		ImageView rockView = new ImageView(rock);
		rockView.setPreserveRatio(true);
		ImageView paperView = new ImageView(paper);
		paperView.setPreserveRatio(true);
		ImageView scissorsView = new ImageView(scissors);
		scissorsView.setPreserveRatio(true);
		
		// HBox for the three ImageView objects
		HBox choiceBox = new HBox(rockView, paperView, scissorsView);
		choiceBox.setSpacing(10);

		// VBox to center align the directions label and the ImageViews
		VBox centeringBox = new VBox(directionsLabel, choiceBox);
		centeringBox.setAlignment(Pos.CENTER);
		centeringBox.setSpacing(10);

		// labels to hold the computer and user's choices
		computerChoiceLabel = new Label("Computer's Choice: ");
		userChoiceLabel = new Label("Your Choice: ");

		// VBox to display everthing to the scene
		VBox rootBox = new VBox(centeringBox, computerChoiceLabel, 
							               userChoiceLabel);
		rootBox.setPadding(new Insets(10));
		rootBox.setSpacing(10);

		// setup and display the scene
		Scene root = new Scene(rootBox);
		primaryStage.setScene(root);
		primaryStage.show();

		// set events for user choosing rock, paper or scissors
		rockView.setOnMouseClicked (event -> 
		{
			userNumber = 0;
			userChoice = "Rock";
			checkWinner(computerNumber, userNumber);
		});

		paperView.setOnMouseClicked (event -> 
		{
			userNumber = 1;
			userChoice = "Paper";
			checkWinner(computerNumber, userNumber);
		});

		scissorsView.setOnMouseClicked (event -> 
		{
			userNumber = 2;
			userChoice = "Scissors";
			checkWinner(computerNumber, userNumber);
		});
	}

	/**
		This method checks if the user or computer won the game. Rock beats
		scissors, scissors beats paper and paper beats rock. It also checks
		if the choices were the same, which results in no winner.
	*/
	private void checkWinner(int comp, int user)
	{
		// check user and computer choices & update labels
		if (user == comp)
		{
			directionsLabel.setText(String.format("It's a tie."));
			computerChoiceLabel.setText(
					String.format("Computer's Choice:   " + userChoice));
			userChoiceLabel.setText(
					String.format("Your Choice:   " + userChoice));
		}
		else if (user == 0)
		{
			if (comp == 1)
			{
				directionsLabel.setText(String.format("You Lose."));
				computerChoiceLabel.setText(
						String.format("Computer's Choice:   Paper"));
				userChoiceLabel.setText(
						String.format("Your Choice:   " + userChoice));
			}
			else
			{
				directionsLabel.setText(String.format("You Win!"));
				computerChoiceLabel.setText(
						String.format("Computer's Choice:   Scissors"));
				userChoiceLabel.setText(
						String.format("Your Choice:   " + userChoice));
			}
		}
		else if (user == 1)
		{
			if (comp == 2)
			{
				directionsLabel.setText(String.format("You Lose."));
				computerChoiceLabel.setText(
						String.format("Computer's Choice:   Scissors"));
				userChoiceLabel.setText(
						String.format("Your Choice:   " + userChoice));
			}
			else
			{
				directionsLabel.setText(String.format("You Win!"));
				computerChoiceLabel.setText(
						String.format("Computer's Choice:   Rock"));
				userChoiceLabel.setText(
						String.format("Your Choice:   " + userChoice));
			}
		}
		else if (user == 2)
		{
			if (comp == 0)
			{
				directionsLabel.setText(String.format("You Lose."));
				computerChoiceLabel.setText(
						String.format("Computer's Choice:   Rock"));
				userChoiceLabel.setText(
						String.format("Your Choice:   " + userChoice));
			}
			else
			{
				directionsLabel.setText(String.format("You Win!"));
				computerChoiceLabel.setText(
						String.format("Computer's Choice:   Paper"));
				userChoiceLabel.setText(
						String.format("Your Choice:   " + userChoice));
			}
		}
	}
}