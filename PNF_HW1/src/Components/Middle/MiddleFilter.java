/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Middle;

import java.io.IOException;
import Framework.CommonFilterImpl;

public class MiddleFilter extends CommonFilterImpl{
    @Override
    public boolean specificComputationForFilter() throws IOException {
        int checkCSBlank = 4;
        int checkCourseBlank = 5;
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[120];
        boolean isCS = false;
        boolean is12345 = false;
        boolean is23456 = false;
        int byte_read = 0;
        while(true) {
            // check "CS" on byte_read from student information
            while(byte_read != '\n' && byte_read != -1) {
                byte_read = in.read();
                if(byte_read == ' ') numOfBlank++;
                if(byte_read != -1 && byte_read != 13 && byte_read != 10) buffer[idx++] = (byte)byte_read;
                if(numOfBlank == checkCSBlank && buffer[idx-3] == 'C' && buffer[idx-2] == 'S')
                    isCS = true;
                if(numOfBlank >= checkCourseBlank
                        && buffer[idx-6] == '1'
                        && buffer[idx-5] == '2'
                        && buffer[idx-4] == '3'
                        && buffer[idx-3] == '4'
                        && buffer[idx-2] == '5')
                    is12345 = true;
                if(numOfBlank >= checkCourseBlank
                        && buffer[idx-6] == '2'
                        && buffer[idx-5] == '3'
                        && buffer[idx-4] == '4'
                        && buffer[idx-3] == '5'
                        && buffer[idx-2] == '6')
                    is23456 = true;
            }

            if(isCS && !is12345){
                buffer[idx++] = ' ';
                int c = 49;
                for (int i = 0; i < 5; i++)
                    buffer[idx++] = (byte) c++;
            }
            if(isCS && !is23456){
                buffer[idx++] = ' ';
                int c = 50;
                for (int i = 0; i < 5; i++)
                    buffer[idx++] = (byte) c++;
            }

            buffer[idx++] = '\n';
            for(int i = 0; i<idx; i++)
                out.write((char) buffer[i]);

            isCS = false;
            is12345 = false;
            is23456 = false;
            if (byte_read == -1) return true;
            idx = 0;
            numOfBlank = 0;
            byte_read = '\0';
        }
    }
}
