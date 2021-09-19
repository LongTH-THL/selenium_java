package javaTest;

import org.testng.Assert;

public class Topic_03_String_Trim {
    public static void main(String[] args){
        String text = "\n" +
                "                First Option\n" +
                "            ";
        Assert.assertEquals(text.trim(), "First Option");
    }
}
