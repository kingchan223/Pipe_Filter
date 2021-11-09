package Components.myFilter;

import Framework.CommonFilterImpl;
import etc.Props;
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
        String[] inPipes = {Props.PIPE1};
        String[] outPipes = {Props.PIPE3, Props.PIPE4};
        specialLogic(inPipes, outPipes);
    }

    private void specialLogic(String[] inPipes, String[] outPipes) throws IOException {
        PipedInputStream pis = ins.get(inPipes[0]);
        PipedOutputStream pos1 = outs.get(outPipes[0]);
        PipedOutputStream pos2 = outs.get(outPipes[1]);

        int idx = 0;
        byte[] buffer = new byte[80];
        int byte_read = 0;
        while(true) {
            while(byte_read != '\n' && byte_read != -1) {
                byte_read = pis.read();
                if(byte_read != -1 && byte_read != 13 && byte_read != 10) buffer[idx++] = (byte) byte_read;
            }
            byte[] copyBuffer = new byte[idx];
            System.arraycopy(buffer, 0, copyBuffer, 0, idx);
//            for (byte b : buffer2) {
//                System.out.print((char)b);
//            }
            if(idx >0){
                if(validate.checkPreCourse(copyBuffer)){
                    for (byte b : copyBuffer) pos1.write((char) b);//수강 제대로된 친구들
                    pos1.write('\n');
                }
                else{
                    for (byte b : copyBuffer) pos2.write((char) b);//수강 이산한 친구들
                    pos2.write('\n');
                }
            }
            if (byte_read == -1){
                pos1.write(-1);
                pos2.write(-1);
                return;
            }
            idx = 0;
            byte_read = '\0';
        }
    }
}
