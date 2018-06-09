package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class Connection extends MyThread{

	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean connectionUp;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	@Override
	public void executableTask() {
		// TODO Auto-generated method stub
		
	}

}
