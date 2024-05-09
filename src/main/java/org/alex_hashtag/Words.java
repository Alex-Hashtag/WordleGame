package org.alex_hashtag;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;


public class Words
{
    static final Random random = new Random();
    static Properties properties;

    static void init() throws IOException
    {
        properties = new Properties();
        properties.load(new FileInputStream("word.properties"));
    }

    public static String selectTargetWord()
    {
        int select = random.nextInt(9079);
        return properties.getProperty(String.valueOf(select));
    }
}
