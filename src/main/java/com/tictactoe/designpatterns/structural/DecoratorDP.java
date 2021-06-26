package com.tictactoe.designpatterns.structural;

public class DecoratorDP {

	public static void main(String[] args) {
		Application application = new Application();
		application.dumbUsageExample();

		System.out.println("----------------------------------");

		ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
		applicationConfiguration.configurationExample();
	}

	interface DataSource {
		void writeData(String data);

		String readData();
	}

	/**
	 * concrete implementation of DataSource that is passed to DataSourceDecorator
	 */
	static class FileDataSource implements DataSource {

		FileDataSource(String filename) {
		}

		@Override
		public void writeData(String data) {
			System.out.println("FileDataSource; write data; " + data);
		}

		@Override
		public String readData() {
			System.out.println("FileDataSource; readData; ");

			return "FileDataSource; read data";
		}
	}

	static class DataSourceDecorator implements DataSource {

		DataSource wrapper;

		DataSourceDecorator(DataSource source) {
			this.wrapper = source;
		}

		@Override
		public void writeData(String data) {
			System.out.println("DataSourceDecorator; writeData; " + data);
			wrapper.writeData(data);
		}

		@Override
		public String readData() {
			System.out.println("DataSourceDecorator; readData;");
			return wrapper.readData();
		}
	}

	static class EncryptionDecorator extends DataSourceDecorator {

		EncryptionDecorator(DataSource source) {
			super(source);
		}

		@Override
		public void writeData(String data) {
			System.out.println("EncryptionDecorator; writeData; " + data);
			super.writeData(data);
		}

		@Override
		public String readData() {
			System.out.println("EncryptionDecorator; readData;");
			return super.readData();
		}
	}

	static class CompressionDecorator extends DataSourceDecorator {
		CompressionDecorator(DataSource source) {
			super(source);
		}

		@Override
		public void writeData(String data) {
			System.out.println("CompressionDecorator; writeData; " + data);
			super.writeData(data);
		}

		@Override
		public String readData() {
			System.out.println("CompressionDecorator; readData;");
			return super.readData();
		}
	}

	static class Application {
		void dumbUsageExample() {
			FileDataSource source = new FileDataSource("temp file");
			source.writeData("temp w data");
			System.out.println();

			CompressionDecorator newSource = new CompressionDecorator(source);
			newSource.writeData("temp w data");
			System.out.println();

			EncryptionDecorator eSource = new EncryptionDecorator(source);
			eSource.writeData("temp w data");
			System.out.println();
		}
	}

	static class SalaryManager {
		DataSource source;

		SalaryManager(DataSource source) {
			this.source = source;
		}

		String load() {
			System.out.println("SalaryManager; load;");
			return source.readData();
		}

		void save() {
			source.writeData("temp w data");
		}
	}

	static class ApplicationConfiguration {
		void configurationExample() {
			FileDataSource source = new FileDataSource("temp s ");

			boolean enabledEncryption = true;
			boolean enabledCompression = false;

			DataSourceDecorator sourceDecorator = null;
			if (enabledEncryption) {
				sourceDecorator = new EncryptionDecorator(source);
			} else if (enabledCompression) {
				sourceDecorator = new CompressionDecorator(source);
			}

			SalaryManager logger = new SalaryManager(sourceDecorator);
			String salary = logger.load();

		}
	}
}
