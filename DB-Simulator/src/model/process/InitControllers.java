package model.process;

import model.IO_Controllers.HDDController;
import model.IO_Controllers.NCQ_HDDController;
import model.IO_Controllers.SSDController;
import model.IO_Devices.HDD;
import model.IO_Devices.NCQ_HDD;
import model.IO_Devices.SSD;
import model.ProxyController;
import model.basic.Range;

public class InitControllers {
    private ProxyController proxyController = new ProxyController();
    private HDDController hddController = new HDDController();
    private NCQ_HDDController ncq_hddController = new NCQ_HDDController();
    private SSDController ssdController = new SSDController();
    private HDD hdd;
    private NCQ_HDD ncq_hdd;
    private SSD ssd;

    private Range<Long> hddRange = new Range<Long>(0l, 1000000l);
    private Range<Long> ncq_hddRange = new Range<Long>(1000000l,2000000l);
    private Range<Long> ssdRange = new Range<Long>(2000000l, 2000000l);

    public void init(){
        hdd = new HDD(hddRange);
        ncq_hdd = new NCQ_HDD(ncq_hddRange);
        ssd = new SSD(ssdRange);

        hddController.attachDevices(hdd);
        ncq_hddController.attachDevices(ncq_hdd);
        ssdController.attachDevices(ssd);

        proxyController.attachControllers(hddController, ncq_hddController, ssdController);
    }
}
