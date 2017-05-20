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
		Boolean output0 = false;
		Boolean output1 = false;
		Boolean output2 = false;
		Boolean output3 = false;
		Boolean output4 = false;
		Boolean output5 = false;
		Boolean output6 = false;
		Boolean output7 = false;
		
		if(request.getParameter("output0") != null) output0 = true;
		if(request.getParameter("output1") != null) output1 = true;
		if(request.getParameter("output2") != null) output2 = true;
		if(request.getParameter("output3") != null) output3 = true;
		if(request.getParameter("output4") != null) output4 = true;
		if(request.getParameter("output5") != null) output5 = true;
		if(request.getParameter("output6") != null) output6 = true;
		if(request.getParameter("output7") != null) output7 = true;
		
		boolean[] outputs = {output0, output1, output2, output3, output4, output5, output6, output7};

		Data data = new Data(message, outputs);

		String result = "";	
		Socket socket;

		try{
			socket = new Socket("139.96.30.151", 3000);
			//socket = new Socket("localhost", 3000);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(data);
			oos.flush();
			
			socket.close();
			result = "data sent successfully";
		} 
		catch(IOException e) {
			result = e.toString();
		}
		
		return new ModelAndView("resultPage", "result", result);
	}
	
}
