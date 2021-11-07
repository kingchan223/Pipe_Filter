/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Source;

import Framework.CommonFilterImpl;

import java.io.*;

public class SourceFilter extends CommonFilterImpl {
    private final String sourceFile;
    private final String sourceFile2;
    
    public SourceFilter(String inputFile,String inputFile2){
        super();
        this.sourceFile = inputFile;
        this.sourceFile2 = inputFile2;
    }

    @Override
    public void specificComputationForFilter() throws IOException {
        int byte_read;
        int byte_read2;
        BufferedInputStream br = new BufferedInputStream(new FileInputStream((sourceFile)));
        BufferedInputStream br2 = new BufferedInputStream(new FileInputStream((sourceFile2)));
        while(true) {
            byte_read = br.read();
            if (byte_read == -1) break;
            out.get(0).write(byte_read);
        }
        while(true) {
            byte_read2 = br2.read();
            if (byte_read2 == -1) break;
            out.get(1).write(byte_read2);
        }
        if(byte_read==-1 && byte_read2==-1) return;
    }
}
