package com.tictactoe.designpatterns.structural;

public class BridgeDP {
	public static void main(String[] args) {
		Tv tv = new Tv();
		RemoteControl remote = new RemoteControl(tv);
		remote.togglePower();

		tv.printStatus();

		Radio radio = new Radio();
		AdvancedRemoteControl advancedRemote = new AdvancedRemoteControl(radio);

		advancedRemote.togglePower();
		advancedRemote.channelUp();
		advancedRemote.volumeDown();

		radio.printStatus();
	}

	interface Device {
		boolean isEnabled();

		void enable();

		void disable();

		int getVolume();

		void setVolume(int percent);

		int getChannel();

		void setChannel(int channel);
	}

	static class RemoteControl {
		Device device;

		RemoteControl(Device device) {
			this.device = device;
		}

		void togglePower() {
			if (device.isEnabled()) {
				device.disable();
			} else {
				device.enable();
			}
		}

		void volumeDown() {
			device.setVolume(device.getVolume() - 10);
		}

		void volumeUp() {
			device.setVolume(device.getVolume() + 10);
		}

		void channelDown() {
			device.setChannel(device.getChannel() - 1);
		}

		void channelUp() {
			device.setChannel(device.getChannel() + 1);
		}
	}

	static class AdvancedRemoteControl extends RemoteControl {

		AdvancedRemoteControl(Device device) {
			super(device);
		}

		void mute() {
			device.setVolume(0);
		}

	}

	static class Tv implements Device {
		private boolean on = false;
		private int volume = 30;
		private int channel = 1;

		@Override
		public boolean isEnabled() {
			return on;
		}

		@Override
		public void enable() {
			on = true;
		}

		@Override
		public void disable() {
			on = false;
		}

		@Override
		public int getVolume() {
			return volume;
		}

		@Override
		public void setVolume(int volume) {
			if (volume > 100) {
				this.volume = 100;
			} else if (volume < 0) {
				this.volume = 0;
			} else {
				this.volume = volume;
			}
		}

		@Override
		public int getChannel() {
			return channel;
		}

		@Override
		public void setChannel(int channel) {
			this.channel = channel;
		}

		public void printStatus() {
			System.out.println("------------------------------------");
			System.out.println("| I'm TV set.");
			System.out.println("| I'm " + (on ? "enabled" : "disabled"));
			System.out.println("| Current volume is " + volume + "%");
			System.out.println("| Current channel is " + channel);
			System.out.println("------------------------------------\n");
		}
	}

	static class Radio implements Device {
		private boolean on = false;
		private int volume = 30;
		private int channel = 1;

		@Override
		public boolean isEnabled() {
			return on;
		}

		@Override
		public void enable() {
			on = true;
		}

		@Override
		public void disable() {
			on = false;
		}

		@Override
		public int getVolume() {
			return volume;
		}

		@Override
		public void setVolume(int volume) {
			if (volume > 100) {
				this.volume = 100;
			} else {
				this.volume = Math.max(volume, 0);
			}
		}

		@Override
		public int getChannel() {
			return channel;
		}

		@Override
		public void setChannel(int channel) {
			this.channel = channel;
		}

		public void printStatus() {
			System.out.println("------------------------------------");
			System.out.println("| I'm radio.");
			System.out.println("| I'm " + (on ? "enabled" : "disabled"));
			System.out.println("| Current volume is " + volume + "%");
			System.out.println("| Current channel is " + channel);
			System.out.println("------------------------------------\n");
		}
	}
}
