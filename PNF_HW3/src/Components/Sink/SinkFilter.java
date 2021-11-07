/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Sink;

import Framework.CommonFilterImpl;

import java.io.FileWriter;
import java.io.IOException;

public class SinkFilter extends CommonFilterImpl{
    private String sinkFile;
    
    public SinkFilter(String outputFile) {
        this.sinkFile = outputFile;
    }
    @Override
    public void specificComputationForFilter() throws IOException {
        int byte_read;
        FileWriter fw = new FileWriter(this.sinkFile);
        while(true) {
            byte_read = in.read();
            System.out.print((char)byte_read);
            if (byte_read == -1) {
            	 fw.close();
                 System.out.print( "::Filtering is finished; Output file is created." );  
                 return;
            }
            fw.write((char)byte_read);
        }   
    }
}
