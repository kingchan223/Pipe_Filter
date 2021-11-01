/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Middle;

import java.io.IOException;
import Framework.CommonFilterImpl;

public class MiddleFilter extends CommonFilterImpl{
    @Override
    public boolean specificComputationForFilter() throws IOException {
        int checkEEBlank = 4;
        int checkCourseBlank = 5;
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[120];
        boolean isEE = false;
        boolean is23456 = false;
        int byte_read = 0;
        while(true) {
            // check "CS" on byte_read from student information
            while(byte_read != '\n' && byte_read != -1) {
                byte_read = in.read();
                if(byte_read == ' ') numOfBlank++;
                if(byte_read != -1 && byte_read != 13 && byte_read != 10) buffer[idx++] = (byte)byte_read;
                if(numOfBlank == checkEEBlank && buffer[idx-3] == 'E' && buffer[idx-2] == 'E')
                    isEE = true;

                if(numOfBlank >= checkCourseBlank &&
                        buffer[idx-6] == '2' &&
                        buffer[idx-5] == '3'&&
                        buffer[idx-4] == '4'&&
                        buffer[idx-3] == '5'&&
                        buffer[idx-2] == '6')
                    is23456 = true;
            }

            if(isEE && !is23456){
                buffer[idx++] = ' ';
                int c = 50;
                for (int i = 0; i < 5; i++)
                    buffer[idx++] = (byte)c++;
            }

            buffer[idx++] = '\n';
            for(int i = 0; i<idx; i++) {
                out.write((char) buffer[i]);
            }
            isEE = false;
            is23456 = false;
            if (byte_read == -1) return true;
            idx = 0;
            numOfBlank = 0;
            byte_read = '\0';
        }
    }
}
