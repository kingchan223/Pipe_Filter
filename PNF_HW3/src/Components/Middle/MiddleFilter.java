/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Middle;

import Framework.CommonFilterImpl;
import utils.Validator;

import java.io.IOException;

public class MiddleFilter extends CommonFilterImpl{

    Validator validator = new Validator();

    @Override
    public void specificComputationForFilter() throws IOException {

        int idx = 0;
        byte[] buffer = new byte[64];
        int byte_read = 0;

        while(true) {
            while(byte_read != '\n' && byte_read != -1) {
                byte_read = in.read();
                if(byte_read != -1 && byte_read != 10 && byte_read != 13) buffer[idx++] = (byte)byte_read;
            }
            String str = new String(buffer);
            if(!validator.isCS(str) && validator.isGrade("13",str )){
                for(int i = 0; i<idx; i++) out.write((char)buffer[i]);
            }
            if (byte_read == -1) return;
            idx = 0;
            byte_read = '\0';
        }
    }
}
