package model;

public interface IO_Element {

	public void read(IO_Request request, IO_Callback callback);
	public void write(IO_Request request, IO_Callback callback);

}
