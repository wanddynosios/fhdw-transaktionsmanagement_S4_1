package model;


import model.basic.Range;

public interface IO_Element {

	Range<Long> getRange();
	public void read(IO_Request request, IO_Callback callback);
	public void write(IO_Request request, IO_Callback callback);

}
