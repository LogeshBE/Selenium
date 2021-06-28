
package csvtohashmap;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CSVtoHashmap {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        CSVReader reader=new CSVReader(new FileReader("D:\\WPS Office\\CSVtoHashmap.csv"));
        HashMap<String,String> map =new HashMap<>();
        String[] data;
        data=reader.readNext();
        for(String key : data){
            map.put(key, null);
        }
        System.out.println("m= "+map);
        String value[]=reader.readNext();
        for(int i=0 ; i< value.length;i++){
            String key = data[i];
            String val = value[i];
            System.out.println(" i = "+i+" -> "+val );
            map.put(key, value[i]);
        }
        
        System.out.println("map = "+map);
        
    }
    
}
