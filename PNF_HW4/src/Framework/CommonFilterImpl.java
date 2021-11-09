/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class CommonFilterImpl implements CommonFilter {

	protected HashMap<String, PipedInputStream> ins = new HashMap<>();
	protected HashMap<String, PipedOutputStream> outs = new HashMap<>();


	public void addPipedInputStream(String key){
		if(ins.get(key)==null)
			ins.put(key, new PipedInputStream());
	}

	public void addPipedOutputStream(String key){
		if(outs.get(key)==null)
			outs.put(key, new PipedOutputStream());
	}

	public void addPipedInputStream(String...keys){
		for (String key : keys){
			if(this.ins.get(key)==null)
				this.ins.put(key, new PipedInputStream());
		}
	}

	public void addPipedOutputStream(String...keys){
		for (String key : keys) {
			if(this.outs.get(key)==null)
				this.outs.put(key, new PipedOutputStream());
		}
	}

	public void connectOutputTo(CommonFilter nextFilter, String pipeKey) throws IOException {
		addPipedOutputStream(pipeKey);
		nextFilter.addPipedInputStream(pipeKey);
		this.outs.get(pipeKey).connect(nextFilter.getInputStream(pipeKey));
	}
	public void connectInputTo(CommonFilter previousFilter, String pipeKey) throws IOException {
		addPipedInputStream(pipeKey);
		previousFilter.addPipedOutputStream(pipeKey);
		this.ins.get(pipeKey).connect(previousFilter.getOutputStream(pipeKey));
	}

	@Override
	public PipedOutputStream getOutputStream(String key) {
		return outs.get(key);
	}

	@Override
	public PipedInputStream getInputStream(String key) {
		return ins.get(key);
	}

	abstract public void specificComputationForFilter() throws IOException;

	public void run() {
		try {
			specificComputationForFilter();
		} catch (IOException e) {
			if (e instanceof EOFException) return;
			else System.out.println(e);
		} finally {
			closePorts();
		}
	}

	// TODO
	private void closePorts() {
		try {
			for (String s : ins.keySet()) ins.get(s).close();
			for (String s : outs.keySet()) outs.get(s).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
