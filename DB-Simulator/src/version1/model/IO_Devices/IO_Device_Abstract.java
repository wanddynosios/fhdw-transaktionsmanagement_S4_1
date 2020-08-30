package version1.model.IO_Devices;

import version1.model.IO_Device;
import version1.model.basic.Range;

public abstract class IO_Device_Abstract implements IO_Device {
    Range<Long> range;
    Long readSpeed, writeSpeed, reactionTime;

    public IO_Device_Abstract(Range<Long> range, Long readSpeed, Long writeSpeed, Long reactionTime){
        this.range = range;
        this.readSpeed = readSpeed;
        this.writeSpeed = writeSpeed;
        this.reactionTime = reactionTime;
    }


    @Override
    public Range<Long> getRange() {
        return range;
    }
}
