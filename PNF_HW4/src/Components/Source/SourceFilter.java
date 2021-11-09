/*
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Source;

import Framework.CommonSourceFilterImpl;
import java.io.*;

public class SourceFilter extends CommonSourceFilterImpl {
    public SourceFilter(String[] readSources, String[] outPipes) {
        super(readSources, outPipes);
    }

    @Override
    public void specificComputationForFilter() throws IOException {
        for (String source_key : sources)
            outSourceToPipe(source_key, sourceWithPipe.get(source_key));
    }

    public void outSourceToPipe(String source, String pipe) throws IOException {
        while(true) {
            int byte_read = bis.get(source).read();
            if (byte_read == -1) break;
            outs.get(pipe).write(byte_read);
        }
    }
}
