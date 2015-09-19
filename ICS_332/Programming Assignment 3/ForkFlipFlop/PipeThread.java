import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class PipeThread extends Thread{
    private InputStream stdout;
    OutputStream sstdin;
	
	public PipeThread(InputStream stdout, OutputStream sstdin) {
		this.stdout = stdout;
		this.sstdin = sstdin;
	}
	
	public void run() {
		try {
			int inputByte = stdout.read();
			
			while(inputByte != -1)
			{
				sstdin.write(inputByte);
				inputByte = stdout.read();
			}
			
		}catch (Exception e) {
		  e.printStackTrace();
		}
	}

}
