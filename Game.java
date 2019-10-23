package hw_01;
//Made by Riley Conant, CSCE 146 Section-007, 8/7/2018
import java.util.Scanner;
public class Game {

	public static void main(String[] args) {
		//Set up the class object and the scanner 
		Hangman hMan = new Hangman();
		Scanner input = new Scanner(System.in);
		String userGuess = "";
		boolean gameOver = true;
		boolean correct = false;
		int incorrectGuess = 0;
		//Create the board
		String[][] board = new String[5][6];
		//Populate the board so that the gallows is created
		for(int x = 0; x < board.length;x++)
			for(int y = 0; y < board[0].length;y++)
			{
				if(x == 0)
					board[0][y] = "|";
				else if(x == 3 && y == 0)
					board[x][y] = "|";
				else 
					board[x][y] = " ";
			}
		System.out.println("Welcome to Hangman!");
		while(gameOver ==true)
		{
		
			while(incorrectGuess <= 7 && correct == false)
		{
			System.out.println("You have made " + hMan.getNumGuesses() + " guesses");
			System.out.println(hMan.getDisguisedWord());
			//print out the gallows
			System.out.println("______");
		for(int y = 0; y < board[0].length; y++ )
			for(int x = 0; x<board.length; x++)
			{
				if(x ==4)
				System.out.println(board[x][y]);
				else 
					System.out.print(board[x][y]);
			}
		System.out.println("Enter a letter");
		userGuess = input.nextLine();
		//Add to the gallows as incorrect answers are made
		if(hMan.makeGuess(userGuess.charAt(0)) == false)
		{
			//Increase incorrect counter
			incorrectGuess++;
			hMan.setIncorrectGuesses(incorrectGuess);
			//Add to the board for the certain amount wrong
			if(hMan.getIncorrectGuesses() ==1)
				board[3][1] = "o";
			if(hMan.getIncorrectGuesses() ==2)
				board[3][2] = "|";
			if(hMan.getIncorrectGuesses() ==3)
				board[3][3] = "|";
			if(hMan.getIncorrectGuesses() ==4)
				board[2][2] = "\\";
			if(hMan.getIncorrectGuesses() ==5)
				board[4][2] = "/";
			if(hMan.getIncorrectGuesses() ==6)
				board[2][4] = "/";
			if(hMan.getIncorrectGuesses() ==7)
				board[4][4] = "\\";
			incorrectGuess = hMan.getIncorrectGuesses();
		}
		if(hMan.isFound() == true)
			correct = true;
		else
			correct = false;
		if(correct == true)
		{
			System.out.println("Congrats you found out the word! " + hMan.getSecretWord());
			System.out.println("Would you like to play again yes/no?");
			String playerChoice = input.nextLine();
			if(playerChoice.equalsIgnoreCase("yes"))
			{
				hMan.resetGame();
			//repopulate the board
			for(int x = 0; x < board.length;x++)
				for(int y = 0; y < board[0].length;y++)
				{
					if(x == 0)
						board[0][y] = "|";
					else if(x == 3 && y == 0)
						board[x][y] = "|";
					else 
						board[x][y] = " ";
				}
			incorrectGuess = 0;
			}
			else if(playerChoice.equalsIgnoreCase("no"))
				gameOver = false;
		
	}
			
			
			}
		System.out.println("GAME OVER!");
		System.out.println("The word was " + hMan.getSecretWord());
		System.out.println("Would you like to play again yes/no?");
		String playerChoice = input.nextLine();
		if(playerChoice.equalsIgnoreCase("yes"))
		{
			hMan.resetGame();
			//repopulate the board
			for(int x = 0; x < board.length;x++)
				for(int y = 0; y < board[0].length;y++)
				{
					if(x == 0)
						board[0][y] = "|";
					else if(x == 3 && y == 0)
						board[x][y] = "|";
					else 
						board[x][y] = " ";
				}
			incorrectGuess = 0;
			
		}
		else if(playerChoice.equalsIgnoreCase("no"))
			gameOver = false;
		}
	}

}
