/*
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Middle;

import Framework.CommonFilterImpl;
import domain.PreCourse;
import etc.Props;

import java.io.IOException;
import java.io.PipedInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MiddleFilter extends CommonFilterImpl {

    @Override
    public void specificComputationForFilter() throws IOException {
        specialLogic(Props.PIPE2);
        SendToNextFilter(Props.PIPE1);
    }

    private void specialLogic(String inPipe) throws IOException {
        int idx = 0;
        byte[] buffer = new byte[64];
        int byte_read = 0;
        PipedInputStream pipedInputStream = ins.get(inPipe);
        while(true) {
            while(byte_read != '\n' && byte_read != -1) {
                byte_read = pipedInputStream.read();
                if(byte_read != -1 && byte_read != 13 && byte_read != 10) buffer[idx++] = (byte) byte_read;
            }
            byte[] copyBuffer = new byte[idx];
            System.arraycopy(buffer, 0, copyBuffer, 0, idx);
            PreCourse.makePreCourse(copyBuffer);
            if (byte_read == -1) break;
            idx = 0;
            byte_read = '\0';
        }
    }

    private void SendToNextFilter(String outPipe) throws IOException {
        int idx = 0;
        byte[] buffer = new byte[64];
        int byte_read = 0;
        PipedInputStream pipedInputStream = ins.get(outPipe);
        while(true) {
            while(byte_read != '\n' && byte_read != -1) {
                byte_read = pipedInputStream.read();
                if(byte_read != -1) buffer[idx++] = (byte) byte_read;
            }
            for(int i = 0; i< idx; i++){
                outs.get(Props.PIPE1).write((char) buffer[i]);
            }
            if (byte_read == -1){
                outs.get(Props.PIPE1).write(-1);
                break;
            }
            idx = 0;
            byte_read = '\0';
        }
    }
}


