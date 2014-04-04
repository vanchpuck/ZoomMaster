package test;

public interface IsSamplesMaster<S extends IsBlock, L extends IsBlock> {
	
	public boolean hasNext();
	
	public IsSamplesDataSource.Sample<S, L> getNext();
	
}
