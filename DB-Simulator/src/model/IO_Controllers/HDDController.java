package model.IO_Controllers;

import model.*;
import model.IO_Request;
import org.apache.commons.math3.distribution.AbstractRealDistribution;

public class HDDController extends IO_Controller_Abstract {
    public HDDController(AbstractRealDistribution readDistribution, AbstractRealDistribution writeDistribution) {
        super(readDistribution, writeDistribution);
    }

    @Override
    public void read(IO_Request request, IO_Callback callback) {

    }

    @Override
    public void write(IO_Request request, IO_Callback callback) {

    }
}
