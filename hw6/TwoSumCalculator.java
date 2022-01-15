package hw6;

import java.io.*;
import java.util.*;
import java.math.BigInteger;


class TwoSumCalculator 
{
    String fileName;
    ArrayList<BigInteger> numbers = new ArrayList<BigInteger>();

    public TwoSumCalculator(String fileName) {
        this.fileName = fileName;
    }

    public void populateArray()
    {
        try
        {   
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null)
            {   
                BigInteger bigInteger = new BigInteger(line);
                numbers.add(bigInteger);
                // System.out.println(line);
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println(numbers.get(5));
        System.out.println(numbers.get(999999));
    }

}