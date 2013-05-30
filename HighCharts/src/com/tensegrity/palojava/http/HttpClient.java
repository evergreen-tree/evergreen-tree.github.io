/*
 * (c) 2007 Tensegrity Software GmbH
 */
package com.tensegrity.palojava.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.tensegrity.palojava.ConnectionInfo;
import com.tensegrity.palojava.PaloException;
import com.tensegrity.palojava.http.handlers.HeaderHandler;

/**
 * <code></code>
 * TODO DOCUMENT ME
 * Performs a communication to the palo server!
 * 
 * @author ArndHouben
 * @version $Id$
 */
public class HttpClient {

    /** a cr and lf.*/
	private static final byte[] CRLF = new byte[] {(byte) 13, (byte) 10};	
	
    private final HttpConnection httpConnection;
    
	private Socket srvConnection;
	//for communication:
	private BufferedOutputStream toServer;
	private BufferedInputStream fromServer;
	
	/**
	 * Creates a new <code>HttpClient</code> instance and connects to the
	 * palo server specified by the given <code>ConnectionHttp</code> instance. 
	 * 
	 * @param paloConn a <code>ConnectionHttp</code> object containing 
	 * information about palo server
	 * @throws UnknownHostException if the IP address of the host could not be 
	 * determined
	 * @throws IOException if an I/O exception occurs on establishing the 
	 * connection
	 */
	public HttpClient(HttpConnection httpConnection)  {
		this.httpConnection = httpConnection;
	}

//	final synchronized void reconnect() throws UnknownHostException, IOException {
//		this.reconnect(TIMEOUT);
//	}
	final synchronized void reconnect(int timeout) throws UnknownHostException, IOException {
		ConnectionInfo connInfo = httpConnection.getInfo();
		srvConnection = new Socket(connInfo.getHost(), 
				Integer.parseInt(connInfo.getPort()));
		srvConnection.setSoTimeout(timeout);
		int outSize = Math.min(srvConnection.getSendBufferSize(), 2048);
		int inSize = Math.min(srvConnection.getReceiveBufferSize(), 2048);
		toServer = new BufferedOutputStream(srvConnection.getOutputStream(),
				outSize);
		fromServer = new BufferedInputStream(srvConnection.getInputStream(),
				inSize);
	}
	
	/**
	 * Checks if the connection to the server is still established
	 * @return true if there is a connection to the server, false otherwise
	 */
	final synchronized boolean isConnected() {
		return (srvConnection != null 
			&& srvConnection.isConnected() 
			&& !srvConnection.isClosed());
	}
	
	/**
	 * Disconnects this client from the palo server
	 * @throws IOException if an I/O exception occurs 
	 */
	final synchronized void disconnect() throws IOException {
		if(srvConnection == null)
			return;
		srvConnection.close();
		srvConnection = null;
	}

	/**
	 * Sends the given request string to the server. Note that all parameters
	 * have to be csv encoded
	 * @param request a request
	 * @return the answer string from the server 
	 * @throws IOException if an I/O exception occurs
	 */
	protected final synchronized String[] send(String request)
			throws ConnectException, IOException {
		BoundedInputStream in = null;
		try {
			// printwriter has problems under linux!!!
			toServer.write(
					request.getBytes(HttpParser.DEFAULT_CHARACTER_ENCODING));
			toServer.write(CRLF);
			toServer.flush();
			// get response:
			HeaderHandler headerHandler = 
					HeaderHandler.getInstance(httpConnection);
			headerHandler.parse(fromServer);
			int contentLength = headerHandler.getContentLength();
			if (contentLength == -1) // && headerHandler.isLegacyServer())
				throw new ConnectException("No response from palo server!!");

			// read content
			in = new BoundedInputStream(fromServer,contentLength);
			ArrayList respLines = new ArrayList();
			for (;;) {
				String response = HttpParser.readRawLine(in);
				if ((response == null) || (response.trim().length() < 1)) {
					break;
				}
				respLines.add(response);
			}
//			in.close();
			return (String[]) respLines.toArray(new String[respLines.size()]);
		} catch (SocketException se) {
			httpConnection.serverDown();
			return null;
		} catch (InterruptedIOException ie) {
			//timeout exception:
			throw new PaloException("Timeout Exception!!",ie);
		} finally {
			if(in != null)
				in.close();
		}
	}

}
