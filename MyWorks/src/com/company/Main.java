package com.company;
import java.util.*;
public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> computerPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);

        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your placement: ");
            int playerPos = scanner.nextInt();

            while (playerPositions.contains(playerPos)||computerPositions.contains(playerPos)){
                System.out.println("Position taken! Enter a new position ");
                playerPos = scanner.nextInt();
            }
            position(gameBoard,playerPos,"player");
            String result =  checkWin();
            if(result.length()>0){
                System.out.println(result);
                break;
            }
            Random random = new Random();
            int cpuPos = random.nextInt(9)+1;
            while (playerPositions.contains(cpuPos)||computerPositions.contains(cpuPos)){
                 cpuPos = random.nextInt(9)+1;
            }
            position(gameBoard,cpuPos,"cpu");

            printGameBoard(gameBoard);

            result =  checkWin();
           if(result.length()>0){
               System.out.println(result);
               break;
           }
        }
    }
    public static void printGameBoard(char [][] gameBoard){
        for(char [] row : gameBoard ){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static void position (char [][] gameBoard,int place , String user){
        char character = ' ';

        if(user.equals("player")){
            character='X';
            playerPositions.add(place);
        }else if (user.equals("cpu")){
            character= 'O';
            computerPositions.add(place);
        }
        switch (place){
            case 1:
                gameBoard[0][0]=character;
                break;
            case 2:
                gameBoard[0][2]=character;
                break;
            case 3:
                gameBoard[0][4]=character;
                break;
            case 4:
                gameBoard[2][0]=character;
                break;
            case 5:
                gameBoard[2][2]=character;
                break;
            case 6:
                gameBoard[2][4]=character;
                break;
            case 7:
                gameBoard[4][0]=character;
                break;
            case 8:
                gameBoard[4][2]=character;
                break;
            case 9:
                gameBoard[4][4]=character;
                break;
            default: break;
        }
    }
    public static String checkWin(){
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,5);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(7,5,3);

        List<List> winning  = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow );
        winning.add(botRow );
        winning.add(leftCol);
        winning.add(midCol );
        winning.add(rightCol);
        winning.add(cross1 );
        winning.add(cross2 );
        for(List l:winning){
            if(playerPositions.containsAll(l)){
                return "You won ";
            }else if(computerPositions.containsAll(l)){
                return "Computer won, sorry (";
            }else if(playerPositions.size() + computerPositions.size() == 9){
                return " Tie ";
            }
        }
        return "";
    }
}