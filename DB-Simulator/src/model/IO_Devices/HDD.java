package model.IO_Devices;


import model.IO_Callback;
import model.IO_Request;
import model.basic.Range;

public class HDD extends IO_Device_Abstract {


    public HDD(Range<Long> range) {
        super(range);
    }

    @Override
    public void read(IO_Request request, IO_Callback callback) {

    }

    @Override
    public void write(IO_Request request, IO_Callback callback) {

    }

}
