import java.net.*;
import java.io.*;
import java.io.PrintWriter;


public class WebpageDownloader{

    private static String url;

    public static void main(String[] args){
        System.out.println("Enter");
        System.out.println(args[0]);
        url = args[0];
        try {
            URL myURL = new URL(url);
            URLConnection myURLConnection = myURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                                        myURLConnection.getInputStream()));
            String inputLine;
            String holder = "";
            while ((inputLine = in.readLine()) != null){
                holder = holder + inputLine;
            }
            System.out.println(holder);
            in.close();
            PrintWriter writer = new PrintWriter("file.html"); 
            writer.println(holder);
            writer.close();
            } 
        catch (MalformedURLException e) { 
            System.out.println("new URL() failed");
            // new URL() failed
            // ...
        } 
        catch (IOException e) {   
            System.out.println("openConnection() failed");
            // openConnection() failed
            // ...
        }
        System.out.println("Exit");

                
    }

}