package com.epam.student.krynytskyi.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GZipFilter implements Filter {

    public GZipFilter() {}

    @Override
    public void init(FilterConfig fConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest  request  = (HttpServletRequest)  req;
        HttpServletResponse response = (HttpServletResponse) res;

        if(!acceptsGZipEncoding(request)){
            chain.doFilter(request, response);
        }else{
            ResponseBuffer wrappedResp = new ResponseBuffer(response);

            chain.doFilter(request, wrappedResp);

            response.setContentType(wrappedResp.getContentType());
            response.setCharacterEncoding(wrappedResp.getCharacterEncoding());

            byte[] content = wrappedResp.getBytes();
            GzipCompressor compressor = new GzipCompressor(content);
            if(contentTypeText(wrappedResp)){
                response.setHeader("Content-Encoding", "gzip");
                compressor.compressTo(response.getOutputStream());
            }else{
                response.setContentLength(content.length);
                compressor.notCompressTo(response.getOutputStream());
            }
        }
    }


    private boolean acceptsGZipEncoding(HttpServletRequest httpRequest) {
        String acceptEncoding = httpRequest.getHeader("Accept-Encoding");
        return acceptEncoding != null && acceptEncoding.contains("gzip");
    }

    private boolean contentTypeText(HttpServletResponse response){
        String contentType = response.getContentType();
        if(contentType != null)
            return contentType.contains("text") || contentType.contains("javascript");
        return false;
    }

    private class GzipCompressor {

        private byte[] input;
        private GZIPOutputStream internalGzipOS;

        public GzipCompressor(byte[] input) {
            this.input = input;
        }

        public void compressTo(ServletOutputStream stream) throws IOException {
            internalGzipOS = new GZIPOutputStream(stream);
            internalGzipOS.write(input);
            internalGzipOS.flush();
            internalGzipOS.close();
        }

        public void notCompressTo(ServletOutputStream stream) throws IOException{
            stream.write(input);
            stream.flush();
            stream.close();
        }
    }

}