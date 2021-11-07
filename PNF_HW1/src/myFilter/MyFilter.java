package myFilter;

import Framework.CommonFilterImpl;
import utils.CSutil;
import utils.DefaultUtil;

import java.io.IOException;
import java.util.List;

public class MyFilter extends CommonFilterImpl {

    DefaultUtil utils = new CSutil();

    @Override
    public void specificComputationForFilter() throws IOException {
        List<String> requiredId = List.of(new String[]{"12345", "23456"});
        int idx = 0;
        byte[] buffer = new byte[80];
        int byte_read = 0;
        while(true) {
            while(byte_read != '\n' && byte_read != -1) {
                byte_read = in.read();
                if(byte_read != -1 && byte_read != 13 && byte_read != 10) buffer[idx++] = (byte)byte_read;
            }
                byte[] buffer2 = new byte[idx];
                System.arraycopy(buffer, 0, buffer2, 0, idx);
                byte[] filtered = utils.addCompulsory("CS", requiredId, buffer2);
                for (byte b : filtered) out.write((char) b);
            if (byte_read == -1) return;
            idx = 0;
            byte_read = '\0';
        }
    }
}