package version1.model.IO_Devices;

import version1.model.IO_Callback;
import version1.model.IO_Request;
import version1.model.basic.Range;

public class SSD extends IO_Device_Abstract {
    public SSD(Range<Long> range, Long readSpeed, Long writeSpeed, Long reactionTime) {
        super(range, readSpeed, writeSpeed, reactionTime);
    }

    @Override
    public void read(IO_Request request, IO_Callback callback) {

    }

    @Override
    public void write(IO_Request request, IO_Callback callback) {

    }

}
