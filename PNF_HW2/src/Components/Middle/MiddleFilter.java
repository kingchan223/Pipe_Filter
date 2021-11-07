/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Middle;

import java.io.IOException;
import Framework.CommonFilterImpl;

public class MiddleFilter extends CommonFilterImpl{
    @Override
    public void specificComputationForFilter() throws IOException {
        int checkBlank = 4;
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[64];
        boolean isEE = false;
        int byte_read = 0;

        while(true) {
            while(byte_read != '\n' && byte_read != -1) {
                byte_read = in.read();
                if(byte_read == ' ') numOfBlank++;
                if(byte_read != -1) buffer[idx++] = (byte)byte_read;
                if(numOfBlank == checkBlank && buffer[idx-3] == 'E' && buffer[idx-2] == 'E')
                    isEE = true;
            }
            if(isEE) {
                for(int i = 0; i<idx; i++)
                    out.write((char)buffer[i]);
                isEE = false;
            }
            if (byte_read == -1) return;
            idx = 0;
            numOfBlank = 0;
            byte_read = '\0';
        }
    }
}
