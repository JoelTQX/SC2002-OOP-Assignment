package DataReadWrite;

import datastorage.DataStorage;

public interface WriterTest<T> {
	public void saveRecords(DataStorage dataStorage);
	abstract String[] createHeader();
	abstract String[] createCells(T object);
}
