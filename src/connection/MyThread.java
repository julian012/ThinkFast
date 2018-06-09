package connection;

public abstract class MyThread implements Runnable{
	
	private boolean pause;
	private boolean stop;
	private Thread thread;

	public MyThread(){
		thread = new Thread(this);
	}

	public void start() {
		thread.start();
	}

	public synchronized void stop() {
		stop = true;
		pause = false;
		notify();
	}

	public synchronized void suspend() {
		pause = true;
	}

	public synchronized void resume() {
		pause = false;
		notify();
	}
	
	public abstract void executableTask();

	@Override
	public void run() {
		while (!stop) {
			executableTask();
			synchronized (this) {
				while (pause)
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				if (stop)
					break;
			}
		}
	}
}