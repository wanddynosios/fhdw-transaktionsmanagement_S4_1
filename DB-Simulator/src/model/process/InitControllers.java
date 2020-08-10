package model.process;

import model.IO_Controllers.HDDController;
import model.IO_Controllers.NCQ_HDDController;
import model.IO_Controllers.SSDController;
import model.IO_Devices.HDD;
import model.IO_Devices.NCQ_HDD;
import model.IO_Devices.SSD;
import model.ProxyController;
import model.basic.Range;
import org.apache.commons.math3.distribution.UniformRealDistribution;

public class InitControllers {
    private ProxyController proxyController = new ProxyController();
    private HDDController hddController = new HDDController(new UniformRealDistribution()); //TODO
    private NCQ_HDDController ncq_hddController = new NCQ_HDDController();
    private SSDController ssdController = new SSDController();
    private HDD hdd;
    private NCQ_HDD ncq_hdd;
    private SSD ssd;

    private Range<Long> hddRange = new Range<Long>(0l, 10000000l);
    private Range<Long> ncq_hddRange = new Range<Long>(10000000l,20000000l);
    private Range<Long> ssdRange = new Range<Long>(20000000l, 20000000l);

    public ProxyController init(){
        hdd = new HDD(hddRange);
        ncq_hdd = new NCQ_HDD(ncq_hddRange);
        ssd = new SSD(ssdRange);

        hddController.attachDevices(hdd);
        ncq_hddController.attachDevices(ncq_hdd);
        ssdController.attachDevices(ssd);

        proxyController.attachControllers(hddController, ncq_hddController, ssdController);
        return proxyController;
    }
}
