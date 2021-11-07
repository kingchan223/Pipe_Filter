/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Sink;

import Framework.CommonFilterImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedInputStream;

public class SinkFilter extends CommonFilterImpl {
    private String sinkFile;
    private String sinkFile2;
    
    public SinkFilter(String outputFile1, String outputFile2) {
        this.sinkFile = outputFile1;
        this.sinkFile2 = outputFile2;
    }
    @Override
    public void specificComputationForFilter() throws IOException {
        int byte_read;
        int byte_read2;
        FileWriter fw = new FileWriter(this.sinkFile);
        FileWriter fw2 = new FileWriter(this.sinkFile2);
        while(true) {
            byte_read = in.get(0).read();
            byte_read2 = in.get(1).read();
            if(byte_read == -1 && byte_read2 == -1){
                fw.close();
                fw2.close();
                System.out.println( "::Filtering is finished; Output file is created." );
                return;
            }
            fw.write((char)byte_read);
            fw2.write((char)byte_read2);
        }
    }
}
