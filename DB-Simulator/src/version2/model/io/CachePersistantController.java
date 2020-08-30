package version2.model.io;

public class CachePersistantController extends CacheController {

	private static final double CACHE_READ_HIT_RATIO = 0.3;
	private static final double CACHE_WRITE_HIT_RATIO = 0.3;

	public CachePersistantController(int redundant) {
		super(redundant, CACHE_READ_HIT_RATIO, CACHE_WRITE_HIT_RATIO);	
	}	
	
}
