/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedInputStream;

public abstract class CommonSinkFilterImpl extends CommonFilterImpl {

    private String outputFile;
    private String inPipe;

    public CommonSinkFilterImpl(String inPipe, String outputFile) {
        this.inPipe = inPipe;
        this.outputFile = outputFile;
    }

    public void writeFile(){
        int byte_read;
        PipedInputStream pis = ins.get(inPipe);
        FileWriter fw = null;
        try {
            fw = new FileWriter(outputFile);
            while(true) {
                byte_read = pis.read();
                if (byte_read == -1) {
                    fw.close();
                    System.out.println("::Filtering is finished; Output file is created.");
                    break;
                }
                else fw.write((char)byte_read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
