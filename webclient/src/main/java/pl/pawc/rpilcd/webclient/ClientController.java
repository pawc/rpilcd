package pl.pawc.rpilcd.webclient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.ObjectInputStream;
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
		Boolean output = false;
		if(request.getParameter("output") != null) output = true;

		Data data = new Data(message, output);

		String result = "";	
		Socket socket;

		try{
			socket = new Socket("139.96.30.151", 3000);
			//socket = new Socket("localhost", 3000);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(data);
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Boolean state = (Boolean) ois.readObject();
			
			socket.close();
			result = "message sent successfully. Input state: "+state;
		} 
		catch(IOException e) {
			result = e.toString();
		}
		catch(ClassNotFoundException e){
			result = e.toString();
		}
		

		return new ModelAndView("resultPage", "result", result);
	}
	
}
