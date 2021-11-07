package Components.myFilter;

import Framework.CommonFilterImpl;
import utils.DefaultUtil;
import utils.ExceptCSutil;
import utils.Validate;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;

public class MyFilter extends CommonFilterImpl {

    Validate validate = new Validate();

    @Override
    public void specificComputationForFilter() throws IOException {
        int idx = 0;
        byte[] buffer = new byte[80];
        int byte_read = 0;
        while(true) {
            while(byte_read != '\n' && byte_read != -1) {
                byte_read = in.get(0).read();
                if(byte_read != -1 && byte_read != 13 && byte_read != 10) buffer[idx++] = (byte)byte_read;
            }
            byte[] buffer2 = new byte[idx];
            System.arraycopy(buffer, 0, buffer2, 0, idx);
            if(idx>0){
                if(validate.checkPreCourse(buffer2)){
                    for (byte b : buffer2) out.get(0).write((char) b);//수강 제대로된 친구들
                    out.get(0).write('\n');
                }
                else{
                    for (byte b : buffer2) out.get(1).write((char) b);//수강 이산한 친구들
                    out.get(1).write('\n');
                }
            }
            if (byte_read == -1) return;
            idx = 0;
            byte_read = '\0';
        }
    }
}
