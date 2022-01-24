import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Puzzle
{
    static String myWord;
    static ArrayList<String> words;
    static boolean solved;
    static int numTries;
    
    public Puzzle(){   
    }
    
    public static void loadWords(String filename){
        File wordfile = new File(filename);
        try{
            Scanner fileScanner = new Scanner(wordfile);
            while (fileScanner.hasNext()){
                String w = fileScanner.nextLine();
                if (w.length() == 5 && !Character.isUpperCase(w.charAt(0))){
                    words.add(w);
                }
            }
        } catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
    
    public static void checkGuess(String str){
        boolean added = false;
        
        if(str.equals(myWord))
        solved = true;
        
        else if(words.indexOf(str)==-1)
        System.out.println("Invalid word");
        
        else{
            String result = "";
            for(int gInd=0; gInd<5; gInd++){
                added = false; 
                for(int wInd=0; wInd<5; wInd++){
                    if(str.substring(gInd,gInd+1).equals(myWord.substring(wInd,wInd+1)) && gInd==wInd){
                        result = result + " " + str.substring(gInd,gInd+1);
                        added = true;
                    }
                    else if(str.substring(gInd,gInd+1).equals(myWord.substring(wInd,wInd+1))){
                        result = result + "?" + str.substring(gInd,gInd+1);
                        added = true;
                    }
                }
                if(added==false)
                result = result + "!" + str.substring(gInd,gInd+1);
            }
            numTries++;
            System.out.println(result);
            System.out.println((6-numTries) + " chances left");
        }
    }
    
    public static void main(String[] args){
        String myGuess;
        
        words = new ArrayList<String>(3300);
        loadWords("words.txt");
        int r = (int)(Math.random()*words.size());
        myWord = words.get(r);
        solved = false;
        numTries = 0;
        
        System.out.println("Guess the word");
        System.out.println("_ _ _ _ _");
        System.out.println("! before a letter means wrong letter");
        System.out.println("? before a letter means correct letter in the wrong place");
        System.out.println("Space before a letter means correct letter in the correct place");
        
        while(numTries<6){
            if(solved == true){
                System.out.println("Congratulations! The word is " + myWord);
                break;
            }
            Scanner takeGuess = new Scanner(System.in);
            myGuess = takeGuess.nextLine();
            checkGuess(myGuess);
        }
        
        if(numTries==6)
        System.out.println("Times up! The word is " + myWord);
    }
}
