package model.IO_Controllers;

import model.*;
import model.basic.CannotHotswapException;
import model.basic.Range;
import org.apache.commons.math3.distribution.AbstractRealDistribution;

public abstract class IO_Controller_Abstract implements IO_Controller {
    protected IO_Device[] devices;
    protected Range<Long> range;
    private AbstractRealDistribution readDistribution, writeDistribution;
    public IO_Controller_Abstract(AbstractRealDistribution readDistribution, AbstractRealDistribution writeDistribution){
        this.readDistribution = readDistribution;
        this.writeDistribution = writeDistribution;
    }

    @Override
    public void attachDevices(IO_Device... devices) {
        if (devices == null)
            this.devices = devices;
        else throw new CannotHotswapException("Devices are already set");
    }

    @Override
    public Range<Long> getRange() {
        if (range == null)
            range =  getRangeFromIO_Elements(devices);
        return range;
    }

}
