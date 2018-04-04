package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.net.*;

public class DocReader extends Menu{
	
	public void URLReader(String url) throws IOException{
	    
	        URL urlreader = new URL(url);
	        BufferedReader in = new BufferedReader(new InputStreamReader(urlreader.openStream()));
	        String str;
	        while ((str = in.readLine()) != null) {
	            str = in.readLine().toString();
	            System.out.println(str);
	            // str is one line of text; readLine() strips the newline character(s)
	        }
	        in.close();
	       /* BufferedReader in = new BufferedReader(
	        new InputStreamReader(urlreader.openStream()));
	        System.out.println("Reading URL one moment.....");
	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	            System.out.println(inputLine);
	        in.close();
	        */
	}
	
	
	public String ReadFile(File selectedFile)
	{
	    StringBuilder contentBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader(selectedFile)))
	    {
	 
	        String sCurrentLine;
	        while ((sCurrentLine = br.readLine()) != null)
	        {
	            contentBuilder.append(sCurrentLine).append("\n");
	        }
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	    return contentBuilder.toString();
	}

	public DocReader() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
}
