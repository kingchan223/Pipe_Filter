/**
 * Copyright(c) 2019 All rights reserved by JU Consulting
 */
package Framework;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public interface CommonFilter extends Runnable{
    void connectOutputTo(CommonFilter filter, String pipeKey) throws IOException;
    void connectInputTo(CommonFilter filter, String pipeKey) throws IOException;
    PipedOutputStream getOutputStream(String key);
    PipedInputStream getInputStream(String key);

    void addPipedInputStream(String key);
    void addPipedOutputStream(String key);

    void addPipedInputStream(String... keys);
    void addPipedOutputStream(String... keys);

}
