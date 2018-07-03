

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ATM extends UnicastRemoteObject implements ATMInterface {
	int taikhoan;
	public ATM() throws RemoteException {
		super();
		taikhoan=0;
	}

	public int kttk() throws RemoteException {
		// TODO Auto-generated method stub
		return taikhoan;
	}

	public void naptien(int x) throws RemoteException {
		// TODO Auto-generated method stub
		taikhoan+=x;
	}

	public void ruttien(int x) throws RemoteException {
		// TODO Auto-generated method stub
		taikhoan-=x;
	}
	
}
