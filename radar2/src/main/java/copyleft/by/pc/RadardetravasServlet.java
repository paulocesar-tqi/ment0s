package copyleft.by.pc;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import copyleft.by.pc.controller.OpcaoController;

@SuppressWarnings("serial")
public class RadardetravasServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		OpcaoController.updateOptions();
		resp.getWriter().println("Foi!");
	}

}
