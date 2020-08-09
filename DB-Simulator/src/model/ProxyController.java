package model;

import model.basic.CannotHotswapException;
import model.basic.Range;


public class ProxyController implements IO_Element {
    private IO_Controller[] controllers;
    private Range<Long> range;

    @Override
    public Range<Long> getRange() {
        if (range == null)
            range = getRangeFromControllers(controllers);
        return range;
    }

    private Range<Long> getRangeFromControllers(IO_Controller... controllers){
        Long minimum = null;
        Long maximum = null;
        for (IO_Controller controller: controllers){
            if (minimum == null)
                minimum = controller.getRange().getMinimum();
            else
            if (controller.getRange().getMinimum() < minimum)
                minimum = controller.getRange().getMinimum();
            if (maximum == null)
                maximum = controller.getRange().getMaximum();
            else
            if (controller.getRange().getMaximum() > maximum)
                maximum = controller.getRange().getMaximum();
        }
        return new Range<Long>(minimum, maximum);
    }

    public void attachControllers(IO_Controller... io_controllers) {
        if (controllers == null)
            controllers = io_controllers;
        else throw new CannotHotswapException("Controllers are already set");
    }

    @Override
    public void read(IO_Request request, IO_Callback callback) {

    }

    @Override
    public void write(IO_Request request, IO_Callback callback) {

    }
}
