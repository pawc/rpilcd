package pl.pawc.rpilcd.webclient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.pawc.rpilcd.shared.Data;

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
			Data data = new Data(message);
			oos.writeObject(data);
			oos.flush();
			socket.close();
			result = "message sent successfully";
		} catch (IOException e) {
			result = e.toString();
		}

		return new ModelAndView("resultPage", "result", result);
	}
	
}