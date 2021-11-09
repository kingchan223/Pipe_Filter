/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class CommonSourceFilterImpl extends CommonFilterImpl {

	protected final HashMap<String, BufferedInputStream> bis = new HashMap<>();
	protected final HashMap<String, String> sourceWithPipe = new HashMap<>();
	protected final List<String> sources = new ArrayList<>();

	public CommonSourceFilterImpl(String[] sources, String[] pipes) {
		for (int i = 0; i < sources.length; i++)
			connectSourceToPipe(sources[i], pipes[i]);
	}

	public void connectSourceToPipe(String source, String pipe) {
		makeBufferedOf(source);
		this.sources.add(source);
		addPipedOutputStream(pipe);
		sourceWithPipe.put(source, pipe);
	}

	public void makeBufferedOf(String source){
		try {
			BufferedInputStream br;
			if(bis.get(source)==null){
				br = new BufferedInputStream(new FileInputStream((source)));
				bis.put(source, br);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
