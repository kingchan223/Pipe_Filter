/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Middle;

import Framework.CommonFilterImpl;
import domain.PreCourse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MiddleFilter extends CommonFilterImpl {

    @Override
    public void specificComputationForFilter() throws IOException {
        int idx = 0;
        int idx2 = 0;
        byte[] buffer = new byte[64];
        byte[] buffer2 = new byte[64];
        int byte_read = 0;
        int byte_read2 = 0;

        while(true) {
            while(byte_read2 != '\n' && byte_read2 != -1) {
                byte_read2 = in.get(1).read();
                if(byte_read2 != -1 && byte_read2 != 13 && byte_read2 != 10) buffer2[idx2++] = (byte)byte_read2;
            }
            byte[] buffer3 = new byte[idx2];
            System.arraycopy(buffer2, 0, buffer3, 0, idx2);
            PreCourse.makePreCourse(buffer3);
            if (byte_read2 == -1) break;
            idx2 = 0;
            byte_read2 = '\0';
        }

        while(true) {
            while(byte_read != '\n' && byte_read != -1) {
                byte_read = in.get(0).read();
                if(byte_read != -1) buffer[idx++] = (byte)byte_read;
            }
            for(int i = 0; i<idx; i++){
                out.get(0).write((char)buffer[i]);
            }
            if (byte_read == -1) return;
            idx = 0;
            byte_read = '\0';
        }
    }
}