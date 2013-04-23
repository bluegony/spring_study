
public class Client {

	private Log log;
	
	Client()
	{
		DefaultLog d = new DefaultLog();
		setLogHandler(d);			
	}

	public void setLogHandler(Log log) {
		this.log = log;
	}

	public void showLog() {
		log.write("Client.showLog()!!!");
	}
}
