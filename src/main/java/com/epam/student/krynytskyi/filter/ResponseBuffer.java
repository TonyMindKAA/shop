package com.epam.student.krynytskyi.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class ResponseBuffer extends HttpServletResponseWrapper {

    private ByteArrayOutputStream internalBuffer;
    private byte[] bufferContent;
    private boolean finished = false;
    private PrintWriter pw;
    private ServletOutputStream sout;

    public ResponseBuffer(HttpServletResponse response) {
        super(response);
        internalBuffer = new ByteArrayOutputStream();
    }

    @Override
    public void setContentLength(int len) {
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (pw != null)
            throw new IllegalStateException();

        if (sout == null) {
            sout = new ServletOutputStream() {
                private boolean closed = false;

                @Override
                public void write(int b) throws IOException {
                    if (closed)
                        throw new IOException(
                                "This output stream has already been closed");
                    internalBuffer.write(b);
                }

                @Override
                public void write(byte[] b, int off, int len)
                        throws IOException {
                    if (closed)
                        throw new IOException(
                                "This output stream has already been closed");
                    internalBuffer.write(b, off, len);
                }

                @Override
                public void flush() throws IOException {
                    if (closed)
                        throw new IOException(
                                "This output stream has already been closed");
                    internalBuffer.flush();
                }
            };
        }

        return sout;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (sout != null)
            throw new IllegalStateException();

        if (pw == null) {
            pw = new PrintWriter(new OutputStreamWriter(internalBuffer,	getResponse().getCharacterEncoding()), true);
        }

        return pw;
    }

    public byte[] getBytes() throws IOException {
        finish();
        return bufferContent;
    }


    private void finish() throws IOException{
        if(finished)
            return;

        if (sout != null){
            sout.flush();
            sout.close();
        }
        if (pw != null){
            pw.flush();
            pw.close();
        }
        bufferContent = internalBuffer.toByteArray();
        internalBuffer.flush();
        internalBuffer.close();

        finished = true;
    }
}