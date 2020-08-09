package model;

import model.basic.Range;

public interface IO_Controller extends IO_Element{

	public void attachDevices(IO_Device... devices);

	default Range<Long> getRangeFromDevices(IO_Device... devices){
		Long minimum = null;
		Long maximum = null;
		for (IO_Device device : devices){
			if (minimum == null)
				minimum = device.getRange().getMinimum();
			else
			if (device.getRange().getMinimum() < minimum)
				minimum = device.getRange().getMinimum();
			if (maximum == null)
				maximum = device.getRange().getMaximum();
			else
			if (device.getRange().getMaximum() > maximum)
				maximum = device.getRange().getMaximum();
		}
		return new Range<Long>(minimum, maximum);
	}

}
