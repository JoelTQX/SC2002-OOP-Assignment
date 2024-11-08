package datareadwrite;

import datastorage.DataStorage;

public interface DataWriter<T> {
	public void saveRecords(DataStorage dataStorage);
	abstract String[] createHeader();
	abstract String[] createCells(T object);
}
