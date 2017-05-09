package pl.pawc.rpilcd.webclient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ClientController{

	@RequestMapping("result")
	public ModelAndView send(HttpServletRequest request, HttpServletResponse response){
		String message = request.getParameter("message");

		String result = "";	
		Socket socket;

		try {
			socket = new Socket("localhost", 3000);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(message+"\n");
			oos.flush();
			socket.close();
			result = "ok";
		} catch (IOException e) {
			result = e.toString();
			result = "something's wrong";
		}

		return new ModelAndView("resultPage", "result", result);
	}
	
}