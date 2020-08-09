package model.IO_Devices;

import model.IO_Callback;
import model.IO_Request;
import model.basic.Range;

public class SSD extends IO_Device_Abstract {

    public SSD(Range<Long> range) {
        super(range);
    }

    @Override
    public void read(IO_Request request, IO_Callback callback) {

    }

    @Override
    public void write(IO_Request request, IO_Callback callback) {

    }

}
