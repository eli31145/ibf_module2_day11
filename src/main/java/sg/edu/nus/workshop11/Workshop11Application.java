package sg.edu.nus.workshop11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//this is to get opts as list
import java.util.Collections;
import java.util.List;

//import third party library for logging 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//different method compared to the slides as slides code longer 

@SpringBootApplication
public class Workshop11Application {
	//Instantiate the logger
		private static final Logger logger = LoggerFactory.getLogger(Workshop11Application.class);
		//default fallback port used by spring boot application
		private static final String DEFAULT_PORT = "3000";
		public static void main(String[] args) {
		
		//code that gets the array, printed out when running spring like println
		logger.info("Workshop 11");	
		//init the spring app
		SpringApplication app = new SpringApplication(Workshop11Application.class);
		//decode the java app args using spring args helper
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		//return the args from the java args as a list of strings  
		List optsVal = appArgs.getOptionValues("port");
		//var to hold up the port number to be passed on to the spring boot app
		String portNumber = null;
		
		//check if the opt arg is null or the first elem is null before retrieving it from the env variable
		if (optsVal == null || optsVal.get(0) == null){
			//retrieve from OS env variable
			portNumber = System.getenv("PORT");
			//if OS env variable is null or empty
			//default is the DEFAULT_PORT
			if (portNumber == null)
			portNumber = DEFAULT_PORT;
		} else {
			// if both above conditions is not met get from the args of the app
			portNumber = (String)optsVal.get(0);
		}

		// check if the port number is still null or empty
		if (portNumber != null){
			// override the spring boot app port number using the defaultproperties from spring boot framework
			app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		}
		app.run(args);
	}

}
