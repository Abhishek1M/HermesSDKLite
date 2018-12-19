package security;

/**
 * This extension to java.util.BitSet overrides the built-in "length()" function
 * to better define the requested or desired size of the object in order to 
 * accommodate a more fixed-length paradigm.  Put more simply, if you declare the BitSet
 * to be 5 bits long, this "length()" method will return 5, while the built-in method would
 * return the number of bits allocated for the BitSet which, depending on the implementation,
 * could very well be much larger. 
 * 
 *  The constructors and get(int, int) method are also overridden to ensure the 
 * encapsulated environment to the user (i.e. the user will always receive and be using
 * this BitSet, not a java.util.BitSet, unless they explicitly ask for the latter).
 * 
 * @author Software Verde: Andrew Groot
 * @author Software Verde: Josh Green
 */
public class CBitSet extends java.util.BitSet {
	public static final int DEFAULT_SIZE = 8;
	private static final long serialVersionUID = 1L;
	private int size;
	
	/**
	 * Creates a BitSet with DEFAULT_SIZE bits.
	 */
	public CBitSet() {
		super(DEFAULT_SIZE);
		size = DEFAULT_SIZE;
	}
	
	/**
	 * Creates a BitSet with a specified number of bits.
	 * @param nbits The size of the created BitSet.
	 */
	public CBitSet(int nbits) {
		super(nbits);
		size = nbits;
	}
	
        /**
         * 
         * @param low
         * @param high
         * @return 
         */
	@Override
	public CBitSet get(int low, int high) {
		CBitSet n = new CBitSet(high-low);
		for (int i=0; i < (high-low); i++) {
			n.set(i, this.get(low+i));
		}
		return n;
	}
	
        /**
         * 
         * @return 
         */
	@Override
	/**
	 * Returns the size of the BitSet as declared or requested (the fixed-length ).
	 * @see java.util.BitSet#length()
	 */
	public int length() {
		return size;
	}

	/**
	 * Provides access to the "logical" length of the BitSet, as in the original "length()" method.
	 * @see java.util.BitSet#length()
	 */
	public int llength() {
		return super.length();
	}
}