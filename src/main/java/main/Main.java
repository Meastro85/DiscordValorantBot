package main;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        try{
            ValorantBot bot = new ValorantBot();
        } catch(InvalidTokenException e){
            System.out.println("ERROR: Provided bot token is invalid!");
        }
    }

}
