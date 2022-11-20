/**
 * 
 */
package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author cristoferSilva
 *
 */
public class InputFileReader {

	 public static String returnStringFromFile(String pathSourceCode) throws FileNotFoundException {
	        String contenString = "";
	        FileReader fileReader =  new FileReader(pathSourceCode);
	        Scanner scanner = new Scanner(fileReader).useDelimiter("\\n");

	        while (scanner.hasNextLine()) {
	            contenString += scanner.nextLine() + " ";
	        }
	        return contenString;
	    }
}
