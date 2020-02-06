package servlet;

import data.db.HospitalCache;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * A {@code HospitalServlet} gives hospitals to the client or modifies services for the client.
 */
public class HospitalServlet extends HttpServlet {

    private final HospitalCache cache;

    /**
     * @param cache Does persistence operations.
     */
    public HospitalServlet(HospitalCache cache) {
        this.cache = cache;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().write(cache.getHospitals());
            response.setStatus(SC_OK);
        } catch (IOException e) {
            response.setStatus(SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String data;
        try {
            data = getDataFrom(request);
        } catch (ServletException e) {
            response.setStatus(SC_INTERNAL_SERVER_ERROR);
            return;
        }
        cache.updateService(data);
    }

    private static String getDataFrom(ServletRequest request) throws ServletException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader;
        try {
            reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            throw new ServletException("Error reading data", e);
        }
        return buffer.toString();
    }
}
