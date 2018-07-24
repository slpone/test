package com.aaa.deadlock.demo;


public class ThreadImplementsRunnable implements Runnable {
	static private Parent parent = new Parent();
	static private Chirdren chirdren = new Chirdren();
	private boolean isParent = true;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (isParent) {
			synchronized (parent) {
				parent.say();
				try { 
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (chirdren) {
					parent.get();
				}
			}
		}else {
			synchronized (chirdren) {
				chirdren.say();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (parent) {
					chirdren.get();
				}
			}
		}
	}
	public static void main(String[] args) {
		ThreadImplementsRunnable threadImplementsRunnable1 = new ThreadImplementsRunnable();
		threadImplementsRunnable1.isParent=true;
		ThreadImplementsRunnable threadImplementsRunnable2 = new ThreadImplementsRunnable();
		threadImplementsRunnable1.isParent=false;
		new Thread(threadImplementsRunnable1).start();
		new Thread(threadImplementsRunnable2).start();
	}

 }
