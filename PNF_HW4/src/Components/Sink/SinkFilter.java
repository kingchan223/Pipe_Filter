/*
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Sink;

import Framework.CommonSinkFilterImpl;
import java.io.IOException;

public class SinkFilter extends CommonSinkFilterImpl {

    public SinkFilter(String inPipe, String outputFile) {
        super(inPipe, outputFile);
    }

    @Override
    public void specificComputationForFilter(){
        writeFile();
    }
}
