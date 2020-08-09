package model.IO_Devices;

import model.IO_Callback;
import model.IO_Device;
import model.IO_Request;
import model.basic.Range;

public abstract class IO_Device_Abstract implements IO_Device {
    Range<Long> range;

    public IO_Device_Abstract(Range<Long> range){
        this.range = range;
    }


    @Override
    public Range<Long> getRange() {
        return range;
    }

    @Override
    public void read(IO_Request request, IO_Callback callback) {

    }

    @Override
    public void write(IO_Request request, IO_Callback callback) {

    }
}
