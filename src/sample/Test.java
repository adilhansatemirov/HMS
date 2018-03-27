package sample;

import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String text = "12/21";
        String month = text.substring(0,2);
        String year = text.substring(3);

        if((!Pattern.matches("[0-9]+", month)) ||
           (!Pattern.matches("[0-9]+", year))   ||
           !(Integer.parseInt(month)>=1 && Integer.parseInt(month)<=12) ||
            (Integer.parseInt(year)<=18) || (text.length()!=5) || text.charAt(2)!='/'){
            System.out.println("format: MM/YY");
        }else {
            System.out.println("good");
        }
    }
}
