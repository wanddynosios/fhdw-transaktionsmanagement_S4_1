package version1.model.IO_Controllers;

import version1.model.IO_Callback;
import version1.model.IO_Request;

public class ReadCacheController extends IO_Controller_Abstract{
    private Long cache;
    public ReadCacheController(Long reactionTimeRead, Long getReactionTimeWrite, Long cache) {
        this.cache = cache;
    }

    @Override
    public void read(IO_Request request, IO_Callback callback) {

    }

    @Override
    public void write(IO_Request request, IO_Callback callback) {

    }
}
