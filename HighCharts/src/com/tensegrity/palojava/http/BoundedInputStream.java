package com.tensegrity.palojava.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class represents a bounded input stream, i.e. the number of bytes
 * which are read from the stream are restricted by constant integer value
 * 
 * @author ArndHouben
 * @version $Id: BoundedInputStream.java,v 1.3 2007/12/05 10:00:24 ArndHouben Exp $
 */
public class BoundedInputStream extends InputStream {

	private final int maxBytes; //max number of bytes to read
	private int bytesRead;		//bytes already read
	private boolean isClosed;
	private InputStream inStream = null;

	public BoundedInputStream(InputStream inStream, int maxBytes) {
		this.maxBytes = maxBytes;
		this.inStream = inStream;
	}

	public final synchronized int read() throws IOException {
		if (isClosed)
			throw new IOException("InputStream is closed!");

		if (bytesRead >= maxBytes)
			return -1;

		bytesRead++;
		return this.inStream.read();
	}

	public final synchronized int read(byte[] b, int off, int len) throws java.io.IOException {
		if (isClosed)
			throw new IOException("InputStream is closed!");

		if (bytesRead >= maxBytes)
			return -1;

		//check length
		if (bytesRead + len > maxBytes) {
			len = (int) (maxBytes - bytesRead);
		}
		int count = this.inStream.read(b, off, len);
		bytesRead += count;
		return count;
	}

	public final int read(byte[] b) throws IOException {
		return read(b, 0, b.length);
	}

	public final synchronized void close() throws IOException {
		if (!isClosed) {
			try {
				//we read until end:
				byte bytes[] = new byte[1024];
				while (this.read(bytes) >= 0) {
					;
				}
			} finally {
				isClosed = true;
			}
		}
	}

	public final synchronized long skip(long n) throws IOException {
		long length = Math.min(n, maxBytes - bytesRead);
		length = this.inStream.skip(length);
		if (length > 0) {
			bytesRead += length;
		}
		return length;
	}

}
