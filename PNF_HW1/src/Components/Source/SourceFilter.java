/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Source;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Framework.CommonFilterImpl;

public class SourceFilter extends CommonFilterImpl{
    private String sourceFile;
    
    public SourceFilter(String inputFile){
        this.sourceFile = inputFile;
    }    
    @Override
    public void specificComputationForFilter() throws IOException {
        int byte_read;    
        BufferedInputStream br = new BufferedInputStream(new FileInputStream((sourceFile)));
        while(true) {
            byte_read = br.read();
            if (byte_read == -1) return;
            out.write(byte_read);
        }
    }
}
