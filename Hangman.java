package hw_01;
import java.io.*;
import java.util.Scanner;
import java.util.Random;
public class Hangman {
	private final static String FILE_NAME = "dictionary.txt";
	Random rand = new Random();
	 int numGuesses, incorrectGuesses;
		String[] dictionary;	
boolean guessStatus = false;
		 String secretWord = "";	
		 String disguisedWord = "";
			char[] disguise;
	public Hangman()
	{
		setNumGuesses(0); setIncorrectGuesses(0);
			dictionary = loadDictionary(FILE_NAME);
			//secretWord = dictionary[rand.nextInt(dictionary.length)];
			secretWord = "true";
			//Creates a string of ?'s that is as long as the secret word
			for(int i = 0; i < secretWord.length();i++)
				disguisedWord += "?";
	disguise = new char[disguisedWord.length()];
	}
	//read the dictionary file and put the words into an array
	public String[] loadDictionary(String aFileName)
	{
		try
		{
			//Creates a scanner for the file and then first counts each word
			Scanner fileScanner = new Scanner(new File(aFileName));
			int wordCount = 0;
			while(fileScanner.hasNextLine())
			{
				fileScanner.nextLine();
				wordCount++;
			}
			if(wordCount <= 0)
			{
				fileScanner.close();
				return null;
			}
			//Creates the return array, resets the file scanner, and populates the array
			String[] retArr = new String[wordCount];
			fileScanner = new Scanner(new File(aFileName));
			for(int i=0;i<retArr.length;i++)
			{
				if(!fileScanner.hasNextLine())
					break;
				retArr[i] = fileScanner.nextLine();
			}
			fileScanner.close();
			return retArr;
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
	
	//Resets all of the variables to default
	public void resetGame()
	{
		disguisedWord = "";
		secretWord = dictionary[rand.nextInt(dictionary.length )];
		
		setNumGuesses(0); setIncorrectGuesses(0);
		for(int i = 0; i < secretWord.length();i++)
			disguisedWord += "?";
		disguise = new char[disguisedWord.length()];
	}
	public boolean makeGuess(char guess)
	{ 
		//Increase the number of guesses and set the return value to false by default
		numGuesses++;
	guessStatus = false;
	//Iterate through the secretword's letters to see if any match the guess
		 for(int i = 0; i <secretWord.length();i++)
		 {
			 //Check to see if the guess equals any letter in the word
			 if(guess == secretWord.charAt(i))
			 {
				 //Set the disguised word equal to the word with the characters in it and set the guessaStatus to true 
				 disguisedWord = insertCharacters(i,guess);
				 guessStatus = true;
			 }
			
		 }
		
		 return guessStatus;
	}
	
	public String insertCharacters(int aValue, char aLetter)
	{
		//Set counter and string that will be returned back to default values
		int count = 0;
		String updatedDisguise = "";
		//Iterate through the diguised word and wherever the counter equals the value where the matching char is the letter will be put in there but for the others the ?'s remain
		for(int i = 0; i<disguisedWord.length();i++ )
		{
			if(i == aValue)
				disguise[i] = aLetter; 
			else
				disguise[i] = disguisedWord.charAt(i);
		}
		while(count < disguisedWord.length())
		{
			//Move the chars from the array into the string that is being returned
			updatedDisguise += disguise[count];
			count++;
		}
		return updatedDisguise;
			
	}
	//Getters and setters
	public int getNumGuesses() {
		return numGuesses;
	}
	public void setNumGuesses(int numGuesses) {
		this.numGuesses = numGuesses;
	}
	public int getIncorrectGuesses() {
		return incorrectGuesses;
	}
	public void setIncorrectGuesses(int incorrectGuesses) {
		this.incorrectGuesses = incorrectGuesses;
	}
	public String getSecretWord() {
		return secretWord;
	}
	public void setSecretWord(String secretWord) {
		this.secretWord = secretWord;
	}
	public String getDisguisedWord() {
		return disguisedWord;
	}
	public void setDisguisedWord(String disguisedWord) {
		this.disguisedWord = disguisedWord;
	}
	public boolean isFound()
	{
		//Checks to see if the disguised word has been fully uncovered
		if(disguisedWord.equalsIgnoreCase(secretWord))
			return true;
		else 
			return false;
	}
}
