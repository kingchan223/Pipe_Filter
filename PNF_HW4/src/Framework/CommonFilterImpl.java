/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;

public abstract class CommonFilterImpl implements CommonFilter {

	protected ArrayList<PipedInputStream> in = new ArrayList<>();
	protected ArrayList<PipedOutputStream> out = new ArrayList<>();
	public CommonFilterImpl() {
		this.in.add(new PipedInputStream());
		this.in.add(new PipedInputStream());
		this.out.add(new PipedOutputStream());
		this.out.add(new PipedOutputStream());
	}

	public void connectOutputTo(CommonFilter nextFilter) throws IOException {
		this.out.get(0).connect(nextFilter.getIn());
		this.out.get(1).connect(nextFilter.getIn2());
	}
	public void connectInputTo(CommonFilter previousFilter) throws IOException {
		this.in.get(0).connect(previousFilter.getOut());
		this.in.get(1).connect(previousFilter.getOut2());
	}

	public PipedInputStream getIn() {
		return this.in.get(0);
	}

	public PipedInputStream getIn2() {
		return this.in.get(1);
	}

	public PipedOutputStream getOut() {
		return this.out.get(0);
	}

	public PipedOutputStream getOut2() {
		return this.out.get(1);
	}

	abstract public void specificComputationForFilter() throws IOException;
	// Implementation defined in Runnable interface for thread
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

	private void closePorts() {
		try {
			out.get(0).close();
			out.get(1).close();
			in.get(0).close();
			in.get(1).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
