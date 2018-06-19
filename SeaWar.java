import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SeaWar{
    
    public static void server(int portNumber, boolean ai)
    {
        System.out.println("Waiting for other player...");
        try (  
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
        ) {
            System.out.println("Connected!");
            Scanner s;
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String inputDamage;
            String outputDamage;
            int x,y;
            Board board1=new Board();
            Board board2=new Board();
            board1.placeShips();
            board2.placeShips();
            String s1, s2;
            s1=board1.toString();
            s2=board2.toString();
            out.println(s1);
            out.println(s2);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            board2.show(true);
            System.out.println("----------------------");
            board1.show(false);
            System.out.println("Fire!!!");
            System.out.print("Coordinates:");

            //-------------------------------------------------------------------------------------//


            if(!ai)
            {
                outputDamage=stdIn.readLine();
                s = new Scanner(outputDamage).useDelimiter("\\s");  
                x=s.next().charAt(0)-64;
                y=s.nextInt();
                s.close();
                board2.damage(x,y);
            }
            else{
                do{
                    x=(int)(Math.random()*10)+1;
                    y=(int)(Math.random()*10)+1;
                    char c=(char)(x+64);
                    outputDamage=c+" "+y;
                }while(!board2.damage(x, y));
            }
            
            
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            board2.show(true);
            System.out.println("----------------------");
            board1.show(false);
            out.println(outputDamage);
            System.out.print("Foe's turn");


            while ((inputDamage = in.readLine()) != null) {
                if(inputDamage.equals("iamlooser")){
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    board2.show(true);
                    System.out.println("----------------------");
                    board1.show(false);
                    System.out.println("You win!");
                    break;
                }
                s = new Scanner(inputDamage).useDelimiter("\\s");  
                x=s.next().charAt(0)-64;
                y=s.nextInt();
                s.close();
                board1.damage(x,y);

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                board2.show(true);
                System.out.println("----------------------");
                board1.show(false);
                
                if(board1.loseCondition()){
                    System.out.println("You loose...");
                    out.println("iamlooser");
                    break;
                }

                System.out.println("Fire!!!");
                System.out.print("Coordinates:");
                
                
                if(!ai){
                    outputDamage=stdIn.readLine();
                    s = new Scanner(outputDamage).useDelimiter("\\s");  
                    x=s.next().charAt(0)-64;
                    y=s.nextInt();
                    s.close();
                    board2.damage(x, y);
                }
                else{
                    do{
                        x=(int)(Math.random()*10)+1;
                        y=(int)(Math.random()*10)+1;
                        char c=(char)(x+64);
                        outputDamage=c+" "+y;
                    }while(!board2.damage(x, y));
                }
                
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                
                board2.show(true);
                System.out.println("----------------------");
                board1.show(false);


                out.println(outputDamage);

                System.out.print("Foe's turn");
            }

        }catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public static void client(String hostName, int portNumber, boolean ai)
    {
        try (
            Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            int x,y;
            Scanner s;
            String inputDamage;
            String outputDamage;
            Board board1=new Board();
            Board board2=new Board();
            board1.setMatrix(in.readLine());
            board2.setMatrix(in.readLine());
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            board1.show(true);
            System.out.println("----------------------");
            board2.show(false);
            System.out.println("Foe's turn");

            //-------------------------------------------------------------------------------//

            while((inputDamage = in.readLine()) != null)
            {
                if(inputDamage.equals("iamlooser")){
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    board1.show(true);
                    System.out.println("----------------------");
                    board2.show(false);
                    System.out.println("You win!");
                    break;
                }
                s = new Scanner(inputDamage).useDelimiter("\\s");  
                x=s.next().charAt(0)-64;
                y=s.nextInt();
                s.close();
                board2.damage(x, y);


                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                board1.show(true);
                System.out.println("----------------------");
                board2.show(false);

                if(board2.loseCondition()){
                    System.out.println("You loose...");
                    out.println("iamlooser");
                    break;
                }
                
                System.out.println("Fire!!!");
                System.out.print("Coordinates:");
                if(!ai)
                {
                    outputDamage=stdIn.readLine();
                    s = new Scanner(outputDamage).useDelimiter("\\s");  
                    x=s.next().charAt(0)-64;
                    y=s.nextInt();
                    s.close();
                    board1.damage(x, y);
                }
                else{
                    do{
                        x=(int)(Math.random()*10)+1;
                        y=(int)(Math.random()*10)+1;
                        char c=(char)(x+64);
                        outputDamage=c+" "+y;
                    }while(!board1.damage(x, y));
                }

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                board1.show(true);
                System.out.println("----------------------");
                board2.show(false);

                out.println(outputDamage);
                System.out.print("Foe's turn");
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) { 
        switch (args[0]) {
            case "client":
                SeaWar.client(args[1], Integer.parseInt(args[2]), Boolean.parseBoolean(args[3]));
                break;
            case "server":
                SeaWar.server(Integer.parseInt(args[1]), Boolean.parseBoolean(args[2]));
                break;
            default:
                break;
        }
      


           
        
    }
}

