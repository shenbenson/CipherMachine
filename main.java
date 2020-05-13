import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

public class CipherMachine {

    public static final String SEQ1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String SEQ2 = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
    public static Scanner inp = new Scanner(System.in);
    
    public static void main(String[] args) {       
        System.out.println("Please enter the key");
        String key = String.valueOf(inp.next().charAt(0)).toUpperCase();
        
        System.out.println("Do you want to encrypt or decrypt?");
        String option = String.valueOf(inp.next().charAt(0)).toUpperCase();
        
        inp.nextLine();
        
        switch (option) {
            case "E":
                encryption(SEQ1.indexOf(key));
                break;
            case "D":
                decryption(SEQ1.indexOf(key));
                break;
            default:
                System.out.println("That is not a valid option.");
                break;
        }
    }
    
    public static void encryption(int shift) {
        System.out.println("Please enter the message you want to encrypt");
        String message = inp.nextLine();
        String result = "";
        
        int SEQ1Index;
        int SEQ2Index;
        
        for (int n = 0; n < message.length(); n++) {
            SEQ1Index = SEQ1.indexOf(message.charAt(n));
            SEQ2Index = SEQ2.indexOf(message.charAt(n));
            if (SEQ1Index != -1) {
                result += SEQ1.charAt(SEQ1Index + shift);
            } else if (SEQ2Index != -1) {
                result += SEQ2.charAt(SEQ2Index + shift);
            } else {
                result += message.charAt(n);
            }
        }
        
        System.out.println("\n" + result + "\n(copied to clipboard)");
        copyToClipboard(result);
    }
    
    public static void decryption(int shift) {
        System.out.println("Please enter the message you want to decrypt");
        String message = inp.nextLine();
        String result = "";
        
        int SEQ1Index;
        int SEQ2Index;
        
        for (int n = 0; n < message.length(); n++) {
            SEQ1Index = SEQ1.lastIndexOf(message.charAt(n));
            SEQ2Index = SEQ2.lastIndexOf(message.charAt(n));
            if (SEQ1Index != -1) {
                result += SEQ1.charAt(SEQ1Index - shift);
            } else if (SEQ2Index != -1) {
                result += SEQ2.charAt(SEQ2Index - shift);
            } else {
                result += message.charAt(n);
            }
        }
        
        System.out.println("\n" + result + "\n(copied to clipboard)");
        copyToClipboard(result);
    }
    
    public static void copyToClipboard(String result) {
        StringSelection selection = new StringSelection(result);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}
