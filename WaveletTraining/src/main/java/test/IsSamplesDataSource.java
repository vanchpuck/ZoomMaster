package test;

public interface IsSamplesDataSource<S extends IsBlock, L extends IsBlock> {

	public static class Sample<S, L>{
		
		private S smallBlock;
		
		private L largeBlock;
		
		public Sample(S smallBlock, L largeBlock){
			this.smallBlock = smallBlock;
			this.largeBlock = largeBlock;
		}
		
		public S getSmallBlock(){
			return smallBlock;
		}
				
		public L getLargeBlock(){
			return largeBlock;
		}
		
	}
	
	public void connect();
	
	public void disconnect();
	
	public void saveSample(Sample<S, L> sample);
	
	public Sample<S, L> findSample(S small);
	
}
